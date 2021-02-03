#include <iostream>
#include <vector>
#include <forward_list>
#include <string>
#include "ListEntry.hpp"

using EntriesArray = ListEntry*;

using namespace std;
int main(){
    SIZE = 20;

    // store size
    {
        shm_unlink(SIZE_NAME);
        int shm_fd = shm_open(SIZE_NAME, O_RDWR|O_CREAT|O_EXCL, 0664);
        if (shm_fd < 0)
        {
            err_quit("create shm failed.");
        }
        ftruncate(shm_fd, sizeof(int));


        /* pointer to shared memory obect */
        auto a = (int*)mmap(nullptr, sizeof(int), PROT_READ|PROT_WRITE, MAP_SHARED, shm_fd, 0);
        if (MAP_FAILED == a)
        {
            err_quit("mmap failed.");
        }
        close(shm_fd);

        *a = SIZE;
    }
    SIZE = 20;
    shm_unlink(SHARE_NAME);
    int shm_fd = shm_open(SHARE_NAME, O_RDWR|O_CREAT|O_EXCL, 0664);
    if (shm_fd < 0)
    {
        err_quit("create shm failed.");
    }
    ftruncate(shm_fd, SIZE*sizeof(ListEntry));


    /* pointer to shared memory obect */
    auto array = (ListEntry*)mmap(nullptr, SIZE*sizeof(ListEntry), PROT_READ|PROT_WRITE, MAP_SHARED, shm_fd, 0);
    if (MAP_FAILED == array)
    {
        err_quit("mmap failed.");
    }
    close(shm_fd);

    for (int i = 0; i < SIZE; ++i) {
        new(array+i) ListEntry();
    }

    (array+2)->hasSM = true;
    for (int i = 0; i < SIZE; ++i) {
        cout<<(array+i)->hasSM<<endl;

    }


//    auto a =(ListEntry*) malloc(20*sizeof (ListEntry));
//    for (int i = 0; i < 20; ++i) {
//        new (a+i) ListEntry();
//    }
//
//    cout<<(a+2)->key<<endl;

};
