#include <pthread.h>
#include <iostream>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include "common.hpp"
#include "shared.hpp"

using namespace std;

int main()
{
    /* open shared memory */
    int shmFd = shm_open(SHARE_NAME, O_RDWR, 0);
    if (shmFd < 0)
    {
        err_quit("open shm failed.");
    }
    auto *ptr = (Shared *)mmap(nullptr, sizeof(Shared), PROT_READ|PROT_WRITE, MAP_SHARED, shmFd, 0);
    if (MAP_FAILED == ptr)
    {
        err_quit("mmap failed.");
    }
    close(shmFd);

    /* write to shared mem */
    pthread_rwlock_wrlock(&ptr->rwlock);
    ptr->product = 5;
    pthread_rwlock_unlock(&ptr->rwlock);

    /* read from shared mem */
    pthread_rwlock_rdlock(&ptr->rwlock);
    cout<<"consume1:"<<ptr->product<<endl;
    pthread_rwlock_unlock(&ptr->rwlock);


    return 0;
}