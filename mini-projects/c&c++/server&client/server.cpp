
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


int main()
{
    /* closed the memory if size is -1 */
    if (false)
    {
        shm_unlink(SHARE_NAME);
        return 0;
    }


   /* create Posix shared memory */
   shm_unlink(SHARE_NAME);
   int shm_fd = shm_open(SHARE_NAME, O_RDWR|O_CREAT|O_EXCL, 0664);
   if (shm_fd < 0)
    {
        err_quit("create shm failed.");
    }
    ftruncate(shm_fd, sizeof(Shared));


    /* pointer to shared memory obect */
    auto *ptr = (Shared *)mmap(nullptr, sizeof(Shared), PROT_READ|PROT_WRITE, MAP_SHARED, shm_fd, 0);
    if (MAP_FAILED == ptr)
    {
        err_quit("mmap failed.");
    }
    close(shm_fd);

    /* initialize reader writer lock */
    ptr->rwlock=PTHREAD_RWLOCK_INITIALIZER;








    return 0;
}
