//
// Created by yunshu on 2/3/21.
//

#include "hashtable.hpp"
#include "common.hpp"
#include <functional>

unsigned long hash(string& key){
    return std::hash<string>()(key);
}

void insert(string& key, string& val){

}