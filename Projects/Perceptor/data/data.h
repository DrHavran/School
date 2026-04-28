#ifndef DATA_H
#define DATA_H
#include "ArrayList.h"

struct node {
    double x;
    double y;
    int category;
};

ArrayList loadData();

#endif