#ifndef DATA_H
#define DATA_H

struct node {
    double x;
    double y;
    int category;
};

extern struct node *nodes;

void loadData();

#endif