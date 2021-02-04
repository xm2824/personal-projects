//
// Created by yunshu on 2/3/21.
//

#ifndef SERVER_CLIENT_HASHTABLE_HPP
#define SERVER_CLIENT_HASHTABLE_HPP
#include <vector>
#include <forward_list>
#include <string>

using namespace std;
unsigned long hash_(string& key);
void insert(string& key, string& val);
string getValueByKey(string& key);
#endif //SERVER_CLIENT_HASHTABLE_HPP
