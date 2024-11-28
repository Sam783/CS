#include<stdio.h>
#include<stdlib.h>
#include <windows.h>
// #include<time.h>

typedef struct Node* node;

struct Node {
    int data;
    node left;
    node right;
};

node createNode(int data){
    node newNode = (node)malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->left = newNode->right = NULL;
    return newNode;
}

node insert(node root, int data){
    if(root == NULL){
        return createNode(data);
    }
    if(data < root->data){
        root->left = insert(root->left,data);
    }
    else if(data > root->data){
        root->right = insert(root->right,data);
    }
    return root;
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

void inorder(node root){
    if(root == NULL){
        return;
    }
    inorder(root->left);
    printf("%d ", root->data);
    inorder(root->right);
}

int minValue(node root){
    while(root->left != NULL){
        root = root->left;
    }
    return root->data;
}

node delete(node root, int val){
    if(root->data > val){
        root->left = delete(root->left, val);
    }else if(root->data < val){
        root->right = delete(root->right, val);
    }else{
        if(root->left == NULL && root->right == NULL){
            return NULL;
        }

        if(root->left == NULL){
            return root->right;
        }
        else if(root->right == NULL){
            return root->left;
        }
        root->data = minValue(root->right);
        root->right = delete(root->right, root->data); 
    }
    return root;
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
        root = insert(root, num);
    }
    fclose(file);
    return root;
}

int main() {
    const char* filename = "case3b.txt";
    node root = insertFromFile(filename);

    printf("Inorder Traversal: ");
    inorder(root);

    int key = 25;
    LARGE_INTEGER start, end, frequency;

    QueryPerformanceFrequency(&frequency);

    QueryPerformanceCounter(&start);
    int found = search(root, key);
    QueryPerformanceCounter(&end);

    if (found) {
        printf("\nKey found");
    } else {
        printf("\nKey not found");
    }
    
    double time_taken = (double)(end.QuadPart - start.QuadPart) * 1e6 / frequency.QuadPart;
    printf("\nTime taken for searching: %lf microseconds\n", time_taken);

    // delete(root,25);
    // inorder(root);

    return 0;
}

// int main() {
//      const char* filename = "case1.txt";
//      node root = insertFromFile(filename);

//      printf("Inorder Traversal: ");
//      inorder(root);

//      int key = 25;

//      struct timespec start, end;

//      // Get start time
//      clock_gettime(CLOCK_MONOTONIC, &start);

//      // Perform search
//      int found = search(root, key);

//      // Get end time
//      clock_gettime(CLOCK_MONOTONIC, &end);

//      if (found) {
//          printf("\nKey found");
//      } else {
//          printf("\nKey not found");
//      }

//      double time_taken = (end.tv_sec - start.tv_sec) * 1e6 + (end.tv_nsec - start.tv_nsec) / 1e3;
//      printf("\nTime taken for searching: %lf microseconds\n", time_taken);

//      return 0;
// }
