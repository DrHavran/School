#ifndef DATA_H
#define DATA_H
#include "ArrayList.h"
#include "raylib.h"

typedef struct node {
    double x;
    double y;
    int category;
} Node;

typedef struct line {
    Vector2 start;
    Vector2 end;
} Line;

ArrayList loadData();
void initLine(Line *line);

#endif