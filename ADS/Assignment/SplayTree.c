#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct Node* node;

struct Node {
    int key;
    struct Node *left, *right, *parent;
};

node root = NULL;

node createNode(int key) {
    node newNode = (node)malloc(sizeof(struct Node));
    newNode->key = key;
    newNode->left = newNode->right = newNode->parent = NULL;
    return newNode;
}

void rotateRight(node x) {
    node y = x->left;
    if (y) {
        x->left = y->right;
        if (y->right) y->right->parent = x;
        y->parent = x->parent;
    }
    if (!x->parent) {
        root = y;
    } else if (x == x->parent->left) {
        x->parent->left = y;
    } else {
        x->parent->right = y;
    }
    if (y) y->right = x;
    x->parent = y;
}

void rotateLeft(node x) {
    node y = x->right;
    if (y) {
        x->right = y->left;
        if (y->left) y->left->parent = x;
        y->parent = x->parent;
    }
    if (!x->parent) {
        root = y;
    } else if (x == x->parent->left) {
        x->parent->left = y;
    } else {
        x->parent->right = y;
    }
    if (y) y->left = x;
    x->parent = y;
}

void splay(node x) {
    while (x->parent) {
        if (!x->parent->parent) {
            if (x == x->parent->left) {
                rotateRight(x->parent);
            } else {
                rotateLeft(x->parent);
            }
        } else if (x == x->parent->left && x->parent == x->parent->parent->left) {
            rotateRight(x->parent->parent);
            rotateRight(x->parent);
        } else if (x == x->parent->right && x->parent == x->parent->parent->right) {
            rotateLeft(x->parent->parent);
            rotateLeft(x->parent);
        } else if (x == x->parent->right && x->parent == x->parent->parent->left) {
            rotateLeft(x->parent);
            rotateRight(x->parent);
        } else {
            rotateRight(x->parent);
            rotateLeft(x->parent);
        }
    }
}

node search(int key) {
    node currentNode = root;
    while (currentNode) {
        if (key < currentNode->key) {
            currentNode = currentNode->left;
        } else if (key > currentNode->key) {
            currentNode = currentNode->right;
        } else {
            splay(currentNode);
            return currentNode;
        }
    }
    return NULL;
}

void insert(int key) {
    node currentNode = root, parent = NULL;
    while (currentNode) {
        parent = currentNode;
        if (key < currentNode->key) {
            currentNode = currentNode->left;
        } else if (key > currentNode->key) {
            currentNode = currentNode->right;
        } else {
            splay(currentNode);
            return;
        }
    }
    node newNode = createNode(key);
    newNode->parent = parent;

    if (!parent) {
        root = newNode;
    } else if (key < parent->key) {
        parent->left = newNode;
    } else {
        parent->right = newNode;
    }
    splay(newNode);
}

void delete(int key) {
    node currentNode = search(key);
    if (!currentNode) return;

    splay(currentNode);

    if (currentNode->left) {
        node leftSubtree = currentNode->left;
        leftSubtree->parent = NULL;

        node maxNode = leftSubtree;
        while (maxNode->right) {
            maxNode = maxNode->right;
        }

        splay(maxNode);
        maxNode->right = currentNode->right;
        if (currentNode->right) {
            currentNode->right->parent = maxNode;
        }
        root = maxNode;
    } else if (currentNode->right) {
        root = currentNode->right;
        root->parent = NULL;
    } else {
        root = NULL;
    }
    free(currentNode);
}

void inOrder(node currentNode) {
    if (currentNode) {
        inOrder(currentNode->left);
        printf("%d ", currentNode->key);
        inOrder(currentNode->right);
    }
}

void insertFromFile(const char* filename) {
    FILE* file = fopen(filename, "r");
    if (file == NULL) {
        printf("Could not open file\n");
        return;
    }
    int num;
    while (fscanf(file, "%d", &num) == 1) {
        insert(num);
    }
    fclose(file);
}

void main() {
    const char* filename = "case3b.txt";
    struct timespec start, end;
    double time_taken;

    clock_gettime(CLOCK_MONOTONIC, &start);
    insertFromFile(filename);
    clock_gettime(CLOCK_MONOTONIC, &end);

    time_taken = (end.tv_sec - start.tv_sec) * 1e6 + (end.tv_nsec - start.tv_nsec) / 1e3;
    printf("Inorder Traversal: ");
    inOrder(root);
    printf("\n");

    printf("\nTime taken for insertion: %lf microseconds\n", time_taken);

    clock_gettime(CLOCK_MONOTONIC, &start);
    if (search(25)) {
        printf("\nKey found\n");
    } else {
        printf("\nKey not found\n");
    }
    clock_gettime(CLOCK_MONOTONIC, &end);

    time_taken = (end.tv_sec - start.tv_sec) * 1e6 + (end.tv_nsec - start.tv_nsec) / 1e3;
    printf("Time taken for searching: %lf microseconds\n", time_taken);

    clock_gettime(CLOCK_MONOTONIC, &start);
    delete(25);
    clock_gettime(CLOCK_MONOTONIC, &end);

    time_taken = (end.tv_sec - start.tv_sec) * 1e6 + (end.tv_nsec - start.tv_nsec) / 1e3;
    printf("Time taken for deletion: %lf microseconds\n", time_taken);

}
