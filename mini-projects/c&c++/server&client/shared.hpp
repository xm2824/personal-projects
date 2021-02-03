//
// Created by yun-_-shu on 2/2/21.
//

#ifndef SERVER_CLIENT_SHARED_HPP
#define SERVER_CLIENT_SHARED_HPP
#include "common.hpp"

using namespace std;
class Shared{
    string value;
public:
    pthread_rwlock_t rwlock;
    string getValue() const;
    void setValue(string& val);

    Shared();

};



#endif //SERVER_CLIENT_SHARED_HPP
