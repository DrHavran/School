#include "draw.h"
#include "raylib.h"
#include "../data/data.h"

void drawWindow() {
    const int screenWidth = 800;
    const int screenHeight = 450;

    InitWindow(screenWidth, screenHeight, "Perceptor");
    SetTargetFPS(60);
}
void drawAll(const ArrayList list, const Line line) {
    ClearBackground(RAYWHITE);
    BeginDrawing();
    for (int i = 0; i < list.size; i++) {
        const struct node *n = list.data[i];
        drawNode(*n);
    }
    drawLine(line);
    EndDrawing();
}
void drawNode(const Node n) {
    Color color;
    if (n.category == 1) {
        color = RED;
    }else {
        color = BLUE;
    }
    DrawCircle(
        n.x * GetScreenWidth(),
        (1 - n.y) * GetScreenHeight(),
        3, color);
}
void drawLine(const Line line) {
    DrawLineV(line.start, line.end, BLACK);
}