#ifndef PERCEPTRON_PERCEPTRON_H
#define PERCEPTRON_PERCEPTRON_H

#include "node.h"
#include "raylib.h"

typedef struct Perceptron {
    double w1, w2, b;
} Perceptron;

typedef struct Vector2Pair {
    Vector2 start;
    Vector2 end;
} Vector2Pair;

void initPerceptron(Perceptron *perceptron);
void updatePerceptron(Perceptron *perceptron, const Node *n);
Vector2Pair createLineFromPerceptron(const Perceptron *perceptron);

#endif //PERCEPTRON_PERCEPTRON_H