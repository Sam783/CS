#include<stdio.h>
#include<stdlib.h>
#include<windows.h>

typedef struct Node* node;

struct Node{
    int data;
    int height;
    node left;
    node right;
};

node create(int data) {
    node newNode = (node)malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->height = 1;
    newNode->left = newNode->right = NULL;
    return newNode;
}

int max_h(int a, int b) {
    return a > b ? a : b;
}

int height(node root){
    return root == NULL ? 0 : root->height;
}

int balance(node root){
    return root == NULL ? 0 : height(root->left)-height(root->right);
}

node rightRotate(node x){
    node y = x->left;

    x->left = y->right;
    y->right = x;
    
    x->height = max_h(height(x->left), height(x->right)) + 1;
    y->height = max_h(height(y->left), height(y->right)) + 1;

    return y;
}

node leftRotate(node x){
    node y = x->right;

    x->right = y->left;
    y->left = x;
    
    x->height = max_h(height(x->left), height(x->right)) + 1;
    y->height = max_h(height(y->left), height(y->right)) + 1;

    return y;
}

node insertNode(node root, int val){
    if(root == NULL){
        return create(val);
    }

    if(root->data > val){
        root->left = insertNode(root->left, val);
    }
    else if (root->data < val){
        root->right = insertNode(root->right, val);
    }
    else{
        return root;
    }

    root->height = max_h(height(root->left), height(root->right)) + 1;
    int bf = balance(root);

    if(bf > 1 && val < root->left->data){ //LL
        return rightRotate(root);
    }

    if(bf < -1 && val > root->right->data){ //RR
        return leftRotate(root);
    }

    if(bf > 1 && val > root->left->data){ //LR
        root->left = leftRotate(root->left);
        return rightRotate(root);
    }

    if(bf < -1 && val < root->right->data){ //RL
        root->right = rightRotate(root->right);
        return leftRotate(root);
    }

    return root;
}

void preorder(node root){
    if(root == NULL){
        return;
    }
    printf("%d ",root->data);
    preorder(root->left);
    preorder(root->right);
}

void inorder(node root){
    if(root == NULL){
        return;
    }
    inorder(root->left);
    printf("%d ",root->data);
    inorder(root->right);
}

void postorder(node root){
    if(root == NULL){
        return;
    }
    postorder(root->left);
    postorder(root->right);
    printf("%d ", root->data);
}

int search(node root, int key){
    if(root == NULL){
        return 0;
    }
    if(root->data == key){
        return 1;
    }
    else if(root->data > key){
        return search(root->left,key);
    }
    else if(root->data < key){
        return search(root->right,key);
    }
    return 0;
}

node insertFromFile(const char* filename) {
    FILE* file = fopen(filename, "r");
    if (file == NULL) {
        printf("Could not open file");
        return NULL;
    }
    node root = NULL;
    int num;
    while (fscanf(file, "%d", &num) == 1) {
        root = insertNode(root, num);
    }
    fclose(file);
    return root;
}

void main() {
    const char* filename = "case1.txt";
    node root = insertFromFile(filename);

    printf("Inorder traversal: ");
    inorder(root);
    printf("\n");


    LARGE_INTEGER start, end, frequency;
    QueryPerformanceFrequency(&frequency);
    QueryPerformanceCounter(&start);

    if(search(root, 95)){
        printf("\nKey found");
    }else{
        printf("\nKey not found");
    }

    QueryPerformanceCounter(&end);
    double time_taken = (double)(end.QuadPart - start.QuadPart) * 1e6 / frequency.QuadPart;
    printf("\nTime taken for searching: %lf microseconds\n", time_taken);
}
