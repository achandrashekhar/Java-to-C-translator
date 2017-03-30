#include <stdio.h>
#include <stdlib.h>

typedef struct {
    char *name;
    int size;
    void (*(*_vtable)[])();
} metadata;

typedef struct {
    metadata *clazz;
} object;

object *alloc(metadata *clazz) {
    object *p = calloc(1, clazz->size); // wipe to 0s
    p->clazz = clazz;
    return p;
}


int main(int argc, char *argv[])
{
    int x;

    x = 0;
    if ( x ) printf("x is 1\n");
    else printf("x is 2\n");
    return 0;
}