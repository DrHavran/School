cmake_minimum_required(VERSION 4.2)
project(Perceptor C)

set(CMAKE_C_STANDARD 11)

add_executable(Perceptor
        logic/main.c
        data/data.c
        data/data.h
        import/stb_ds.h
        logic/draw.c
        logic/draw.h
)
