cmake_minimum_required(VERSION 4.2)
project(Perceptor C)

set(CMAKE_C_STANDARD 11)

add_executable(Perceptor
        main.c
        data.c
        data.h
        node.h
)