//
// Created by yunshu on 2/3/21.
//

#include "hashtable.hpp"
#include "common.hpp"
#include <functional>

unsigned long hash_(string& key){
    return std::hash<string>()(key) %SIZE;
}

void insert(string& key, string& val){

}
string getValueByKey(string& key){
    auto hashCode = hash_(key);
}