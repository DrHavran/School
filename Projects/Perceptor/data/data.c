#include "../data/data.h"
#include "../import/stb_ds.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Define the global pointer
struct node *nodes = NULL;

void loadData() {
    FILE *f = fopen("data.csv", "r");

    char line[1024];

    fgets(line, sizeof(line), f);

    while (fgets(line, sizeof(line), f)) {
        char *token = strtok(line, ",");
        double x = atof(token);

        token = strtok(NULL, ",");
        double y = atof(token);

        token = strtok(NULL, ",");
        int category = atoi(token);

        struct node n = {x, y, category};
        arrput(nodes, n);
    }
    fclose(f);
}