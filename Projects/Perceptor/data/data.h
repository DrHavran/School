#ifndef DATA_H
#define DATA_H
#include "ArrayList.h"
#include "raylib.h"

typedef struct node {
    double x, y, yLogic, xLogic;
    int b;
    int category;
} Node;

typedef struct line {
    double w1, w2, b;
    Vector2 start;
    Vector2 end;
} Line;

ArrayList loadData(const char *fileName);
void initLine(Line *line);
void changeLine(Line *line);
void updateLineFromWeights(Line *line);
void updateMinMax(double x, double y);

#endif