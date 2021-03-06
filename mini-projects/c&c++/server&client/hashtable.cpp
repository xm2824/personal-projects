//
// Created by yunshu on 2/3/21.
//

#include "hashtable.hpp"
#include <functional>
using namespace std;

unsigned long hash_(const string& key){
    return std::hash<string>()(key) %SIZE;
}

void insertKeyValue(const string& key2, const string& val, ListEntry *hashTable) {
    auto hashCode = hash_(key2);
    auto tmp = hashTable + hashCode;
    while (tmp->next){
        tmp = tmp->next;
        if (tmp->key == key2){
            tmp->writeValue(val);
            return;
        }
    }
    // key not existed yet
    tmp->addNext(key2,val);
    cout<<"SYSTEM: key-value pair inserted..."<<endl;

}
string getValueByKey(const string& key2, ListEntry* hashtable){
    auto hashCode = hash_(key2);
    auto tmp = hashtable+hashCode;
    while (tmp->next){
        tmp = tmp->next;
        if(tmp->key == key2){
            return tmp->readValue();
        }
    }
    return "ERROR: this key doesn't exist...";
}

void deleteKeyValue(const string& key2, ListEntry *hashTable)
{
    auto hashCode = hash_(key2);
    auto tmp = hashTable + hashCode;
    while (tmp->next){
        if(tmp->next->key==key2){
            auto prev = tmp;
            tmp=tmp->next;
            auto nextEntry = tmp->next;
            prev->next = nextEntry;

            delete tmp;
            cout<<"SYSTEM: Key-value pair deleted..."<<endl;
            return;
        }
        else{
            tmp = tmp->next;
        }
    }
    cout<<"SYSTEM: Key doesn't exist, deletion aborts..."<<endl;
}