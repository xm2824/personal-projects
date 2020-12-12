.intel_syntax noprefix

.section .text

# Define a bijection between variable names and used registers.
#define SCANLINE_LEN    r9
#define WIDTH           rdi
#define HEIGHT          rsi
#define DEST            rdx
#define SRC             rcx
#define PADDING         r8
#define OFFS            rbx
#define X               r12
#define Y               r13
#define WIDTH_3         r15

#define TEMP            r14
#define TEMP_DWORD      r14d
#define ACC             eax
#define ACC_BYTE        al

#define CENTER_PIXEL    xmm0
#define PIXEL_TOP       xmm1
#define PIXEL_BOTTOM    xmm2
#define PIXEL_LEFT      xmm3
#define PIXEL_RIGHT     xmm4
#define CONSTANT_1020   xmm5
#define CONSTANT_NEG_2  xmm6
#define CONSTANT_NEG_3  xmm7
#define CONSTANT_NEG_4  xmm8

# Compute padding, which is added per scanline.
# The padding is computed by computing the scanline length,
# that is the length of each pixel row in color values plus
# the trailling padding.
# We use the following equation to get the scanline length:
# width_3:= 3 * image_width
# padding = width_3 - (width_3 + 3) & -4
# The function is not cdecl compilant.
compute_padding:
    mov WIDTH_3, WIDTH
    shl WIDTH_3, 1
    add WIDTH_3, WIDTH

    mov PADDING, WIDTH_3
    add PADDING, 3
    and PADDING, -4
    mov SCANLINE_LEN, PADDING
    sub PADDING, WIDTH_3

    ret
