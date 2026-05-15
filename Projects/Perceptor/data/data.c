#include "../data/data.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

double xMin, xMax, yMin, yMax;

ArrayList loadData(const char *fileName) {
    ArrayList list;
    al_init(&list);

    char path[256];
    snprintf(path, sizeof(path), "../import_data/%s", fileName);
    FILE *f = fopen(path, "r");

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

        n->category = category;
        n->x = x;
        n->y = y;
        n->b = category;
        n->xLogic = x;
        n->yLogic = y;
        if (category == 1) {
            n->xLogic = x;
            n->yLogic = y;
            n->b = 1;
        }else {
            n->xLogic = -x;
            n->yLogic = -y;
            n->b = -1;
        }
        updateMinMax(x, y);
        al_add(&list, n);
    }
    fclose(f);
    return list;
}

void initLine(Line *line) {
    line->w1 = 0;
    line->w2 = 0;
    line->b = 0;
    line->start = (Vector2){ 0, (float)GetScreenHeight()/2 };
    line->end   = (Vector2){ (float)GetScreenWidth(), (float)GetScreenHeight()/2 };
}

// GPT code to convert line points into vectors, too lazy to write it myself :)
void updateLineFromWeights(Line *line) {

    // Check if w2 is very close to zero (vertical line)
    if (line->w2 > -1e-8 && line->w2 < 1e-8) {
        double x_const = -line->b / line->w1;
        if (x_const < xMin) x_const = xMin;
        if (x_const > xMax) x_const = xMax;
        line->start.x = x_const * GetScreenWidth();
        line->start.y = (1.0 - yMin) * GetScreenHeight();
        line->end.x   = x_const * GetScreenWidth();
        line->end.y   = (1.0 - yMax) * GetScreenHeight();
        return;
    }

    double y0 = -(line->w1 * xMin + line->b) / line->w2;
    double y1 = -(line->w1 * xMax + line->b) / line->w2;

    if (y0 < yMin) y0 = yMin;
    if (y0 > yMax) y0 = yMax;
    if (y1 < yMin) y1 = yMin;
    if (y1 > yMax) y1 = yMax;

    line->start.x = xMin * GetScreenWidth();
    line->start.y = (1.0 - y0) * GetScreenHeight();
    line->end.x   = xMax * GetScreenWidth();
    line->end.y   = (1.0 - y1) * GetScreenHeight();
}
void updateMinMax(const double x, const double y) {
    if (x < xMin) xMin = x;
    if (x > xMax) xMax = x;
    if (y < yMin) yMin = y;
    if (y > yMax) yMax = y;
}