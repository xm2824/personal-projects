//
// Created by yunshu on 2/3/21.
//

#ifndef SERVER_CLIENT_HASHTABLE_HPP
#define SERVER_CLIENT_HASHTABLE_HPP
#include <vector>
#include <forward_list>
#include <string>

using namespace std;
class HashTable{
private:
    vector<forward_list<string>>* table;

public:
    HashTable();
    int getSize();
};
#endif //SERVER_CLIENT_HASHTABLE_HPP
