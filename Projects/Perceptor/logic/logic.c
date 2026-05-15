#include "logic.h"
#include "../data/ArrayList.h"
#include "../draw/draw.h"
#include "raylib.h"

void setUpPerceptron(char *method, char *fileName) {
    const ArrayList list = loadData(fileName);

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
}

void updateLine(Line *line, const Node *n) {
    line->w1 = line->w1 + n->xLogic;
    line->w2 = line->w2 + n->yLogic;
    line->b = line->b + n->b;
    updateLineFromWeights(line);
}