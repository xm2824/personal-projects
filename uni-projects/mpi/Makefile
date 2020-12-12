all: mpi_test

mpi_test: main.cpp
	mpicxx -o $@ $^
	
.PHONY: clean

clean:
	rm -f mpi_test
