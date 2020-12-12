#ifndef ERA_TEAM_181__LAPLACE_H
#define ERA_TEAM_181__LAPLACE_H

#include "common.h"

/// Function type of every laplace kernel implementation.
typedef __cdecl void(*PFN_laplace)(uint64_t width, uint64_t height, uint8_t *dst, const uint8_t *src);

/// Performs the laplace filter on the given input image [src].
/// The resulting pixels are written into the pre-allocated memory
/// pointed to by [dst].
///
/// This is the entry function for the Laplace filter, which is
/// implemented in x86_64 Intel assembly.
__cdecl void laplace(
    uint64_t width,
    uint64_t height,
    uint8_t *dst,
    const uint8_t *src
);

// Implementation of the Laplace kernel using SIMD instructions to reduce
// execution time.
/// Performs the laplace filter on the given input image [src].
/// The resulting pixels are written into the pre-allocated memory
/// pointed to by [dst].
///
/// This is the entry function for the Laplace filter, which is
/// implemented in x86_64 Intel assembly.
__cdecl void laplace_simd(
    uint64_t width,
    uint64_t height,
    uint8_t *dst,
    const uint8_t *src
);

#endif // ERA_TEAM_181__LAPLACE_H
