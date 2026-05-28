#ifndef PERCEPTOR_DRAW_H
#define PERCEPTOR_DRAW_H

#include "../data/arrayList.h"
#include "../data/perceptron.h"
#include "../data/node.h"

void drawWindow();
void drawAll(ArrayList list, Perceptron perceptron);
void drawNode(Node node);
void drawPerceptron(Perceptron perceptron);

#endif //PERCEPTOR_DRAW_H