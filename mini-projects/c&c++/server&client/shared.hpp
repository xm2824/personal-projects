//
// Created by yun-_-shu on 2/2/21.
//

#ifndef SERVER_CLIENT_SHARED_HPP
#define SERVER_CLIENT_SHARED_HPP
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include <vector>
#include <forward_list>

struct Shared{
    pthread_rwlock_t rwlock;
    int product;
    Shared(){
        product = 0;
    }
};



#endif //SERVER_CLIENT_SHARED_HPP
