#include "ListEntry.hpp"
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

    // store size
    {
        shm_unlink(SIZE_NAME);
        int shm_fd = shm_open(SIZE_NAME, O_RDWR|O_CREAT|O_EXCL, 0664);
        if (shm_fd < 0)
        {
            err_quit("create shm failed. Probably the key already exists, try update");
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

    /* create the shared memory */
    shm_unlink(SHARE_NAME);
    int shm_fd = shm_open(SHARE_NAME, O_RDWR|O_CREAT|O_EXCL, 0664);
    if (shm_fd < 0)
    {
        err_quit("create shm failed.");
    }
    ftruncate(shm_fd, SIZE*sizeof(ListEntry));


    /* pointer to shared memory obect */
    auto hashTable = (ListEntry*)mmap(nullptr, SIZE * sizeof(ListEntry), PROT_READ | PROT_WRITE, MAP_SHARED, shm_fd, 0);
    if (MAP_FAILED == hashTable)
    {
        err_quit("mmap failed.");
    }
    close(shm_fd);
    for (int i = 0; i < SIZE; ++i) {
        new(hashTable + i) ListEntry();
    }



//    hashTable->addNext("wwaawwwwaa", "1");
//    cout << hashTable->next->readValue() << endl;
//    hashTable->next->writeValue("hladsf");
//    cout << hashTable->next->readValue() << endl;


    return 0;
}
