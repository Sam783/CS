#include<stdio.h>
#include<stdlib.h>
//#include <windows.h>
#include<time.h>
struct Node {
    int data;
    struct Node* left;
    struct Node* right;
};

typedef struct Node* myNode;

myNode createNode(int data){
    myNode node = (struct Node*)malloc(sizeof(struct Node));
    node->data = data;
    node->left = node->right = NULL;
    return node;
}

myNode insert(myNode root, int data){
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

int search(myNode root, int key){
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

void inorder(myNode root){
    if(root == NULL){
        return;
    }
    inorder(root->left);
    printf("%d ", root->data);
    inorder(root->right);
}

int getInorderSuccessor(myNode root){
    if(root->left==NULL)
        return root->data;
    else
        return getInorderSuccessor(root->left);
}

myNode deleteNode(myNode root,int key){
    if(root==NULL)
    {
        printf("Key Element %d not found\n",key);
        return root;
    }
    else if(root->data==key)
    {
        if(root->left==NULL && root->right==NULL)
        {
            free(root);
            return NULL;
        }
        else if(root->left==NULL){
            myNode temp=root->right;
            free(root);
            return temp;
        }
        else if(root->right==NULL)
        {
            myNode temp=root->left;
            free(root);
            return temp;
        }
        else{
            root->data=getInorderSuccessor(root->right);
            root->right=deleteNode(root->right,root->data);
        }
    }
    else if(key<root->data)
    {
        root->left=deleteNode(root->left,key);
    }
    else{
        root->right=deleteNode(root->right,key);
    }
    return root;
}

myNode insertFromFile(const char* filename) {
    FILE* file = fopen(filename, "r");
    if (file == NULL) {
        printf("Could not open file");
        return NULL;
    }
    struct Node* root = NULL;
    int num;
    while (fscanf(file, "%d", &num) == 1) {
        root = insert(root, num);
    }
    fclose(file);
    return root;
}

int main() {
     const char* filename = "case3b.txt";
     struct timespec start, end;
	 double time_taken;
     
     clock_gettime(CLOCK_MONOTONIC, &start);
     struct Node* root = insertFromFile(filename);
     clock_gettime(CLOCK_MONOTONIC, &end);
     time_taken = (end.tv_sec - start.tv_sec) * 1e6 + (end.tv_nsec - start.tv_nsec) / 1e3;
     
     printf("Inorder Traversal: ");
     inorder(root);
     
     

     int key = 25;

     printf("\nTime taken for insertion: %lf microseconds", time_taken);

     clock_gettime(CLOCK_MONOTONIC, &start);

     int found = search(root, key);

     clock_gettime(CLOCK_MONOTONIC, &end);

     
     if (found) {
         printf("\nKey found");
     } else {
         printf("\nKey not found");
     }

     time_taken = (end.tv_sec - start.tv_sec) * 1e6 + (end.tv_nsec - start.tv_nsec) / 1e3;
     printf("\nTime taken for searching: %lf microseconds\n", time_taken);
	 
	 clock_gettime(CLOCK_MONOTONIC, &start);
	 deleteNode(root,5);
	 clock_gettime(CLOCK_MONOTONIC, &end);
	 
	 time_taken = (end.tv_sec - start.tv_sec) * 1e6 + (end.tv_nsec - start.tv_nsec) / 1e3;
     printf("Time taken for deletion: %lf microseconds\n", time_taken);
     
     return 0;
}
