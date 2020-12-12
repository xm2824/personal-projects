.intel_syntax noprefix

.section .text
.global laplace_simd

#include "laplace_utils.S"

# Performs the laplace filter on the pixels of some input image.
# The image y-coordinate is expected to grow from bottom to top.
# Follows the cdecl calling convention.
# rdi: Image width
# rsi: Image height
# rdx: Destination pixel array
# rcx: Source pixel array
laplace_simd:

    # Push stack frame.
    push rbp
    mov rbp, rsp

    # Safe callee-safe registers.
    push rbx
    push r12
    push r13
    push r14
    push r15

    call compute_padding

    # ############################
    # Initialization
    # ############################

    # Build (1020, 1020, 1020, 1020):
    pxor CONSTANT_1020, CONSTANT_1020
    mov eax, 1020
    movd CONSTANT_1020, eax
    pshufd CONSTANT_1020, CONSTANT_1020, 0x00

    # Build (-2, -2, -2, -2):
    pxor CONSTANT_NEG_2, CONSTANT_NEG_2
    mov eax, -2
    movd CONSTANT_NEG_2, eax
    pshufd CONSTANT_NEG_2, CONSTANT_NEG_2, 0x00

    # Build (-3, -3, -3, -3):
    pxor CONSTANT_NEG_3, CONSTANT_NEG_3
    mov eax, -3
    movd CONSTANT_NEG_3, eax
    pshufd CONSTANT_NEG_3, CONSTANT_NEG_3, 0x00

    # Build (-4, -4, -4, -4):
    pxor CONSTANT_NEG_4, CONSTANT_NEG_4
    mov eax , -4
    movd CONSTANT_NEG_4, eax
    pshufd CONSTANT_NEG_4, CONSTANT_NEG_4, 0x00

    xor OFFS, OFFS

    pxor CENTER_PIXEL, CENTER_PIXEL
    pxor PIXEL_TOP, PIXEL_TOP
    pxor PIXEL_BOTTOM, PIXEL_BOTTOM
    pxor PIXEL_RIGHT, PIXEL_RIGHT
    pxor PIXEL_LEFT, PIXEL_LEFT

    # ############################
    # Case distinction
    # ############################

    # Ignore image at all if width or height are zero.
    cmp WIDTH, 0
    je .end
    cmp HEIGHT, 0
    je .end

    cmp WIDTH, 1
    jne .case_nx_

    cmp HEIGHT, 1
    jne .case_1xn

    call laplace_simd_1x1
    jmp .end

.case_1xn:
    call laplace_simd_1xn
    jmp .end

.case_nx_:

    cmp HEIGHT, 1
    jne .case_nxn

    call laplace_simd_nx1
    jmp .end

.case_nxn:

    call laplace_simd_nxn

.end:

    # Restore callee-safe registers.
    pop r15
    pop r14
    pop r13
    pop r12
    pop rbx

    # Pop stack frame.
    pop rbp

    ret

# Performes the laplace filter on an 1x1 image.
# May overwrite any of these registers:
# SCANLINE_LEN
# WIDTH
# HEIGHT
# DEST
# SRC
# PADDING
# OFFS
# X
# Y
# WIDTH_3
# CENTER_PIXEL
# PIXEL_TOP
# PIXEL_BOTTOM
# PIXEL_LEFT
# PIXEL_RIGH
laplace_simd_1x1:

    mov byte ptr [DEST+OFFS], 127
    mov byte ptr [DEST+OFFS+1], 127
    mov byte ptr [DEST+OFFS+2], 127

    ret

# Performes the laplace filter on a nx1 image.
# May overwrite any of these registers:
# SCANLINE_LEN
# WIDTH
# HEIGHT
# DEST
# SRC
# PADDING
# OFFS
# X
# Y
# WIDTH_3
# CENTER_PIXEL
# PIXEL_TOP
# PIXEL_BOTTOM
# PIXEL_LEFT
# PIXEL_RIGH
laplace_simd_nx1:

    # Implements the algorithm for an image of dimension n x 1, where
    # n >= 2.
    # These images form a single row.

    # Leftmost pixel.
    # a' := -a + right = right - a
    call read_center_pixel
    call read_right_pixel

    psubd PIXEL_RIGHT, CENTER_PIXEL
    movdqa CENTER_PIXEL, PIXEL_RIGHT

    call rescale_and_write

    pxor PIXEL_RIGHT, PIXEL_RIGHT

    # Go to first inner pixel.
    mov OFFS, 3

    mov X, 2
.laplace_simd_nx1__loop_x_begin:
    cmp X, WIDTH
    je .laplace_simd_nx1__loop_x_end

    # Inner pixel.
    # a' := -2a + right + left
    call read_center_pixel
    call read_right_pixel
    call read_left_pixel

    pmulld CENTER_PIXEL, CONSTANT_NEG_2
    paddd CENTER_PIXEL, PIXEL_RIGHT
    paddd CENTER_PIXEL, PIXEL_LEFT

    call rescale_and_write

    # Go to next pixel.
    add OFFS, 3

    inc X
    jmp .laplace_simd_nx1__loop_x_begin
.laplace_simd_nx1__loop_x_end:

    # Rightmost pixel.
    # a' := -a + left = left - a
    call read_center_pixel
    call read_left_pixel

    psubd PIXEL_LEFT, CENTER_PIXEL
    movdqa CENTER_PIXEL, PIXEL_LEFT

    call rescale_and_write

    pxor PIXEL_LEFT, PIXEL_LEFT

    ret

# Performes the laplace filter on an 1xn image.
# May overwrite any of these registers:
# SCANLINE_LEN
# WIDTH
# HEIGHT
# DEST
# SRC
# PADDING
# OFFS
# X
# Y
# WIDTH_3
# CENTER_PIXEL
# PIXEL_TOP
# PIXEL_BOTTOM
# PIXEL_LEFT
# PIXEL_RIGH
laplace_simd_1xn:

    # Implements the algorithm for an image of dimension 1 x n, where
    # n >= 2.
    # These images form a single column.

    # Bottom-most pixel.
    # a' := -a + top = top - a
    call read_center_pixel
    call read_top_pixel

    psubd PIXEL_TOP, CENTER_PIXEL
    movdqa CENTER_PIXEL, PIXEL_TOP

    call rescale_and_write

    pxor PIXEL_TOP, PIXEL_TOP

    # Go to first inner pixel.
    mov OFFS, 3
    add OFFS, PADDING

    mov Y, 2
.laplace_simd_1xn__loop_y_begin:
    cmp Y, HEIGHT
    je .laplace_simd_1xn__loop_y_end

    # Inner pixel.
    # a' := -2a + top + bottom
    call read_center_pixel
    call read_top_pixel
    call read_bottom_pixel

    pmulld CENTER_PIXEL, CONSTANT_NEG_2
    paddd CENTER_PIXEL, PIXEL_TOP
    paddd CENTER_PIXEL, PIXEL_BOTTOM

    call rescale_and_write

    # Go to next pixel.
    add OFFS, 3
    add OFFS, PADDING

    inc Y
    jmp .laplace_simd_1xn__loop_y_begin
.laplace_simd_1xn__loop_y_end:

    # Topmost pixel.
    # a' := -a + bottom = bottom - a
    call read_center_pixel
    call read_bottom_pixel

    psubd PIXEL_BOTTOM, CENTER_PIXEL
    movdqa CENTER_PIXEL, PIXEL_BOTTOM

    call rescale_and_write

    pxor PIXEL_BOTTOM, PIXEL_BOTTOM

    ret

# Performes the laplace filter on an 1x1 image.
# May overwrite any of these registers:
# SCANLINE_LEN
# WIDTH
# HEIGHT
# DEST
# SRC
# PADDING
# OFFS
# X
# Y
# WIDTH_3
# CENTER_PIXEL
# PIXEL_TOP
# PIXEL_BOTTOM
# PIXEL_LEFT
# PIXEL_RIGH
laplace_simd_nxn:

    # Order of pixel processing:
    # 1. Lower left corner pixel
    # 2. Bottom border pixels
    # 3. Lower right corner pixel
    # 4. Interior rows, for each:
    #   1. Leftmost border pixel
    #   2. Interior pixels (every pixel that has 8 neighbouring pixels)
    #   3. Rightmost border pixel
    # 5. Upper left corner pixel
    # 6. Top border pixels
    # 7. Upper right corner pixel

    # ############################
    # 1. Lower left corner pixel.
    # ############################

    call read_center_pixel
    call read_right_pixel
    call read_top_pixel

    # a' := -2a + top + right
    pmulld CENTER_PIXEL, CONSTANT_NEG_2
    paddd CENTER_PIXEL, PIXEL_TOP
    paddd CENTER_PIXEL, PIXEL_RIGHT

    call rescale_and_write

    # ############################
    # 2. Bottom border pixels.
    # ############################

    mov X, 2
    add OFFS, 3

.first_row_loop_begin:
    cmp X, WIDTH
    jz .first_row_loop_end

    call read_center_pixel
    call read_right_pixel
    call read_top_pixel
    call read_left_pixel

    # a' := -3a + top + right + left
    pmulld CENTER_PIXEL, CONSTANT_NEG_3
    paddd CENTER_PIXEL, PIXEL_TOP
    paddd CENTER_PIXEL, PIXEL_RIGHT
    paddd CENTER_PIXEL, PIXEL_LEFT

    call rescale_and_write

    # Increment offset.
    add OFFS, 3
    inc X
    jmp .first_row_loop_begin

.first_row_loop_end:

    # ############################
    # 3. Bottom right corner pixel.
    # ############################

    call read_center_pixel
    call read_left_pixel
    call read_top_pixel

    # a' := -2a + top + left
    pmulld CENTER_PIXEL, CONSTANT_NEG_2
    paddd CENTER_PIXEL, PIXEL_TOP
    paddd CENTER_PIXEL, PIXEL_LEFT

    call rescale_and_write

    # first row over, starting loop for interiors:
    # for each row we deal with pixels in first column befor the corresponding X-loop ,
    # and pixels in last column after that X-loop

    # ############################
    # 4. Interior rows
    # ############################

    # y loop:
    add OFFS, 3
    add OFFS, PADDING
    mov Y, 2

.loop_y_begin:
    cmp Y, HEIGHT
    je .loop_y_end

    # ############################
    # 4.1. Leftmost border pixel
    # ############################

    call read_center_pixel
    call read_right_pixel
    call read_top_pixel
    call read_bottom_pixel

    # a' := -3a + top + right + bottom
    pmulld CENTER_PIXEL, CONSTANT_NEG_3
    paddd CENTER_PIXEL, PIXEL_TOP
    paddd CENTER_PIXEL, PIXEL_RIGHT
    paddd CENTER_PIXEL, PIXEL_BOTTOM

    call rescale_and_write

    # ############################
    # 4.2 Interior pixels
    # ############################

    # x loop:
    add OFFS, 3
    mov X, 2

.loop_x_begin:

    cmp X, WIDTH
    je .loop_x_end

    call read_center_pixel
    call read_right_pixel
    call read_left_pixel
    call read_top_pixel
    call read_bottom_pixel

    # a' := -4a + top + right + bottom + left
    pmulld CENTER_PIXEL, CONSTANT_NEG_4
    paddd CENTER_PIXEL, PIXEL_TOP
    paddd CENTER_PIXEL, PIXEL_RIGHT
    paddd CENTER_PIXEL, PIXEL_BOTTOM
    paddd CENTER_PIXEL, PIXEL_LEFT

    call rescale_and_write

    # Go to next pixel.
    add OFFS, 3

    inc X
    jmp .loop_x_begin
.loop_x_end:

    # ############################
    # 4.3 Rightmost border pixel
    # ############################

    call read_center_pixel
    call read_left_pixel
    call read_top_pixel
    call read_bottom_pixel

    # a' := -3a + top + bottom + left
    pmulld CENTER_PIXEL, CONSTANT_NEG_3
    paddd CENTER_PIXEL, PIXEL_TOP
    paddd CENTER_PIXEL, PIXEL_BOTTOM
    paddd CENTER_PIXEL, PIXEL_LEFT

    call rescale_and_write

    # Current row is done.
    # Go to next pixel, skipping the padding.
    add OFFS, 3
    add OFFS, PADDING

    inc Y
    jmp .loop_y_begin
.loop_y_end:

    # ############################
    # 5. Upper left corner pixel
    # ############################

    call read_center_pixel
    call read_right_pixel
    call read_bottom_pixel

    # a' := -2a + right + bottom
    pmulld CENTER_PIXEL, CONSTANT_NEG_2
    paddd CENTER_PIXEL, PIXEL_RIGHT
    paddd CENTER_PIXEL, PIXEL_BOTTOM

    call rescale_and_write

    # ############################
    # 6. Top border pixels
    # ############################

    mov X, 2
    add OFFS, 3
.last_row_loop_begin:
    cmp X, WIDTH
    jz .last_row_loop_end

    call read_center_pixel
    call read_right_pixel
    call read_bottom_pixel
    call read_left_pixel

    # a' := -3a + right + bottom + left
    pmulld CENTER_PIXEL, CONSTANT_NEG_3
    paddd CENTER_PIXEL, PIXEL_RIGHT
    paddd CENTER_PIXEL, PIXEL_BOTTOM
    paddd CENTER_PIXEL, PIXEL_LEFT

    call rescale_and_write

    # Go to next pixel.
    add OFFS, 3
    inc X
    jmp .last_row_loop_begin

.last_row_loop_end:

    # ############################
    # 7. Upper right corner pixel
    # ############################

    call read_center_pixel
    call read_left_pixel
    call read_bottom_pixel

    # a' := -2a + bottom + left
    pmulld CENTER_PIXEL, CONSTANT_NEG_2
    paddd CENTER_PIXEL, PIXEL_BOTTOM
    paddd CENTER_PIXEL, PIXEL_LEFT

    call rescale_and_write

    # Done with all pixels.

    ret

# Rescales CENTER_PIXEL and write it to the dst image using the current offset.
rescale_and_write:
    paddd CENTER_PIXEL, CONSTANT_1020
    psrld CENTER_PIXEL, 3
    pextrb [DEST+OFFS], CENTER_PIXEL, 0
    pextrb [DEST+OFFS+1], CENTER_PIXEL, 4
    pextrb [DEST+OFFS+2], CENTER_PIXEL, 8
    ret

# Read the next three bytes into the lower three doublewords of CENTER_PIXEL.
read_center_pixel:
    pinsrb CENTER_PIXEL, [SRC+OFFS], 0
    pinsrb CENTER_PIXEL, [SRC+OFFS+1], 4
    pinsrb CENTER_PIXEL, [SRC+OFFS+2], 8
    ret

# Read the next three bytes into the lower three doublewords of PIXEL_RIGHT.
read_right_pixel:
    add OFFS, 3
    pinsrb PIXEL_RIGHT, [SRC+OFFS], 0
    pinsrb PIXEL_RIGHT, [SRC+OFFS+1], 4
    pinsrb PIXEL_RIGHT, [SRC+OFFS+2], 8
    sub OFFS, 3
    ret

# Read the next three bytes into the lower three doublewords of PIXEL_TOP.
read_top_pixel:
    add OFFS, SCANLINE_LEN
    pinsrb PIXEL_TOP, [SRC+OFFS], 0
    pinsrb PIXEL_TOP, [SRC+OFFS+1], 4
    pinsrb PIXEL_TOP, [SRC+OFFS+2], 8
    sub OFFS, SCANLINE_LEN
    ret

# Read the next three bytes into the lower three doublewords of PIXEL_LEFT.
read_left_pixel:
    sub OFFS, 3
    pinsrb PIXEL_LEFT, [SRC+OFFS], 0
    pinsrb PIXEL_LEFT, [SRC+OFFS+1], 4
    pinsrb PIXEL_LEFT, [SRC+OFFS+2], 8
    add OFFS, 3
    ret

# Read the next three bytes into the lower three doublewords of PIXEL_BOTTOM.
read_bottom_pixel:
    sub OFFS, SCANLINE_LEN
    pinsrb PIXEL_BOTTOM, [SRC+OFFS], 0
    pinsrb PIXEL_BOTTOM, [SRC+OFFS+1], 4
    pinsrb PIXEL_BOTTOM, [SRC+OFFS+2], 8
    add OFFS, SCANLINE_LEN
    ret
