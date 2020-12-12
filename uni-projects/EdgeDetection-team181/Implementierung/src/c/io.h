#ifndef ERA_TEAM_181__IO_H
#define ERA_TEAM_181__IO_H

#include <stdlib.h>
#include <stdio.h>

#include "common.h"

#ifdef ANSI_COLORS
#define ANSI_COLOR_ITALICS "\x1b[3m"
#define ANSI_COLOR_WHITE   "\x1b[01;37m"
#define ANSI_COLOR_RED     "\x1b[01;31m"
#define ANSI_COLOR_GREEN   "\x1b[01;32m"
#define ANSI_COLOR_YELLOW  "\x1b[01;33m"
#define ANSI_COLOR_BLUE    "\x1b[01;34m"
#define ANSI_COLOR_MAGENTA "\x1b[01;35m"
#define ANSI_COLOR_CYAN    "\x1b[01;36m"
#define ANSI_COLOR_RESET   "\x1b[0m"
#endif // ANSI_COLORS

/// Image type.
/// An image has a positive non-zero width and height.
/// The pixels buffer holds all the color values,
/// the order of color components is not specified.
/// An image can contain scanline padding, meaning
/// empty fill bytes at the end of each scanline.
/// The scanline padding is not included in the image's width.
typedef struct {
    uint32_t width;
    uint32_t height;
    uint32_t padding;
    uint8_t *pixels;
} Image;

/// Allocates an image from a given size.
/// Returns 0 on success.
Result image_create(
    uint32_t width,
    uint32_t height,
    const uint32_t padding,
    Image *out_image
);

/// Releases all memory resources hold by the image, if it does hold some.
void image_destroy(
    Image *image
);

/// Sets a pixels located at ([x], [y]) to ([a], [b], [c]).
void image_write_pixel(
    Image *image,
    uint32_t x,
    uint32_t y,
    const uint8_t a,
    const uint8_t b,
    const uint8_t c
);

void image_read_pixel(
    const Image *image,
    uint32_t x,
    uint32_t y,
    uint8_t *out_pixel
);

/// Creates and reads an image from a bitmap.
Result image_read_from_bmp(
    const char *path,
    Image *out_image
);

/// Writes an image to a bitmap.
Result image_write_to_bmp(
    Image *image,
    const char *path
);

void image_print(
    const Image *image,
    FILE *fd
);

size_t align_to_4(const size_t n);

uint64_t now();

#endif // ERA_TEAM_181__IO_H
