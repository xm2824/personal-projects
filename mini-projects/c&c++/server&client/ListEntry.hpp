//
// Created by yunshu on 2/3/21.
//

#ifndef SERVER_CLIENT_LISTENTRY_HPP
#define SERVER_CLIENT_LISTENTRY_HPP
#include <string>
#include "common.hpp"

using namespace std;
class ListEntry{
public:
    std::string key;
    bool hasSM;
    ListEntry* next;
    Shared* ptr;

    explicit ListEntry(string& key,string& value);
    void createSM(Shared** a,string& val) ;
    void closeSM() ;
    void addNext(string& key,string& val);

    string readValue() const;
    void writeValue(string& newVal);
    void deleteSelf();
    ListEntry();
};
#endif //SERVER_CLIENT_LISTENTRY_HPP
