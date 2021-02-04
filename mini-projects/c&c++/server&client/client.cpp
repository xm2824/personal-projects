#include "common.hpp"
#include "ListEntry.hpp"


using namespace std;

int main()
{
    /* read size */
    {
        int shmFd = shm_open(SIZE_NAME, O_RDWR, 0);
        if (shmFd < 0)
        {
            err_quit("open shm failed.");
        }
        auto ptr = (int *)mmap(nullptr, sizeof(int), PROT_READ|PROT_WRITE, MAP_SHARED, shmFd, 0);
        if (MAP_FAILED == ptr)
        {
            cout<<SIZE<<endl;
            err_quit("mmap failed.");
        }
        close(shmFd);
        SIZE = *ptr;

    }

    /* open shared memory */
    int shmFd = shm_open(SHARE_NAME, O_RDWR, 0);
    if (shmFd < 0)
    {
        err_quit("open shm failed.");
    }
    auto hashTable = (ListEntry *)mmap(nullptr, SIZE * sizeof(ListEntry), PROT_READ | PROT_WRITE, MAP_SHARED, shmFd, 0);
    if (MAP_FAILED == hashTable)
    {
        err_quit("mmap failed.");
    }
    close(shmFd);





    return 0;
}