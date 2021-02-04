//
// Created by yun-_-shu on 2/2/21.
//

#ifndef SERVER_CLIENT_COMMON_HPP
#define SERVER_CLIENT_COMMON_HPP
#include "pthread.h"
#include <unistd.h>
#include <iostream>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include "shared.hpp"




extern char const * SHARE_NAME;
extern char const* SIZE_NAME;
extern int SIZE;
void err_quit(const char *fmt);
#endif //SERVER_CLIENT_COMMON_HPP
