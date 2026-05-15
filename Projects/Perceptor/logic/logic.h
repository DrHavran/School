#ifndef PERCEPTOR_LOGIC_H
#define PERCEPTOR_LOGIC_H

#include "../draw/draw.h"

void setUpPerceptron(char *method, char *fileName);
void updateLine(Line *line, const Node *n);
void updateLineFromWeights(Line *line);

#endif //PERCEPTOR_LOGIC_H