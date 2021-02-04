//
// Created by yunshu on 2/3/21.
//

#ifndef SERVER_CLIENT_HASHTABLE_HPP
#define SERVER_CLIENT_HASHTABLE_HPP
#include <vector>
#include <forward_list>
#include <string>

using namespace std;
unsigned long hash(string& key);
void insert(string& key, string& val);
#endif //SERVER_CLIENT_HASHTABLE_HPP
