//
// Created by yunshu on 2/3/21.
//

#include "hashtable.hpp"
#include "common.hpp"
HashTable::HashTable() {
    this->table = new vector<forward_list<string>>(SIZE);

}

int HashTable::getSize() {
    return this->table->size();
}

