#define STB_DS_IMPLEMENTATION

#include <stdio.h>

#include "raylib.h"
#include "../data/data.h"
#include "../data/ArrayList.h"
#include "../draw/draw.h"

int main(void) {
    const ArrayList list = loadData();

    for (int i = 0; i < list.size; i++) {
        const struct node *n = list.data[i];
        printf("Node %d: x = %.2f, y = %.2f, category = %d\n",
               i, n->x, n->y, n->category);
    }

    drawWindow();
    const Line line;
    initLine(&line);
    while (!WindowShouldClose()) {
        ClearBackground(RAYWHITE);
        BeginDrawing();
        for (int i = 0; i < list.size; i++) {
            const struct node *n = list.data[i];
            drawNode(*n);
        }
        drawLine(line);
        EndDrawing();
    }

    CloseWindow();
    return 0;
}

void modifyLine(Node node, Line line) {

}