#ifndef PERCEPTOR_ARRAYLIST_H
#define PERCEPTOR_ARRAYLIST_H

typedef struct ArrayList {
    void **data;
    int size;
} ArrayList;

void al_init(ArrayList *list);
void al_add(ArrayList *list, void *data);

#endif //PERCEPTOR_ARRAYLIST_H