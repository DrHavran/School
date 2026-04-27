#include "data.h"

#include <stdio.h>
#include <string.h>

void loadData() {
    FILE *f = fopen("data.csv","r");
    char line[1024];

    fgets(line, sizeof(line), f);
    while (fgets(line, sizeof(line), f)) {
        const char *token = strtok(line, ",");
        while (token) {
            printf("%s ", token);
            token = strtok(NULL, ",");
        }
    }

    fclose(f);
}

struct node {
    double x;
    double y;
    int category;
};