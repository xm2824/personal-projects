#include "ListEntry.hpp"

#include <utility>
#include "common.hpp"
using namespace std;
ListEntry::ListEntry(string key, string value) {
    this->key = std::move(key);
    this->next = nullptr;
    this->createSM(&this->ptr,std::move(value));

}

void ListEntry::createSM(Shared** a,string value)  {
    int shm_fd = shm_open(this->key.c_str(), O_RDWR|O_CREAT|O_EXCL, 0664);
    if (shm_fd < 0)
    {
        err_quit("create shm failed. Probably the key already exists, try update");
    }
    ftruncate(shm_fd, sizeof(Shared));

    /* pointer to shared memory obect */
    *a = (Shared *)mmap(nullptr, sizeof(Shared), PROT_READ|PROT_WRITE, MAP_SHARED, shm_fd, 0);
    if (MAP_FAILED == ptr)
    {
        err_quit("mmap failed.");
    }
    close(shm_fd);

    // initialize the shared
    new (*a) Shared(std::move(value));

    // set flag
//    this->hasSM = true;









}

inline void ListEntry::closeSM() const  {
    shm_unlink(this->key.c_str());
    cout<<"SYSTEM: ListEntry object removed..."<<endl;
}

 void ListEntry::addNext(string next_key,string val) {
    this->next = new ListEntry(std::move(next_key),std::move(val));
}

void ListEntry::writeValue(string newVal) {
        pthread_rwlock_wrlock(&this->ptr->rwlock);
        this->ptr->setValue(std::move(newVal));
        pthread_rwlock_unlock(&ptr->rwlock);
}

string ListEntry::readValue() const {
    string ret;
//    int shmFd = shm_open(this->key.c_str(), O_RDWR, 0);
//    if (shmFd < 0)
//    {
//        err_quit("open shm failed.");
//    }
//    auto ptr2 = (Shared *)mmap(nullptr, sizeof(Shared), PROT_READ|PROT_WRITE, MAP_SHARED, shmFd, 0);
//    if (MAP_FAILED == ptr)
//    {
//        cout<<SIZE<<endl;
//        err_quit("mmap failed.");
//    }
//    close(shmFd);

    pthread_rwlock_rdlock(&ptr->rwlock);
    ret = ptr->getValue();
    pthread_rwlock_unlock(&ptr->rwlock);
    return ret;
}

inline void ListEntry::deleteSelf() const {
    this->closeSM();
}


/*
 * for head of the list
 */
ListEntry::ListEntry() {
    this->ptr = nullptr;
    this->key = "";
    this->next = nullptr;
}

ListEntry::~ListEntry() {
    this->deleteSelf();
}


