#include <iostream>
#include <cstdlib>
#include <cmath>
#include <mpi/mpi.h>
#include <cstddef>
#define printf(a,b) printf(a,b);fflush(stdout);
#define SIZE 4*size
using namespace std;

int main(int argc, char **argv) {

    int rank, size;
    float* a,*b;
    MPI_Status* statues;
    MPI_Request* requests;

    MPI_Init(&argc,&argv);
    MPI_Comm_rank(MPI_COMM_WORLD,&rank);
    MPI_Comm_size(MPI_COMM_WORLD,&size);

    a=(float*)malloc(4*size*sizeof(float));
    b=(float*)malloc(4*size*sizeof(float));
    statues = (MPI_Status*) malloc(size*sizeof(MPI_Status));
    requests=(MPI_Request*) malloc(size*sizeof(MPI_Request));

    for (size_t i = 0; i < SIZE; i++)
    {
        a[i]=i/2.0f;
    }
    
    memset(b,0,4*size*sizeof(float));

    
    if(!rank){
        puts("a:\n");
    for (size_t i = 0; i < 4*size; i++)
    {
        printf("%f ",a[i]);
    }
    puts("\n\n");
    fflush(stdout);
    }

    //*
    for (size_t i = 0; i < size; i++)
    {
        if(rank!=i) MPI_Isend(a+i,4,MPI_FLOAT,i,0,MPI_COMM_WORLD,requests+i);
        else requests[i]=MPI_REQUEST_NULL;
    }
    for (size_t i = 0; i < size; i++)
    {
        if(rank!=i) MPI_Recv(b+i,4,MPI_FLOAT,i,0,MPI_COMM_WORLD,statues+i);
    }
    //*

    memcpy(b+rank,a+rank,4*sizeof(float));

    MPI_Waitall(size,requests,statues);
    if(rank==2){
    for (size_t i = 0; i < 4*size; i++)
    {
        printf("%f ",b[i]);
    }
    puts("\n");
    fflush(stdout);
    }
    free(a);free(b);free(statues);free(requests);

    MPI_Finalize();
    

    return 0;
}

