#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

struct Node {
    int data;
    char color;
    struct Node *left, *right, *parent;
};

typedef struct Node* Node;
Node root = NULL;

Node create_node(int data) {
    Node new_node = (Node)malloc(sizeof(struct Node));
    new_node->data = data;
    new_node->color = 'R';
    new_node->left = new_node->right = new_node->parent = NULL;
    return new_node;
}

void rotate_left(Node node) {
    Node right_child = node->right;
    node->right = right_child->left;

    if (right_child->left != NULL) {
        right_child->left->parent = node;
    }

    right_child->parent = node->parent;

    if (node->parent == NULL) {
        root = right_child;
    } else if (node == node->parent->left) {
        node->parent->left = right_child;
    } else {
        node->parent->right = right_child;
    }

    right_child->left = node;
    node->parent = right_child;
}

void rotate_right(Node node) {
    Node left_child = node->left;
    node->left = left_child->right;

    if (left_child->right != NULL) {
        left_child->right->parent = node;
    }

    left_child->parent = node->parent;

    if (node->parent == NULL) {
        root = left_child;
    } else if (node == node->parent->right) {
        node->parent->right = left_child;
    } else {
        node->parent->left = left_child;
    }

    left_child->right = node;
    node->parent = left_child;
}

void fix_insertion(Node node) {
    while (node != root && node->parent->color == 'R') {
        Node parent = node->parent;
        Node grandparent = parent->parent;

        if (parent == grandparent->left) {
            Node uncle = grandparent->right;

            if (uncle != NULL && uncle->color == 'R') {
                parent->color = 'B';
                uncle->color = 'B';
                grandparent->color = 'R';
                node = grandparent;
            } else {
                if (node == parent->right) {
                    rotate_left(parent);
                    node = parent;
                    parent = node->parent;
                }

                parent->color = 'B';
                grandparent->color = 'R';
                rotate_right(grandparent);
            }
        } else {
            Node uncle = grandparent->left;

            if (uncle != NULL && uncle->color == 'R') {
                parent->color = 'B';
                uncle->color = 'B';
                grandparent->color = 'R';
                node = grandparent;
            } else {
                if (node == parent->left) {
                    rotate_right(parent);
                    node = parent;
                    parent = node->parent;
                }

                parent->color = 'B';
                grandparent->color = 'R';
                rotate_left(grandparent);
            }
        }
    }
    root->color = 'B';
}

void insert(int data) {
    Node new_node = create_node(data);

    if (root == NULL) {
        root = new_node;
        root->color = 'B';
        return;
    }

    Node curr = root;
    Node parent = NULL;

    while (curr != NULL) {
        parent = curr;
        if (data < curr->data) {
            curr = curr->left;
        } else if (data > curr->data) {
            curr = curr->right;
        } else {
            free(new_node);
            return;
        }
    }

    if (data < parent->data) {
        parent->left = new_node;
    } else {
        parent->right = new_node;
    }
    new_node->parent = parent;

    fix_insertion(new_node);
}

void transplant(Node u, Node v) {
    if (u->parent == NULL) {
        root = v;
    } else if (u == u->parent->left) {
        u->parent->left = v;
    } else {
        u->parent->right = v;
    }
    if (v != NULL) {
        v->parent = u->parent;
    }
}

Node find_minimum(Node node) {
    while (node->left != NULL) {
        node = node->left;
    }
    return node;
}

Node find_node(Node root, int data) {
    while (root != NULL) {
        if (data < root->data) {
            root = root->left;
        } else if (data > root->data) {
            root = root->right;
        } else {
            return root;
        }
    }
    return NULL;
}

void fix_deletion(Node node) {
    while (node != root && (node == NULL || node->color == 'B')) {
        Node parent = node == NULL ? root : node->parent;
        Node sibling;

        if (node == parent->left) {
            sibling = parent->right;

            if (sibling->color == 'R') {
                sibling->color = 'B';
                parent->color = 'R';
                rotate_left(parent);
                sibling = parent->right;
            }

            if ((sibling->left == NULL || sibling->left->color == 'B') &&
                (sibling->right == NULL || sibling->right->color == 'B')) {
                sibling->color = 'R';
                node = parent;
            } else {
                if (sibling->right == NULL || sibling->right->color == 'B') {
                    if (sibling->left != NULL) sibling->left->color = 'B';
                    sibling->color = 'R';
                    rotate_right(sibling);
                    sibling = parent->right;
                }

                sibling->color = parent->color;
                parent->color = 'B';
                if (sibling->right != NULL) sibling->right->color = 'B';
                rotate_left(parent);
                node = root;
            }
        } else {
            sibling = parent->left;

            if (sibling->color == 'R') {
                sibling->color = 'B';
                parent->color = 'R';
                rotate_right(parent);
                sibling = parent->left;
            }

            if ((sibling->right == NULL || sibling->right->color == 'B') &&
                (sibling->left == NULL || sibling->left->color == 'B')) {
                sibling->color = 'R';
                node = parent;
            } else {
                if (sibling->left == NULL || sibling->left->color == 'B') {
                    if (sibling->right != NULL) sibling->right->color = 'B';
                    sibling->color = 'R';
                    rotate_left(sibling);
                    sibling = parent->left;
                }

                sibling->color = parent->color;
                parent->color = 'B';
                if (sibling->left != NULL) sibling->left->color = 'B';
                rotate_right(parent);
                node = root;
            }
        }
    }

    if (node != NULL) {
        node->color = 'B';
    }
}

void delete_node(int data) {
    Node node = find_node(root, data);
    if (node == NULL) return;

    Node replacement = node;
    char original_color = replacement->color;
    Node child;

    if (node->left == NULL) {
        child = node->right;
        transplant(node, node->right);
    } else if (node->right == NULL) {
        child = node->left;
        transplant(node, node->left);
    } else {
        replacement = find_minimum(node->right);
        original_color = replacement->color;
        child = replacement->right;

        if (replacement->parent == node) {
            if (child != NULL) child->parent = replacement;
        } else {
            transplant(replacement, replacement->right);
            replacement->right = node->right;
            if (replacement->right != NULL) replacement->right->parent = replacement;
        }

        transplant(node, replacement);
        replacement->left = node->left;
        if (replacement->left != NULL) replacement->left->parent = replacement;
        replacement->color = node->color;
    }

    if (original_color == 'B') {
        fix_deletion(child);
    }

    free(node);
}

void inorder_traversal(Node node) {
    if (node == NULL) return;

    inorder_traversal(node->left);
    printf("%d(%c) ", node->data, node->color);
    inorder_traversal(node->right);
}

void insertFromFile(const char* filename) {
    FILE* file = fopen(filename, "r");
    if (file == NULL) {
        printf("Could not open file %s\n", filename);
        return;
    }

    int num;
    while (fscanf(file, "%d", &num) == 1) {
        insert(num);
    }
    fclose(file);
}

int main() {
    struct timespec start, end;
    double time_taken;

    const char* filename = "case3b.txt";

    clock_gettime(CLOCK_MONOTONIC, &start);
    insertFromFile(filename);
    clock_gettime(CLOCK_MONOTONIC, &end);
    time_taken = (end.tv_sec - start.tv_sec) * 1e6 + (end.tv_nsec - start.tv_nsec) / 1e3;
    printf("\nIn-order Traversal: ");
    inorder_traversal(root);
    printf("\nTime taken for insertion: %lf microseconds", time_taken);

    clock_gettime(CLOCK_MONOTONIC, &start);
    if (find_node(root, 25)) {
        printf("\nKey found");
    } else {
        printf("\nKey not found");
    }
    clock_gettime(CLOCK_MONOTONIC, &end);
    time_taken = (end.tv_sec - start.tv_sec) * 1e6 + (end.tv_nsec - start.tv_nsec) / 1e3;
    printf("\nTime taken for searching: %lf microseconds", time_taken);

    clock_gettime(CLOCK_MONOTONIC, &start);
    delete_node(25);
    clock_gettime(CLOCK_MONOTONIC, &end);
    time_taken = (end.tv_sec - start.tv_sec) * 1e6 + (end.tv_nsec - start.tv_nsec) / 1e3;
    printf("\nTime taken for deletion: %lf microseconds\n", time_taken);

    return 0;
}