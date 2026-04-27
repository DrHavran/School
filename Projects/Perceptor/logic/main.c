#define STB_DS_IMPLEMENTATION

#include <stdio.h>
#include "../import/stb_ds.h"
#include "../data/data.h"

int main(void) {
    loadData();

    printf("Loaded %zu nodes\n", arrlen(nodes));
    for (size_t i = 0; i < arrlen(nodes); i++) {
        printf("Node %zu: x=%.2f, y=%.2f, cat=%d\n",
               i, nodes[i].x, nodes[i].y, nodes[i].category);
    }

    return 0;
}