//
// Created by yunshu on 2/3/21.
//

#ifndef SERVER_CLIENT_HASHTABLE_HPP
#define SERVER_CLIENT_HASHTABLE_HPP
#include <string>
#include "ListEntry.hpp"

using namespace std;
unsigned long hash_(const string& key);
void insertKeyValue(const string& key2, const string& val, ListEntry *hashTable);
string getValueByKey(const string& key2, ListEntry* hashtable);
void deleteKeyValue(const string& key2, ListEntry *hashTable);
#endif //SERVER_CLIENT_HASHTABLE_HPP
