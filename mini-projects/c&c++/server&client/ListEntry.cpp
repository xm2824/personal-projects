#include "ListEntry.hpp"
#include "common.hpp"
using namespace std;
ListEntry::ListEntry(string &key, string& value) {
    this->hasSM = false;
    this->key = move(key);
    this->next = nullptr;
    this->createSM(&this->ptr,value);
}

void ListEntry::createSM(Shared** a,string& value)  {
    int shm_fd = shm_open(this->key.c_str(), O_RDWR|O_CREAT|O_EXCL, 0664);
    if (shm_fd < 0)
    {
        err_quit("create shm failed.");
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
    (*a)->rwlock = PTHREAD_RWLOCK_INITIALIZER;

    // set flag
    this->hasSM = true;

    // set value
    this->writeValue(value);







}

void ListEntry::closeSM()  {
    shm_unlink(this->key.c_str());
    this->hasSM = false;
}

 void ListEntry::addNext(string &next_key,string& val) {
    this->next = new ListEntry(next_key,val);
}

void ListEntry::writeValue(string &newVal) {
    if (!this->hasSM){
        this->createSM(&this->ptr,newVal);
    }
    else{
        pthread_rwlock_wrlock(&this->ptr->rwlock);
        this->ptr->setValue(newVal);
        pthread_rwlock_unlock(&ptr->rwlock);
    }
}

string ListEntry::readValue() const {
    string ret;
    pthread_rwlock_rdlock(&ptr->rwlock);
    ret = ptr->getValue();
    pthread_rwlock_unlock(&ptr->rwlock);
    return ret;
}

void ListEntry::deleteSelf() {
    this->closeSM();
    delete this;
}


/*
 * for head of the list
 */
ListEntry::ListEntry() {
    this->hasSM = false;
    this->ptr = nullptr;
    this->key = "";
    this->next = nullptr;
}


