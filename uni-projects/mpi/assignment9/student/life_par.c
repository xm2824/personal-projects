#include<stdio.h>
#include<stdlib.h>
#include<mpi.h>
#include<string.h>
#include"helper.h"
#include"life.h"
#include"gui.h"

void make_local_rows_periodic(int height, int width, int grid[height][width])
{
  /*
    Make rows at each process periodic.
  */
  for (int i = 1; i < height - 1; i++)
    {
      // first column same as second last
      grid[i][0] = grid[i][width-2];
      // last column same as second
      grid[i][width-1] = grid[i][1];
    }
}

void prepare_halo_rows(int height, int width, int grid[height][width], int top[width], int bottom[width])
{
  // copy inner part
  for( int j = 1; j < width - 1; j++)
    {
      // first row
      top[j] = grid[1][j];
      // last row
      bottom[j] = grid[height-2][j];
    }

  // handle edge values:
  // top
  top[0] = grid[1][width-2];
  top[width-1] = grid[1][1];

  //bottom
  bottom[0] = grid[height-2][width-2];
  bottom[width-1] = grid[height-2][1];
}

void evolve_inner( int height, int width, int grid[height][width], int temp[height][width] )
{
  // pointer to v
  for( int i = 1; i < height - 1; i++)
    for ( int j = 1; j < width - 1; j++ )
      {

        int sum = grid[i-1][j-1]  + grid[i-1][j] + grid[i-1][j+1] + \
          grid[i][j-1] + grid[i][j+1] + \
          grid[i+1][j-1] + grid[i+1][j] + grid[i+1][j+1];

        if ( grid[i][j] == 0 )
          {
            // reproduction
            if (sum == 3)
              {
                temp[i][j] = 1;
              }
            else
              {
                temp[i][j] = 0;
              }
          }
        // alive
        else
          {
            // stays alive
            if (sum == 2 || sum == 3)
              {
                temp[i][j] = 1;
              }
            else
              {
                temp[i][j] = 0;
              }
          }

      }
  //copy temp to grid
  memcpy( &(grid[1][0]), &(temp[1][0]), sizeof(int) * (height - 2 ) * width );
}

void simulate(int height, int width, int grid[height][width], int num_iterations)
{
  /*
    grid: contiguous in memory: you can use it with MPI functions that expect contiguous memory (eg scatter) 
    height: includes padding rows i.e. real height is "height - 2"
    width:  includes padding colums i.e. real width is "width - 2"

    NOTE: You might need to redefine the "evolve" function.

    Your code only needs to work for (height - 2) > num_procs
   */

  int rank, num_procs;
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);
  MPI_Comm_size(MPI_COMM_WORLD, &num_procs);

  int real_height = height - 2;
  // equally distribute rows; + 2 for halo rows
  int local_rows = real_height / num_procs + ( rank < (real_height % num_procs ) ) + 2;

  // define local grid buffers - includes halo rows
  int (*local_grid)[width] = malloc(sizeof(int[local_rows][width]));

  // prepare arrays for Scatterv
  int *sendcounts = malloc(sizeof(int) * num_procs);
  int *displs = malloc(sizeof(int) * num_procs);

  int sum = 0;
  for (int i = 0; i < num_procs; i++)
    {
      int num_rows = (height - 2  ) / num_procs + (i < ( (height - 2) % num_procs ) );
      sendcounts[i] = num_rows * width;
      displs[i] = sum;
      sum += sendcounts[i];
    }

  //int MPI_Scatterv(const void *sendbuf, const int *sendcounts, const int *displs,
  //                  MPI_Datatype sendtype, void *recvbuf, int recvcount,
  //                  MPI_Datatype recvtype, int root, MPI_Comm comm)

  MPI_Scatterv( &(grid[1][0]), sendcounts, displs,
                MPI_INT, &(local_grid[1][0]), sendcounts[rank],
                MPI_INT, 0, MPI_COMM_WORLD);

  // define neighbouring ranks
  int top, bottom;

  top = rank - 1;
  bottom = rank + 1;

  if (rank == 0)
    {
      top = num_procs - 1;
    }
  if (rank == num_procs - 1)
    {
      bottom = 0;
    }

  // allocate space for top and bottom halo rows
  int *top_halo = malloc(sizeof(int[width]));
  int *bottom_halo = malloc(sizeof(int[width]));

  // temporary data storage
  int (*temp)[width] = malloc(sizeof(int[local_rows][width]));

  // In each iteration
  // 1. make local rows periodic
  // 2. fix halo rows
  // 3. send recv data
  // 4. compute

  for (int i = 0; i < num_iterations; i++)
    {

      // 1.
      make_local_rows_periodic(local_rows, width, local_grid);

      // 2.
      prepare_halo_rows(local_rows, width, local_grid, top_halo, bottom_halo);

      //int MPI_Sendrecv(const void *sendbuf, int sendcount, MPI_Datatype sendtype,
      //                 int dest, int sendtag,
      //                 void *recvbuf, int recvcount, MPI_Datatype recvtype,
      //                 int source, int recvtag,
      //                 MPI_Comm comm, MPI_Status *status)

      // 3. send to top process
      MPI_Sendrecv( top_halo, width, MPI_INT,
                    top, 0,
                    &(local_grid[local_rows-1][0]), width, MPI_INT,
                    bottom, 0,
                    MPI_COMM_WORLD, MPI_STATUS_IGNORE);

      // send to bottom process
      MPI_Sendrecv( bottom_halo, width, MPI_INT,
                    bottom, 0,
                    &(local_grid[0][0]), width, MPI_INT,
                    top, 0,
                    MPI_COMM_WORLD, MPI_STATUS_IGNORE);

      // 4.
      evolve_inner(local_rows, width, local_grid, temp);
      
      if (global_show_gui) gui_draw(local_rows, width, local_grid[0]);
    }


  // gather results back at root

  //MPI_Gatherv(const void *sendbuf, int sendcount, MPI_Datatype sendtype,
  //            void *recvbuf, const int *recvcounts, const int *displs,
  //            MPI_Datatype recvtype, int root, MPI_Comm comm)
  MPI_Gatherv( &(local_grid[1][0]), sendcounts[rank], MPI_INT,
               &(grid[1][0]), sendcounts, displs,
               MPI_INT, 0, MPI_COMM_WORLD );

  free(local_grid);
  free(temp);
  free(sendcounts);
  free(displs);
  free(top_halo);
  free(bottom_halo);
}


