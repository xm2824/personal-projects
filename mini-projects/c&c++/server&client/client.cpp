#include "common.hpp"
#include "ListEntry.hpp"
#include "hashtable.hpp"


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


    /* interactive */
    cout<<"Welcome!"<<endl;
    string help = "Usage:\ninsert <key> <value>\t\tadd a new k-v pair\n"
                  "read   <key>        \t\tread the value of corresponding key\n"
                  "delete <key>        \t\tdelete k-v pair\n"
                  "exit                \t\texit the program\n";
    cout<<help<<endl;
    string input;
    getline(cin,input);
    while (input!="exit"){
        // read
        if (stringBeginsWith(input,"read")){
            std::string delimiter = " ";
            size_t pos = 0;
            std::string token;
            int count = 0;
            while ((pos = input.find(delimiter)) != std::string::npos) {
                token = input.substr(0, pos);
                std::cout << token << std::endl;
                input.erase(0, pos + delimiter.length());
                count ++;
                if(count==2){
                    break;
                }
            }
            cout<<getValueByKey(token,hashTable);

        }

        // insert
        else if (stringBeginsWith(input,"insert")){

        }

        // delete
        else if (stringBeginsWith(input,"delete")){

        }

        // help
        else{
            cout<<help<<endl;
        }
        getline(cin,input);
    }





    return 0;
}