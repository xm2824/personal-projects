//
// Created by yun-_-shu on 2/2/21.
//
#include "common.h"
#include <stdio.h>
#include <stdlib.h>
char const* SHARE_NAME = "123";
void err_quit(const char *fmt)
{
    fprintf(stderr,"%s", fmt);
    exit(EXIT_FAILURE);
}