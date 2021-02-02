cmake_minimum_required(VERSION 3.17)
project(server_client)

set(CMAKE_CXX_STANDARD 14)

add_executable(server_client main.cpp server.cpp server.h client.cpp server.c)