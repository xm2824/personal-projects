All: server client test
CC = g++
LIBS = -lrt
FLAGS =-pthread
server: common.cpp common.hpp shared.hpp  shared.cpp ListEntry.hpp ListEntry.cpp hashtable.hpp hashtable.cpp server.cpp
	$(CC) -o $@ $^ $(FLAGS) $(LIBS)


client: common.cpp common.hpp shared.hpp  shared.cpp ListEntry.hpp ListEntry.cpp hashtable.hpp hashtable.cpp client.cpp
	$(CC) -o $@  $^ $(FLAGS) $(LIBS)

test: common.cpp common.hpp shared.hpp  shared.cpp ListEntry.hpp ListEntry.cpp hashtable.hpp hashtable.cpp test.cpp
	$(CC) -o $@  $^ $(FLAGS) $(LIBS)
