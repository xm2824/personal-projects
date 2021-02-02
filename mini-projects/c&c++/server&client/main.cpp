#include <iostream>
#include<cstdlib>
#include<string>
#include<cstdio>
using namespace std;
class HashTableEntry {
public:
    int k;
    int v;
    HashTableEntry(int k, int v) {
        this->k= k;
        this->v = v;
    }
};
class HashMapTable {
private:
    HashTableEntry** t;
    int size;
public:
    HashMapTable(const int size) {
        this->size = size;
        t = new HashTableEntry* [size];
        for (int i = 0; i< size; i++) {
            t[i] = nullptr;
        }
    }
    int HashFunc(int k) {
        return k % size;
    }
    void Insert(int k, int v) {
        int h = HashFunc(k);
        while (t[h] != nullptr && t[h]->k != k) {
            h = HashFunc(h + 1);
        }
        if (t[h] != nullptr)
            delete t[h];
        t[h] = new HashTableEntry(k, v);
    }
    int SearchKey(int k) {
        int h = HashFunc(k);
        while (t[h] != nullptr && t[h]->k != k) {
            h = HashFunc(h + 1);
        }
        if (t[h] == nullptr)
            return -1;
        else
            return t[h]->v;
    }
    void Remove(int k) {
        int h = HashFunc(k);
        while (t[h] != nullptr) {
            if (t[h]->k == k)
                break;
            h = HashFunc(h + 1);
        }
        if (t[h] == nullptr) {
            cout<<"No Element found at key "<<k<<endl;
            return;
        } else {
            delete t[h];
        }
        cout<<"Element Deleted"<<endl;
    }
    ~HashMapTable() {
        for (int i = 0; i < size; i++) {
            if (t[i] != nullptr)
                delete t[i];
            delete[] t;
        }
    }
};
int main() {
    std::cout << "Hello, World!" << std::endl;
    return 0;
}
