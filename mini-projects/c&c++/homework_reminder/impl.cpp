#include<iostream>
#include<fstream>
#include<stdio.h>
#include<stdint.h>
#include<vector>
#include<string.h>
#include<stdlib.h>
using namespace std;
#define PATH  ".homework.txt"
#define nEQ(s1,s2,n) (!strncasecmp((s1),(s2),(n)))
FILE* file;

// vector of char* for dynamically adding/removing HW entries
vector< char*> hws;

// print or not booleans
vector<int> prints;


inline void write();

// function to setup the homework file
inline void setup_homework_file(){
    cout<<"请输入您这学期的课程, 以空格分开：\nPlease enter your subjects in this semester, separated by space:\n";
    char line[50];
    fgets(line,50,stdin);
    std::ofstream outfile;

    outfile.open(".homework.txt", std::ios_base::app); // append instead of overwrite
  

    for(int i = 0; i<strlen(line);i++){
        // if not space char, write it to flie
        if(line[i]!=' '){
            outfile << line[i];
        }

        // else break line
        else{
            outfile << " 1\n";
        }
    }
    outfile.close();

}

// load data to hws and prints
inline void load(){
    file =fopen(PATH,"r");
    if(file ==NULL){
        setup_homework_file();
        return;
    }

    char line[100];
    while (fgets(line,50,file))
    {
        if(strcasecmp("\n",line)){
            char* hw = new char[15];
            int print;
            sscanf(line,"%s %d\n",hw,&print); 
            hws.push_back(hw);
            prints.push_back(print);
        }
    }
    
    
}


// function to print the homework
inline void printHW(){

        load();
        int count = 0;
        for (size_t i = 0; i < hws.size();i++)
        {
            if(prints[i]){
                printf("%d. %s\n",count,hws[i]);
                count++;
            }
        } 
    
   
}
inline void __print_internal(){
    int count = 0;
        for (size_t i = 0; i < hws.size();i++)
        {
            if(prints[i]){
                printf("%d. %s\n",count,hws[i]);
                count++;
            }
        } 
}


// load the template to disk
//! must be called after opening the file
inline void reload(){
    system("rm -rf .homework.txt");
}

// add item to HW
inline void add(char* nhw){
    load();

    for (size_t i = 0; i < hws.size(); i++)
    {
        if(!strcasecmp(hws[i],nhw)){
             char command[50];
            strcpy(command,"sed -i 's/");
            strcat(command,hws[i]);
            strcat(command," 0/");
            strcat(command,hws[i]);
            strcat(command," 1/g' ");
            strcat(command,PATH);
            system(command);

            prints[i]=1;
             
            return;
        }
    }
    
    // NOT_CONTAINED
    freopen(PATH,"a",file);
    char temp[20]="\n";
    strcat(temp,nhw);
    strcat(temp," 1\n\n");
    fwrite(temp,strlen(temp),1,file);
    char* tttemp = new char[strlen(nhw)+1];
    strcpy(tttemp,nhw);
    hws.emplace_back(tttemp);
    prints.push_back(1);
    
}

// delete item from HW
inline void del(const char* hw){
    load();

    for (unsigned i =0;i<hws.size();i++)
    {
        if(!strncasecmp(hw,hws[i],strlen(hw))){
            prints[i]=0;
            char command[50];
            strcpy(command,"sed -i 's/");
            strcat(command,hws[i]);
            strcat(command," 1/");
            strcat(command,hws[i]);
            strcat(command," 0/g' ");
            strcat(command,PATH);
            system(command);

            
            return;
        }
    }

    
}



int main(int argc, char** argv){

    
    // if no arguments
    if(argc ==1){
        printHW();
    }

    else{
        for (int i = 1; i < argc; i++)
        {
            if (nEQ("-a",argv[i],2)){
                for (i++; i < argc; i++)
                {
                    if(nEQ("-",argv[i],1)) break;
                    add(argv[i]);
                    
                }
                
            }
            else if (nEQ("-rm",argv[i],3)){
                for (i++; i < argc; i++)
                {
                    if(nEQ("-",argv[i],1)) break;
                    del(argv[i]);
                }
                
            }

            else if(nEQ("-rl",argv[i],3)){
                reload();
            }

            else if(nEQ("-h",argv[i],2)||nEQ("--help",argv[i],6)){
                help:
                printf("Usage:\n./homework_reminder [options]\n\t-a  [newHomeWork]\t\t\tadd a new homework\n\t-rm [homework]\t\t\t\tdelete a homework\n\t-rl             \t\t\treload homework for this semester\n\n");
                break;
            }
            else goto help;
        }
    
        
    }
    

    // free
    for (size_t i = 0; i < hws.size(); i++)
    {
     free(hws[i]);
    }
    
    
    
    if(file) fclose(file);
}
