#include "draw.h"
#include "raylib.h"
#include "../data/ArrayList.h"
#include "../data/data.h"

void drawWindow() {
    const int screenWidth = 800;
    const int screenHeight = 450;

    InitWindow(screenWidth, screenHeight, "Perceptor");
    SetTargetFPS(60);
}
void drawNodes(const ArrayList list) {
    ClearBackground(RAYWHITE);
    BeginDrawing();
    for (int i = 0; i < list.size; i++) {
        const struct node *n = list.data[i];
        Color color;
        if (n->category == 1) {
            color = RED;
        }else {
            color = BLUE;
        }
        DrawCircle(
            n->x * GetScreenWidth(),
            (1 - n->y) * GetScreenHeight(),
            3, color);
    }
    EndDrawing();
}
void drawLine(void *data) {
    ClearBackground(RAYWHITE);
    BeginDrawing();
    EndDrawing();
}