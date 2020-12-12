.intel_syntax noprefix

.section .text
.global laplace

#include "laplace_utils.S"

# Performs the laplace filter on the pixels of some input image.
# Follows the cdecl calling convention.
# rdi: Image width
# rsi: Image height
# rdx: Destination pixel array
# rcx: Source pixel array
laplace:

    # Safe callee-safe registers.
    push r12
    push r13
    push r14
    push r15
    push rbx

    # Ignore image at all if width or height are zero.
    cmp WIDTH, 0
    je .end
    cmp HEIGHT, 0
    je .end

    call compute_padding

    # Pixel processing:
    # Each color value is computed individually, so 3 computations per pixel
    # take place.

    # w' := (w - 1) * 3
    # h' := h - 1
    dec WIDTH
    imul WIDTH, 3
    dec HEIGHT

    # ############################
    # Initialization
    # ############################

    xor OFFS, OFFS
    xor X, X

    # ############################
    # Case distinction
    # ############################

    cmp WIDTH, 0
    jne .case_nx_

    cmp HEIGHT, 0
    jne .case_1xn

    call laplace_1x1
    jmp .end

.case_1xn:
    call laplace_1xn
    jmp .end

.case_nx_:

    cmp HEIGHT, 0
    jne .case_nxn

    call laplace_nx1
    jmp .end

.case_nxn:

    call laplace_nxn

.end:

    # ############################
    # Done.
    # ############################

    # Restore callee-safe registers.
    pop r8
    pop r15
    pop r14
    pop r13
    pop r12

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
# TEMP
# TEMP_DWORD
# ACC
# ACC_BYTE
laplace_1x1:

    mov byte ptr [DEST+OFFS], 127
    mov byte ptr [DEST+OFFS+1], 127
    mov byte ptr [DEST+OFFS+2], 127

    ret

# Implements the algorithm for an image of dimension n x 1, where n >= 2.
# These images form a single row.
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
# TEMP
# TEMP_DWORD
# ACC
# ACC_BYTE
laplace_nx1:

    # ############################
    # Left pixel.
    # ############################

    # a' := -a + right

    call read_center
    neg ACC
    call add_right
    call rescale_and_write
    inc OFFS

    call read_center
    neg ACC
    call add_right
    call rescale_and_write
    inc OFFS

    call read_center
    neg ACC
    call add_right
    call rescale_and_write
    inc OFFS

    # ############################
    # Inner pixels.
    # ############################

    mov X, 3

.laplace_nx1_begin:

    cmp X, WIDTH
    je .laplace_nx1_end

    # a' := -2a + right + left
    call read_center
    shl ACC, 1
    neg ACC

    call add_right
    call add_left

    call rescale_and_write

    # Go to next color value.
    inc OFFS
    inc X
    jmp .laplace_nx1_begin

.laplace_nx1_end:

    # ############################
    # Right pixel.
    # ############################

    # a' := -a + left

    call read_center
    neg ACC
    call add_left
    call rescale_and_write
    inc OFFS

    call read_center
    neg ACC
    call add_left
    call rescale_and_write
    inc OFFS

    call read_center
    neg ACC
    call add_left
    call rescale_and_write
    inc OFFS

    ret

# Implements the algorithm for an image of dimension 1 x n, where n >= 2.
# These images form a single column.
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
# TEMP
# TEMP_DWORD
# ACC
# ACC_BYTE
laplace_1xn:

    # ############################
    # Bottom pixel.
    # ############################

    # a' := -a + top

    call read_center
    neg ACC
    call add_top
    call rescale_and_write
    inc OFFS

    call read_center
    neg ACC
    call add_top
    call rescale_and_write
    inc OFFS

    call read_center
    neg ACC
    call add_top
    call rescale_and_write
    inc OFFS

    add OFFS, PADDING

    # ############################
    # Inner pixels.
    # ############################

    mov Y, 1

.laplace_1xn_begin:

    cmp Y, HEIGHT
    je .laplace_1xn_end

    # a' := -2a + top + bottom
    call read_center
    shl ACC, 1
    neg ACC
    call add_top
    call add_bottom
    call rescale_and_write
    inc OFFS

    call read_center
    shl ACC, 1
    neg ACC
    call add_top
    call add_bottom
    call rescale_and_write
    inc OFFS

    call read_center
    shl ACC, 1
    neg ACC
    call add_top
    call add_bottom
    call rescale_and_write
    inc OFFS

    inc Y
    add OFFS, PADDING

    jmp .laplace_1xn_begin

.laplace_1xn_end:

    # ############################
    # Right pixel.
    # ############################

    # a' := -a + bottom

    call read_center
    neg ACC
    call add_bottom
    call rescale_and_write
    inc OFFS

    call read_center
    neg ACC
    call add_bottom
    call rescale_and_write
    inc OFFS

    call read_center
    neg ACC
    call add_bottom
    call rescale_and_write
    inc OFFS

    ret

# Implements the algorithm for an image of dimension n x n, where n >= 2.
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
# TEMP
# TEMP_DWORD
# ACC
# ACC_BYTE
laplace_nxn:

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
    # Bottom left pixel.
    # ############################

.bottom_left_begin:

    cmp X, 3
    je .bottom_left_end

    # a' := -2a + right + top
    call read_center
    shl ACC, 1
    neg ACC

    call add_right
    call add_top

    call rescale_and_write

    # Go to next color value.
    inc OFFS
    inc X
    jmp .bottom_left_begin

.bottom_left_end:

    # ############################
    # Bottom border pixels.
    # ############################

.bottom_begin:

    cmp X, WIDTH
    je .bottom_end

    # a' := -3a + right + top + left
    call read_center
    imul ACC, -3

    call add_right
    call add_left
    call add_top

    call rescale_and_write

    # Go to next color value.
    inc OFFS
    inc X
    jmp .bottom_begin

.bottom_end:

    # ############################
    # Bottom right pixel.
    # ############################

    xor X, X

.bottom_right_begin:

    cmp X, 3
    je .bottom_right_end

    # a' := -2a + left + top
    call read_center
    shl ACC, 1
    neg ACC

    call add_left
    call add_top

    call rescale_and_write

    # Go to next color value.
    inc OFFS
    inc X
    jmp .bottom_right_begin

.bottom_right_end:

    add OFFS, PADDING

    # ############################
    # Interior rows.
    # ############################

    # y loop:
    mov Y, 1

.loop_y_begin:

    cmp Y, HEIGHT
    je .loop_y_end

    # ############################
    # Interior row, left border pixel.
    # ############################

    xor X, X

.left_begin:

    cmp X, 3
    je .left_end

    # a' := -3a + bottom + right + top
    call read_center
    imul ACC, -3

    call add_bottom
    call add_right
    call add_top

    call rescale_and_write

    # Go to next color value.
    inc OFFS
    inc X
    jmp .left_begin

.left_end:

    # ############################
    # Interior row, inner pixels.
    # ############################

.loop_x_begin:

    cmp X, WIDTH
    je .loop_x_end

    # a' := -4a + right + top + left + bottom
    call read_center
    shl ACC, 2
    neg ACC

    call add_right
    call add_left
    call add_top
    call add_bottom

    call rescale_and_write

    # Go to next color value.
    inc OFFS
    inc X
    jmp .loop_x_begin

.loop_x_end:

    # ############################
    # Interior row, right border pixel.
    # ############################

    xor X, X

.right_begin:

    cmp X, 3
    je .right_end

    # a' := -3a + bottom + left + top
    call read_center
    imul ACC, -3

    call add_bottom
    call add_left
    call add_top

    call rescale_and_write

    # Go to next color value.
    inc OFFS
    inc X
    jmp .right_begin

.right_end:

    add OFFS, PADDING

    inc Y
    jmp .loop_y_begin

.loop_y_end:

    # ############################
    # Top left pixel.
    # ############################

    xor X, X

.top_left_begin:

    cmp X, 3
    je .top_left_end

    # a' := -2a + right + bottom
    call read_center
    shl ACC, 1
    neg ACC

    call add_right
    call add_bottom

    call rescale_and_write

    # Go to next color value.
    inc OFFS
    inc X
    jmp .top_left_begin

.top_left_end:

    # ############################
    # Top border pixels.
    # ############################

.top_begin:

    cmp X, WIDTH
    je .top_end

    # a' := -3a + left + bottom
    call read_center
    imul ACC, -3

    call add_right
    call add_left
    call add_bottom

    call rescale_and_write

    # Go to next color value.
    inc OFFS
    inc X
    jmp .top_begin

.top_end:

    # ############################
    # Top right pixel.
    # ############################

    xor X, X

.top_right_begin:

    cmp X, 3
    je .top_right_end

    # a' := -2a + left + bottom
    call read_center
    shl ACC, 1
    neg ACC

    call add_left
    call add_bottom

    call rescale_and_write

    # Go to next color value.
    inc OFFS
    inc X
    jmp .top_right_begin

.top_right_end:

    ret

# Rescales ACC and write it to the dst image using the current offset.
rescale_and_write:
    add ACC, 1020
    shr ACC, 3
    mov [DEST+OFFS], ACC_BYTE
    ret

# Read a single byte from the source image with the current offset into TEMP
# and ACC.
read_center:
    movzx TEMP, byte ptr [SRC+OFFS]
    mov ACC, TEMP_DWORD
    ret

# Read a single byte from the source image with the current offset into TEMP
# and ACC.
add_right:
    add OFFS, 3
    movzx TEMP, byte ptr [SRC+OFFS]
    add ACC, TEMP_DWORD
    sub OFFS, 3
    ret

# Read a single byte from the source image with the current offset into TEMP
# and ACC.
add_left:
    sub OFFS, 3
    movzx TEMP, byte ptr [SRC+OFFS]
    add ACC, TEMP_DWORD
    add OFFS, 3
    ret

# Read a single byte from the source image with the current offset into TEMP
# and ACC.
add_top:
    add OFFS, SCANLINE_LEN
    movzx TEMP, byte ptr [SRC+OFFS]
    add ACC, TEMP_DWORD
    sub OFFS, SCANLINE_LEN
    ret

# Read a single byte from the source image with the current offset into TEMP
# and ACC.
add_bottom:
    sub OFFS, SCANLINE_LEN
    movzx TEMP, byte ptr [SRC+OFFS]
    add ACC, TEMP_DWORD
    add OFFS, SCANLINE_LEN
    ret
