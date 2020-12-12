#include "common.h"

#include <stdio.h>
#include <stdlib.h>

ResultImpl Ok_impl = { .code = 0, .description = "Ok"};
Result Ok = &Ok_impl;

ResultImpl ErrAllocation_impl = { .code = 1, .description = "Allocation" };
Result ErrAllocation = &ErrAllocation_impl;

ResultImpl ErrIO_impl = { .code = 2, .description = "IO" };
Result ErrIO = &ErrIO_impl;

ResultImpl ErrInput_impl = { .code = 3, .description = "Invalid Input"};
Result ErrInput = &ErrInput_impl;

void assert(const Result result, const char *const message)
{
    if (result != Ok)
    {
        fprintf(stderr, "Error [%s]: %s\n", result->description, message);
        exit(result->code);
    }
}
