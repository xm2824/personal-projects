#include <string.h>
#include <mpi/mpi.h>
#include <stdlib.h>
#include <stdio.h>
#include "search.h"
#include "helper.h"

void search_text (char* text, int num_lines, int line_length, char* search_string, int *occurences)
{
    int rank;
    int size;
    MPI_Comm_rank(MPI_COMM_WORLD,&rank);
    MPI_Comm_size(MPI_COMM_WORLD,&size);

    int n_chunks = num_lines / size;
    int running_count=0;

    if(rank!=size-1){
      for (size_t i = rank*n_chunks; i < (rank+1)*n_chunks; i++)
      {
        running_count += count_occurences(text + i * line_length, search_string);
      }
    }
    else{
      for (size_t i = rank*n_chunks; i < num_lines; i++)
      {
        running_count += count_occurences(text + i * line_length, search_string);
      }
    }
    int result;
    MPI_Reduce(&running_count,&result,1,MPI_INT,MPI_SUM,0,MPI_COMM_WORLD);

    if(!rank){
      *occurences = result;
    }

}
