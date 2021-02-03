
#include "common.hpp"

using namespace std;

int main(int argc, char *argv[])
{
    

    /* read size from input */
    if (argc==1){
        cout<<"Please specify the size of the hashtable"<<endl;
        cout<<"Usage:\t./server <size>"<<endl;
        cout<<"      \t         if the size = -1, then the shared memory is closed"<<endl;
        return 0;
    }
    else{
        SIZE= stoi(argv[1]);
        if (SIZE==-1){
            shm_unlink(SHARE_NAME);
            cout<<"Hashtable closed"<<endl;
            return 0;
        }
        if (SIZE<=0){
            cout<<"The size must be positive!"<<endl;
            return 0;
        }
        else{
            cout<<"Hashtable created!"<<endl;
            cout<<"The size of the hashtable is "<<SIZE<<"."<<endl;
        }
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
    ptr->arr = new vector<int>(SIZE);
    cout<<ptr->arr->at(0)<<endl;





    return 0;
}
