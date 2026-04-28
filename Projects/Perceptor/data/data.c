#include "../data/data.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

ArrayList loadData() {
    ArrayList list;
    al_init(&list);
    FILE *f = fopen("data.csv", "r");

    char line[1024];

    fgets(line, sizeof(line), f);

    while (fgets(line, sizeof(line), f)) {
        const char *token = strtok(line, ",");
        const double x = atof(token);

        token = strtok(NULL, ",");
        const double y = atof(token);

        token = strtok(NULL, ",");
        const int category = atoi(token);

        struct node *n = malloc(sizeof(struct node));
        n->x = x;
        n->y = y;
        n->category = category;

        al_add(&list, n);
    }
    fclose(f);
    return list;
}

void initLine(Line *line) {
    line->start = (Vector2){ 0, (float)GetScreenHeight()/2 };
    line->end   = (Vector2){ (float)GetScreenWidth(), (float)GetScreenHeight()/2 };
}