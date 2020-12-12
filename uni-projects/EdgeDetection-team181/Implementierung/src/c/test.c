#include <stdio.h>
#include <string.h>
#include <inttypes.h>

#include "laplace.h"

#define ANSI_COLORS
#include "io.h"

void check_pixels(
    const Image *const input,
    const Image *const actual,
    const Image *const expected
) {
    (void)input;
    /*
    fprintf(stderr, "Input:\n");
    image_print(input, stderr);

    fprintf(stderr, "Expected:\n");
    image_print(expected, stderr);

    fprintf(stderr, "Actual:\n");
    image_print(actual, stderr);
    */

    unsigned errors = 0;
    for(uint32_t y = 0; y < expected->height; ++y)
    {
        for(uint32_t x = 0; x < expected->width; ++x)
        {
            uint8_t pixel_expected[3], pixel_actual[3];
            image_read_pixel(expected, x, y, pixel_expected);
            image_read_pixel(actual, x, y, pixel_actual);
            if(pixel_actual[0] != pixel_expected[0]
                || pixel_actual[1] != pixel_expected[1]
                || pixel_actual[2] != pixel_expected[2]
            ) {
                ++errors;
                fprintf(stderr,
                    ANSI_COLOR_YELLOW "Pixel at (%d, %d) is wrong, expected (%d, %d, %d), "
                    "got (%d, %d, %d).\n" ANSI_COLOR_RESET,
                    x, y, pixel_expected[0], pixel_expected[1],
                    pixel_expected[2], pixel_actual[0], pixel_actual[1], pixel_actual[2]);
            }
        }
    }

    if (errors > 0)
    {
        fprintf(stderr, ANSI_COLOR_RED "%d %s wrong.\n" ANSI_COLOR_RESET,
            errors, errors == 1 ? "pixel is" : "pixels are");
    }
    else
    {
        fprintf(stderr, ANSI_COLOR_GREEN "All pixels are correct.\n" ANSI_COLOR_RESET);
    }
}

/// Creates an image with 5x5 pixels, i.e. with a scanline length of 16 bytes,
/// including 1 byte padding.
/// Fill the image with preset data and checks the results from the kernel.
void test_image_5x5(PFN_laplace fn)
{
    Image src, dst, expected;
    const uint64_t width = 5;
    const uint64_t height = 5;
    const uint64_t len = 16;
    const uint64_t padding = 1;

    image_create(width, height, padding, &src);
    image_create(width, height, padding, &dst);
    image_create(width, height, padding, &expected);

    /// Fill source image with data.

    image_write_pixel(&src, 0, 0, 250, 192, 47);
    image_write_pixel(&src, 1, 0, 12, 137, 137);
    image_write_pixel(&src, 2, 0, 89, 19, 69);
    image_write_pixel(&src, 3, 0, 136, 23, 205);
    image_write_pixel(&src, 4, 0, 153, 215, 231);

    image_write_pixel(&src, 0, 1, 37, 216, 209);
    image_write_pixel(&src, 1, 1, 142, 97, 11);
    image_write_pixel(&src, 2, 1, 144, 24, 216);
    image_write_pixel(&src, 3, 1, 79, 166, 206);
    image_write_pixel(&src, 4, 1, 189, 108, 146);

    image_write_pixel(&src, 0, 2, 53, 231, 84);
    image_write_pixel(&src, 1, 2, 227, 243, 93);
    image_write_pixel(&src, 2, 2, 110, 200, 113);
    image_write_pixel(&src, 3, 2, 51, 208, 8);
    image_write_pixel(&src, 4, 2, 1, 106, 95);

    image_write_pixel(&src, 0, 3, 104, 16, 56);
    image_write_pixel(&src, 1, 3, 58, 30, 153);
    image_write_pixel(&src, 2, 3, 196, 174, 49);
    image_write_pixel(&src, 3, 3, 29, 125, 215);
    image_write_pixel(&src, 4, 3, 236, 59, 195);

    image_write_pixel(&src, 0, 4, 254, 240, 171);
    image_write_pixel(&src, 1, 4, 210, 212, 31);
    image_write_pixel(&src, 2, 4, 176, 194, 104);
    image_write_pixel(&src, 3, 4, 34, 245, 57);
    image_write_pixel(&src, 4, 4, 169, 118, 36);

    /// Fill image with precomputed result of the Laplace kernel.

    image_write_pixel(&expected, 0, 0, 71, 123, 159);
    image_write_pixel(&expected, 1, 0, 183, 114, 92);
    image_write_pixel(&expected, 2, 0, 130, 143, 171);
    image_write_pixel(&expected, 3, 0, 116, 168, 113);
    image_write_pixel(&expected, 4, 0, 129, 90, 113);

    image_write_pixel(&expected, 0, 1, 169, 111, 66);
    image_write_pixel(&expected, 1, 1, 109, 156,203);
    image_write_pixel(&expected, 2, 1, 108, 175, 69);
    image_write_pixel(&expected, 3, 1, 153, 89, 96);
    image_write_pixel(&expected, 4, 1, 85, 147, 139);

    image_write_pixel(&expected, 0, 2, 153, 100, 140);
    image_write_pixel(&expected, 1, 2, 59, 75, 126);
    image_write_pixel(&expected, 2, 2, 149, 108,116);
    image_write_pixel(&expected, 3, 2, 129, 98, 202);
    image_write_pixel(&expected, 4, 2, 186, 134, 135);

    image_write_pixel(&expected, 0, 3, 134, 184, 157);
    image_write_pixel(&expected, 1, 3, 190, 193, 79);
    image_write_pixel(&expected, 2, 3, 76, 109, 176);
    image_write_pixel(&expected, 3, 3, 177, 150, 58);
    image_write_pixel(&expected, 4, 3, 63, 149, 97);

    image_write_pixel(&expected, 0, 4, 103, 96, 95);
    image_write_pixel(&expected, 1, 4, 109, 106, 169);
    image_write_pixel(&expected, 2, 4, 116, 133, 105);
    image_write_pixel(&expected, 3, 4, 161, 90, 150);
    image_write_pixel(&expected, 4, 4, 119, 136, 150);

    /// Clear dst image pixels to (-1, -1, -1).
    /// This makes it easier to see areas where nothing was written to.
    memset(dst.pixels, 0xff, height * len);

    /// Run the Laplace kernel.
    fn(width, height, dst.pixels, src.pixels);

    check_pixels(&src, &dst, &expected);

    image_destroy(&src);
    image_destroy(&dst);
}

void test_image_5x1(PFN_laplace fn)
{
    Image src, dst, expected;
    const uint64_t width = 5;
    const uint64_t height = 1;
    const uint64_t len = 16;
    const uint64_t padding = 1;

    image_create(width, height, padding, &src);
    image_create(width, height, padding, &dst);
    image_create(width, height, padding, &expected);

    /// Fill source image with data.

    image_write_pixel(&src, 0, 0, 250, 192, 47);
    image_write_pixel(&src, 1, 0, 12, 137, 137);
    image_write_pixel(&src, 2, 0, 89, 19, 69);
    image_write_pixel(&src, 3, 0, 136, 23, 205);
    image_write_pixel(&src, 4, 0, 153, 215, 231);

    /// Fill image with precomputed result of the Laplace kernel.

    image_write_pixel(&expected, 0, 0, 97, 120, 138);
    image_write_pixel(&expected, 1, 0, 166, 119, 107);
    image_write_pixel(&expected, 2, 0, 123, 142, 153);
    image_write_pixel(&expected, 3, 0, 123, 151, 113);
    image_write_pixel(&expected, 4, 0, 125, 103, 124);

    /// Clear dst image pixels to (-1, -1, -1).
    /// This makes it easier to see areas where nothing was written to.
    memset(dst.pixels, 0xff, height * len);

    /// Run the Laplace kernel.
    fn(width, height, dst.pixels, src.pixels);

    check_pixels(&src, &dst, &expected);

    image_destroy(&src);
    image_destroy(&dst);
}

void test_image_1x5(PFN_laplace fn)
{
    Image src, dst, expected;
    const uint64_t width = 1;
    const uint64_t height = 5;
    const uint64_t len = 4;
    const uint64_t padding = 1;

    image_create(width, height, padding, &src);
    image_create(width, height, padding, &dst);
    image_create(width, height, padding, &expected);

    /// Fill source image with data.

    image_write_pixel(&src, 0, 0, 250, 192, 47);
    image_write_pixel(&src, 0, 1, 12, 137, 137);
    image_write_pixel(&src, 0, 2, 89, 19, 69);
    image_write_pixel(&src, 0, 3, 136, 23, 205);
    image_write_pixel(&src, 0, 4, 153, 215, 231);

    /// Fill image with precomputed result of the Laplace kernel.

    image_write_pixel(&expected, 0, 0, 97, 120, 138);
    image_write_pixel(&expected, 0, 1, 166, 119, 107);
    image_write_pixel(&expected, 0, 2, 123, 142, 153);
    image_write_pixel(&expected, 0, 3, 123, 151, 113);
    image_write_pixel(&expected, 0, 4, 125, 103, 124);

    /// Clear dst image pixels to (-1, -1, -1).
    /// This makes it easier to see areas where nothing was written to.
    memset(dst.pixels, 0xff, height * len);

    /// Run the Laplace kernel.
    fn(width, height, dst.pixels, src.pixels);

    check_pixels(&src, &dst, &expected);

    image_destroy(&src);
    image_destroy(&dst);
}

void test_image_1x1(PFN_laplace fn)
{
    Image src, dst, expected;
    const uint64_t width = 1;
    const uint64_t height = 1;
    const uint64_t len = 4;
    const uint64_t padding = 1;

    image_create(width, height, padding, &src);
    image_create(width, height, padding, &dst);
    image_create(width, height, padding, &expected);

    /// Fill source image with data.

    image_write_pixel(&src, 0, 0, 250, 192, 47);

    /// Fill image with precomputed result of the Laplace kernel.

    image_write_pixel(&expected, 0, 0, 127, 127, 127);

    /// Clear dst image pixels to (-1, -1, -1).
    /// This makes it easier to see areas where nothing was written to.
    memset(dst.pixels, 0xff, height * len);

    /// Run the Laplace kernel.
    fn(width, height, dst.pixels, src.pixels);

    check_pixels(&src, &dst, &expected);

    image_destroy(&src);
    image_destroy(&dst);
}

void test_image_1x0(PFN_laplace fn)
{
    Image src, dst, expected;
    const uint64_t width = 1;
    const uint64_t height = 0;
    const uint64_t padding = 1;

    image_create(width, height, padding, &src);
    image_create(width, height, padding, &dst);
    image_create(width, height, padding, &expected);

    /// Run the Laplace kernel. Just should not create a segfault.
    fn(width, height, dst.pixels, src.pixels);

    fprintf(stderr, ANSI_COLOR_GREEN "Did not crash.\n" ANSI_COLOR_RESET);

    image_destroy(&src);
    image_destroy(&dst);
}

void test_image_0x1(PFN_laplace fn)
{
    Image src, dst, expected;
    const uint64_t width = 0;
    const uint64_t height = 1;
    const uint64_t padding = 0;

    image_create(width, height, padding, &src);
    image_create(width, height, padding, &dst);
    image_create(width, height, padding, &expected);

    /// Run the Laplace kernel. Just should not create a segfault.
    fn(width, height, dst.pixels, src.pixels);

    fprintf(stderr, ANSI_COLOR_GREEN "Did not crash.\n" ANSI_COLOR_RESET);

    image_destroy(&src);
    image_destroy(&dst);
}

void test_image_0x0(PFN_laplace fn)
{
    Image src, dst, expected;
    const uint64_t width = 0;
    const uint64_t height = 0;
    const uint64_t padding = 0;

    image_create(width, height, padding, &src);
    image_create(width, height, padding, &dst);
    image_create(width, height, padding, &expected);

    /// Run the Laplace kernel. Just should not create a segfault.
    fn(width, height, dst.pixels, src.pixels);

    fprintf(stderr, ANSI_COLOR_GREEN "Did not crash.\n" ANSI_COLOR_RESET);

    image_destroy(&src);
    image_destroy(&dst);
}

void test_permutation(
    const char *const msg,
    void(*const test_function)(PFN_laplace),
    PFN_laplace laplace_impl
) {
    fprintf(stderr, ANSI_COLOR_CYAN "======================================\n" ANSI_COLOR_RESET);
    fprintf(stderr, ANSI_COLOR_CYAN "%s ...\n" ANSI_COLOR_RESET, msg);
    const uint64_t t0 = now();
    test_function(laplace_impl);
    const uint64_t t1 = now();
    const uint64_t dt = t1 - t0;
    fprintf(stderr, "  took %" PRIu64 " ms\n", dt);
}

int main()
{
    test_permutation("Testing 5x5 image with laplace kernel", test_image_5x5, laplace);
    test_permutation("Testing 5x1 image with laplace kernel", test_image_5x1, laplace);
    test_permutation("Testing 1x5 image with laplace kernel", test_image_1x5, laplace);
    test_permutation("Testing 1x1 image with laplace kernel", test_image_1x1, laplace);
    test_permutation("Testing 1x0 image with laplace kernel", test_image_1x0, laplace);
    test_permutation("Testing 0x1 image with laplace kernel", test_image_0x1, laplace);
    test_permutation("Testing 0x0 image with laplace kernel", test_image_0x0, laplace);

    test_permutation("Testing 5x5 image with SIMD laplace kernel", test_image_5x5, laplace_simd);
    test_permutation("Testing 5x1 image with SIMD laplace kernel", test_image_5x1, laplace_simd);
    test_permutation("Testing 1x5 image with SIMD laplace kernel", test_image_1x5, laplace_simd);
    test_permutation("Testing 1x1 image with SIMD laplace kernel", test_image_1x1, laplace_simd);
    test_permutation("Testing 1x0 image with SIMD laplace kernel", test_image_1x0, laplace_simd);
    test_permutation("Testing 0x1 image with SIMD laplace kernel", test_image_0x1, laplace_simd);
    test_permutation("Testing 0x0 image with SIMD laplace kernel", test_image_0x0, laplace_simd);
}
