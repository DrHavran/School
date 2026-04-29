#define STB_DS_IMPLEMENTATION

#include <stdio.h>
#include "raylib.h"
#include "../data/data.h"
#include "../data/ArrayList.h"
#include "../draw/draw.h"

void updateLine(Line *line, const Node *n) {
    line->w1 = line->w1 + n->xLogic;
    line->w2 = line->w2 + n->yLogic;
    line->b = line->b + n->b;
    updateLineFromWeights(line);
}

int main(void) {
    const ArrayList list = loadData();

    drawWindow();
    const Line line;
    initLine(&line);
    while (!WindowShouldClose()) {
        for (int i = 0; i < list.size; i++) {
            const struct node *n = list.data[i];
            const double value = line.w1 * n->xLogic + line.w2 * n->yLogic + line.b * n->b;
            if (0 >= value) {
                updateLine(&line, n);
                break;
            }
        }
        drawAll(list, line);
    }

    CloseWindow();
    return 0;
}