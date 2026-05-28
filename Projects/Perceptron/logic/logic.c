#include "logic.h"
#include "../data/arrayList.h"
#include "../data/data.h"
#include "../draw/draw.h"
#include "raylib.h"
#include "../data/perceptron.h"

void setUpPerceptron(char *method, const char *fileName) {
    const ArrayList list = loadData(fileName);

    drawWindow();
    Perceptron perceptron;
    initPerceptron(&perceptron);
    while (!WindowShouldClose()) {
        for (int i = 0; i < list.size; i++) {
            const struct node *n = list.data[i];
            const double value = perceptron.w1 * n->xLogic + perceptron.w2 * n->yLogic + perceptron.b * n->b;
            if (0 >= value) {
                updatePerceptron(&perceptron, n);
                break;
            }
        }
        drawAll(list, perceptron);
    }

    CloseWindow();
}