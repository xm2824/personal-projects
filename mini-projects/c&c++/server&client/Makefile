All: server client
CC = g++
LIBS = -lrt
FLAGS =-pthread
server: common.cpp common.hpp shared.hpp  server.cpp
	$(CC) -o $@ $^  $(LIBS)


client: common.cpp common.hpp shared.hpp  client.cpp
	$(CC) -o $@  $^ $(FLAGS) $(LIBS)