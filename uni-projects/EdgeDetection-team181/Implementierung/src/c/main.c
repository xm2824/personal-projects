#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdint.h>
#include <inttypes.h>

#include "laplace.h"

#define ANSI_COLORS
#include "io.h"

static const char *const option_help_short = "-h";
static const char *const option_help = "--help";
static const char *const option_quite = "--quiet";
static const char *const option_quite_short = "-q";
static const char *const option_no_simd_short = "-ns";
static const char *const option_no_simd = "--no-simd";
static const char *const option_profile_short = "-p";
static const char *const option_profile = "--profile";
static const char *const option_open_afterwards = "--open-afterwards";

void print_help(const char *const prog_name)
{
    printf(
        "\n"
        "Usage: " ANSI_COLOR_WHITE "%s" ANSI_COLOR_BLUE " <input-image> <output-image>"
        ANSI_COLOR_CYAN " [OPTIONS]\n" ANSI_COLOR_RESET
        "\n"
        "Options:\n"
        ANSI_COLOR_BLUE"  input-image      " ANSI_COLOR_RESET "      Input bitmap image path to be processed, "
        "e.g.: `../resources/lena.bmp`\n"
        ANSI_COLOR_BLUE"  output-image     " ANSI_COLOR_RESET "      Output bitmap image path, "
        "e.g.: `./output.bmp`\n"
        ANSI_COLOR_CYAN"  -h, --help       " ANSI_COLOR_RESET "      Print this help screen.\n"
        ANSI_COLOR_CYAN"  -q, --quiet      " ANSI_COLOR_RESET "      Do not print options.\n"
        ANSI_COLOR_CYAN"  -ns, --no-simd   " ANSI_COLOR_RESET "      Do not use the SIMD implementation.\n"
        ANSI_COLOR_CYAN"  -p, --profile    " ANSI_COLOR_RESET "      Measure how much time the algorithm and IO took.\n"
        ANSI_COLOR_CYAN"  --open-afterwards" ANSI_COLOR_RESET "      Open the created image afterwards.\n"
        "                         Only available if the output path is shorter than 1024 characters.\n"
        "\n"
        ANSI_COLOR_ITALICS "An image utility for edge detection.\n"
        "Written by Mohamed Said Derbel, Tao Xiang and Jim Eckerlein.\n" ANSI_COLOR_RESET
        "\n"
        , prog_name
    );
}

int main(const int argc, const char *const *const argv)
{
    // Print help if there are no options given.
    if(argc < 3)
    {
        print_help(argv[0]);
        return 0;
    }

    // Get the input image, the first and only positional argument.
    // Use default values for everything else.
    const char *const input_path = argv[1];
    const char *const output_path = argv[2];
    int no_simd = 0;
    int profiling = 0;
    int open_afterwards = 0;
    int quiet = 0;

    // Gather all the other options.
    for (int i = 3; i < argc; ++i)
    {
        const char *const arg = argv[i];
        if(!strcmp(arg, option_help) || !strcmp(arg, option_help_short))
        {
            // Print help screen and exit.
            print_help(argv[0]);
            return -1;
        }
        if(!strcmp(arg, option_quite) || !strcmp(arg, option_quite_short))
        {
            // Enable quiet mode.
            quiet = 1;
        }
        else if(!strcmp(arg, option_no_simd) || !strcmp(arg, option_no_simd_short))
        {
            // Enables non SIMD mode.
            no_simd = 1;
        }
        else if(!strcmp(arg, option_profile) || !strcmp(arg, option_profile_short))
        {
            // Enables profiling.
            profiling = 1;
        }
        else if(!strcmp(arg, option_open_afterwards))
        {
            // Open image afterwards.
            open_afterwards = 1;
        }
        else {
            // Unrecognized option, exit.
            printf("Unrecognized option `%s`, try: %s -h\n", arg, argv[0]);
            return -1;
        }
    }

    if(!quiet)
    {
        // Print options.
        printf(
            ANSI_COLOR_BLUE "Options:\n" ANSI_COLOR_RESET
            "  Input image path:  " ANSI_COLOR_WHITE "%s\n" ANSI_COLOR_RESET
            "  Output image path: " ANSI_COLOR_WHITE "%s\n" ANSI_COLOR_RESET
            "  SIMD:              " ANSI_COLOR_WHITE "%s\n" ANSI_COLOR_RESET
            "  Profiling:         " ANSI_COLOR_WHITE "%s\n" ANSI_COLOR_RESET
            "  Open afterwards:   " ANSI_COLOR_WHITE "%s\n" ANSI_COLOR_RESET
            "\n",
            input_path, output_path,
            no_simd ? "disabled" : "enabled",
            profiling ? "enabled" : "disabled",
            open_afterwards ? "yes" : "no");
    }

    Image input, output;
    uint64_t time_io = 0, time_kernel = 0, t0 = 0;

    // Read image from input file.
    t0 = now();
    assert(image_read_from_bmp(input_path, &input), "Cannot create the input image. Is the path correct?");
    time_io += now() - t0;

    // Create output image, allocate pixel buffer.
    assert(image_create(input.width, input.height, input.padding, &output), "Cannot create the output image.");

    // Call the requested Laplace implementation.
    const PFN_laplace laplace_impl = no_simd ? laplace : laplace_simd;
    t0 = now();
    laplace_impl(input.width, input.height, output.pixels, input.pixels);
    time_kernel += now() - t0;

    // Write the result back to the output image.
    t0 = now();
    assert(image_write_to_bmp(&output, output_path), "Cannot write the output image to disk.");
    time_io += now() - t0;

    // Cleanup resources.
    image_destroy(&input);
    image_destroy(&output);

    // Print profiling stats.
    if (profiling)
    {
        printf(
            ANSI_COLOR_BLUE "Profiling results:\n" ANSI_COLOR_RESET
            "  IO: %" PRIu64 " milliseconds\n"
            "  Kernel: %" PRIu64 " milliseconds\n"
            "\n"
            , time_io, time_kernel);
    }

    if (open_afterwards && strlen(output_path) < 1024)
    {
        char cmd[9 + 1024];
        strcpy(cmd, "xdg-open ");
        strcat(cmd, output_path);
        system(cmd);
    }
}
