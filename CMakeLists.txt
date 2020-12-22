cmake_minimum_required(VERSION 3.17)
project(personal_projects)

set(CMAKE_CXX_STANDARD 14)

include_directories(mini-projects/c&c++/homework_reminder)
include_directories(uni-projects/EdgeDetection-team181/Implementierung/src/c)
include_directories(uni-projects/mpi/assignment8)
include_directories(uni-projects/mpi/assignment9)

add_executable(personal_projects
        "leetcode/387. First Unique Character in a String.cpp"
        mini-projects/c&c++/homework_reminder/helper.h
        mini-projects/c&c++/homework_reminder/impl.cpp
        mini-projects/c&c++/OOP/string.cpp
        uni-projects/EdgeDetection-team181/Implementierung/src/c/common.c
        uni-projects/EdgeDetection-team181/Implementierung/src/c/common.h
        uni-projects/EdgeDetection-team181/Implementierung/src/c/io.c
        uni-projects/EdgeDetection-team181/Implementierung/src/c/io.h
        uni-projects/EdgeDetection-team181/Implementierung/src/c/laplace.h
        uni-projects/EdgeDetection-team181/Implementierung/src/c/main.c
        uni-projects/EdgeDetection-team181/Implementierung/src/c/test.c)
