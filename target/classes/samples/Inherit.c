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



// D e f i n e  C l a s s  Animal
typedef struct {
    metadata *clazz;
    int ID;
} Animal;

#define Animal_getID_SLOT 0
#define Animal_speak_SLOT 1


int Animal_getID(Animal *this)
{
    return this->ID;
}
void Animal_speak(Animal *this)
{
    printf("blort!\n");
}

void (*Animal_vtable[])() = {
    (void (*)())&Animal_getID,
    (void (*)())&Animal_speak
};

metadata Animal_metadata = {"Animal", sizeof(Animal), &Animal_vtable};

// D e f i n e  C l a s s  Dog
typedef struct {
    metadata *clazz;
    int ID;
} Dog;

#define Dog_getID_SLOT 0
#define Dog_speak_SLOT 1


void Dog_speak(Dog *this)
{
    printf("woof!\n");
}

void (*Dog_vtable[])() = {
    (void (*)())&Animal_getID,
    (void (*)())&Dog_speak
};

metadata Dog_metadata = {"Dog", sizeof(Dog), &Dog_vtable};

int main(int argc, char *argv[])
{
    Animal * a;
    Dog * d;

    a = ((Animal *)alloc(&Animal_metadata));
    (*(void (*)(Animal *))(*(a)->clazz->_vtable)[Animal_speak_SLOT])(((Animal *)a));
    a->ID = 4;
    printf("%d\n", (*(int (*)(Animal *))(*(a)->clazz->_vtable)[Animal_getID_SLOT])(((Animal *)a)));
    d = ((Dog *)alloc(&Dog_metadata));
    d->ID = 5;
    (*(void (*)(Dog *))(*(d)->clazz->_vtable)[Dog_speak_SLOT])(((Dog *)d));
    printf("%d\n", (*(int (*)(Animal *))(*(d)->clazz->_vtable)[Dog_getID_SLOT])(((Animal *)d)));
    printf("%d\n", d->ID);
    return 0;
}