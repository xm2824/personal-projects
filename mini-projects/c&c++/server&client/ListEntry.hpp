//
// Created by yunshu on 2/3/21.
//

#ifndef SERVER_CLIENT_LISTENTRY_HPP
#define SERVER_CLIENT_LISTENTRY_HPP
#include <string>
#include "common.hpp"

using namespace std;
class ListEntry{
    void createSM(Shared** a,string val) ;
    Shared* ptr;
public:
    std::string key;
    ListEntry* next;


    explicit ListEntry(string key,string value);

    void closeSM() const ;
    void addNext(string key,string val);

    string readValue() const;
    void writeValue(string newVal);
    void deleteSelf() const;
    ListEntry();
    ~ListEntry();
};
#endif //SERVER_CLIENT_LISTENTRY_HPP
