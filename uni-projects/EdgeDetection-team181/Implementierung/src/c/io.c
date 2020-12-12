#include "io.h"

#include <string.h>
#include <time.h>
#include <unistd.h>

typedef struct __attribute__((__packed__)) {
    // Primary header:
    uint8_t magic_b;                // := 'B'
    uint8_t magic_m;                // := 'M'
    uint32_t binary_size;           // Size of the whole binary bitmap data.
    uint32_t app_specific;          // := 0
    uint32_t pixel_offset;          // := 54

    // Secondary header;
    uint32_t header_byte_length;    // := 40
    uint32_t width;                 // Pixel width.
    int32_t height;                 // Pixel height. Positive means bottom to top, negative means top to bottom.
    uint16_t planes;                // := 1
    uint16_t bits_per_pixels;       // := 24
    uint32_t compression;           // := 0
    uint32_t image_size;            // Size of raw bitmap data, including padding.
    uint32_t resolution_horizontal; // := 2835
    uint32_t resolution_vertical;   // := 2835
    uint32_t colors_in_palette;     // := 0
    uint32_t important_colors;      // := 0
} BitmapHeader;

/// Returns the current time in milliseconds.
uint64_t now()
{
    struct timespec t;
    clock_gettime(CLOCK_MONOTONIC, &t);
    return t.tv_sec * 1000 + t.tv_nsec * 1e-6;
}

/// Returns the nearest multiple of 32 which is equal or greater than [n].
size_t align_to_4(const size_t n) {
    return (n - 1u + 0x4u) & -0x4u;
}

Result image_create(
        const uint32_t width,
        const uint32_t height,
        const uint32_t padding,
        Image *const out_image
) {
    uint8_t *const pixels = (uint8_t *)malloc((3 * width + padding) * height);
    if (NULL == pixels)
    {
        free(pixels);
        return ErrAllocation;
    }

    out_image->width = width;
    out_image->height = height;
    out_image->padding = padding;
    out_image->pixels = pixels;

    return Ok;
}

void image_destroy(Image *const image)
{
    free(image->pixels);
    image->pixels = NULL;
}

void image_write_pixel(
    Image *const image,
    const uint32_t x,
    const uint32_t y,
    const uint8_t a,
    const uint8_t b,
    const uint8_t c
) {
    uint8_t *const dst = &image->pixels[y * (image->width * 3 + image->padding) + 3 * x];

    dst[0] = a;
    dst[1] = b;
    dst[2] = c;
}

void image_read_pixel(
    const Image *image,
    uint32_t x,
    uint32_t y,
    uint8_t *const out_pixel
)
{
    uint8_t *const src = &image->pixels[y * (image->width * 3 + image->padding) + 3 * x];

    out_pixel[0] = src[0];
    out_pixel[1] = src[1];
    out_pixel[2] = src[2];
}

Result image_read_from_bmp(const char *const path, Image *const out_image)
{
    FILE *const fd = fopen(path, "rb");
    if (fd == NULL)
    {
        return ErrIO;
    }

    // Find size of file.
    fseek(fd, 0, SEEK_END);
    const int size = ftell(fd);
    rewind(fd);

    // Allocate a buffer, holding the file contents.
    uint8_t *const buffer = (uint8_t *)malloc(size);

    // Read file into the buffer.
    if (1 != fread(buffer, size, 1, fd))
    {
        free(buffer);
        return ErrIO;
    }

    // Close the file after reading.
    if (0 != fclose(fd))
    {
        free(buffer);
        return ErrIO;
    }

    // Read header information.
    BitmapHeader *const header = (BitmapHeader *)buffer;
    out_image->width = header->width;
    out_image->height = header->height;
    out_image->padding = align_to_4(out_image->width * 3) - out_image->width * 3;

    // Allocate pixel buffer.
    uint8_t *const pixels = malloc(header->image_size);
    if(pixels == NULL)
    {
        free(buffer);
        return ErrAllocation;
    }

    // Copy pixels from read-buffer into pixel buffer.
    memcpy(pixels, &buffer[header->pixel_offset], header->image_size);
    out_image->pixels = pixels;

    free(buffer);
    return Ok;
}

Result image_write_to_bmp(Image *const image, const char *const path)
{
    const uint32_t scanline_length = align_to_4(image->width * 3);

    // Fill out header.
    BitmapHeader header;

    header.magic_b = 'B';
    header.magic_m = 'M';
    header.binary_size = sizeof(BitmapHeader) + (scanline_length * image->height);
    header.app_specific = 0;
    header.pixel_offset = sizeof(BitmapHeader);

    header.header_byte_length = 40;
    header.width = image->width;
    header.height = image->height;
    header.planes = 1;
    header.bits_per_pixels = 24;
    header.compression = 0;
    header.image_size = scanline_length * image->height;
    header.resolution_horizontal = 2835;
    header.resolution_vertical = 2835;
    header.colors_in_palette = 0;
    header.important_colors = 0;

    // Open image file.
    FILE *const fd = fopen(path, "wb");
    if (NULL == fd)
    {
        return ErrIO;
    }

    // Write header to image file.
    if(1 != fwrite(&header, sizeof(BitmapHeader), 1, fd))
    {
        fclose(fd);
        return ErrIO;
    }

    if((3 * image->width + image->padding) % 4 != 0)
    {
        return ErrInput;
    }

    if(1 != fwrite(image->pixels, scanline_length * image->height, 1, fd))
    {
        fclose(fd);
        return ErrIO;
    }

    fclose(fd);
    return Ok;
}

void image_print(const Image *const image, FILE *const fd) {
    uint8_t pixel[3];
    for(uint32_t y = 0; y < image->height; ++y)
    {
        for(uint32_t x = 0; x < image->width; ++x)
        {
            image_read_pixel(image, x, y, pixel);
            fprintf(fd, "(%3u %3u %3u) ",
                (int)pixel[0], (int)pixel[1], (int)pixel[2]);
        }
        fprintf(fd, "\n");
    }
}
