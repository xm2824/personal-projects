#ifndef ERA_TEAM_181__COMMON_H
#define ERA_TEAM_181__COMMON_H

#include <stdint.h>

#ifdef assert
#undef assert
#endif

typedef struct {
    uint8_t code;
    const char *description;
} ResultImpl;

typedef ResultImpl *Result;

extern Result Ok;
extern Result ErrAllocation;
extern Result ErrIO;
extern Result ErrInput;

void assert(Result result, const char *message);

#endif // ERA_TEAM_181__COMMON_H
