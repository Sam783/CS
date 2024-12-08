import java.util.LinkedList;
import java.util.Queue;

class RedBlackTree {
    static Node root;

    static class Node {
        int data;
        String color;
        Node left, right, parent;

        Node(int data) {
            this.data = data;
            this.color = "red";
            this.left = this.right = this.parent = null;
        }
    }

    public static void insert(int data) {
        Node newNode = new Node(data);

        if (root == null) {
            root = newNode;
            root.color = "black";
            return;
        }

        Node curr = root;
        Node parent = null;

        while (curr != null) {
            parent = curr;
            if (data < curr.data) {
                curr = curr.left;
            } else if (data > curr.data) {
                curr = curr.right;
            } else {
                return;
            }
        }

        if (data < parent.data) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        newNode.parent = parent;

        fixInsertion(newNode);
    }

    public static void fixInsertion(Node node) {
        while (node != root && node.parent.color.equals("red")) {
            Node parent = node.parent;
            Node grandparent = parent.parent;

            if (parent == grandparent.left) {
                Node uncle = grandparent.right;

                if (uncle != null && uncle.color.equals("red")) {
                    parent.color = "black";
                    uncle.color = "black";
                    grandparent.color = "red";
                    node = grandparent;
                } else {
                    if (node == parent.right) {
                        rotateLeft(parent);
                        node = parent;
                        parent = node.parent;
                    }

                    parent.color = "black";
                    grandparent.color = "red";
                    rotateRight(grandparent);
                }
            } else {
                Node uncle = grandparent.left;

                if (uncle != null && uncle.color.equals("red")) {
                    parent.color = "black";
                    uncle.color = "black";
                    grandparent.color = "red";
                    node = grandparent;
                } else {
                    if (node == parent.left) {
                        rotateRight(parent);
                        node = parent;
                        parent = node.parent;
                    }

                    parent.color = "black";
                    grandparent.color = "red";
                    rotateLeft(grandparent);
                }
            }
        }
        root.color = "black";
    }

    public static void rotateLeft(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;

        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.parent = node.parent;

        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }

        rightChild.left = node;
        node.parent = rightChild;
    }

    public static void rotateRight(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;

        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        leftChild.parent = node.parent;

        if (node.parent == null) {
            root = leftChild;
        } else if (node == node.parent.right) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }

        leftChild.right = node;
        node.parent = leftChild;
    }

    public static void delete(int data) {
        Node node = findNode(root, data);
        if (node == null) return;

        Node replacement = node;
        String originalColor = replacement.color;
        Node child;

        if (node.left == null) {
            child = node.right;
            transplant(node, node.right);
        } else if (node.right == null) {
            child = node.left;
            transplant(node, node.left);
        } else {
            replacement = findMinimum(node.right);
            originalColor = replacement.color;
            child = replacement.right;

            if (replacement.parent == node) {
                if (child != null) child.parent = replacement;
            } else {
                transplant(replacement, replacement.right);
                replacement.right = node.right;
                if (replacement.right != null) replacement.right.parent = replacement;
            }

            transplant(node, replacement);
            replacement.left = node.left;
            if (replacement.left != null) replacement.left.parent = replacement;
            replacement.color = node.color;
        }

        if (originalColor.equals("black")) {
            fixDeletion(child);
        }
    }

    public static void fixDeletion(Node node) {
        while (node != root && (node == null || node.color.equals("black"))) {
            Node parent = node == null ? root : node.parent;
            Node sibling;

            if (node == parent.left) {
                sibling = parent.right;

                if (sibling.color.equals("red")) {
                    sibling.color = "black";
                    parent.color = "red";
                    rotateLeft(parent);
                    sibling = parent.right;
                }

                if ((sibling.left == null || sibling.left.color.equals("black")) &&
                    (sibling.right == null || sibling.right.color.equals("black"))) {
                    sibling.color = "red";
                    node = parent;
                } else {
                    if (sibling.right == null || sibling.right.color.equals("black")) {
                        if (sibling.left != null) sibling.left.color = "black";
                        sibling.color = "red";
                        rotateRight(sibling);
                        sibling = parent.right;
                    }

                    sibling.color = parent.color;
                    parent.color = "black";
                    if (sibling.right != null) sibling.right.color = "black";
                    rotateLeft(parent);
                    node = root;
                }
            } else {
                sibling = parent.left;

                if (sibling.color.equals("red")) {
                    sibling.color = "black";
                    parent.color = "red";
                    rotateRight(parent);
                    sibling = parent.left;
                }

                if ((sibling.right == null || sibling.right.color.equals("black")) &&
                    (sibling.left == null || sibling.left.color.equals("black"))) {
                    sibling.color = "red";
                    node = parent;
                } else {
                    if (sibling.left == null || sibling.left.color.equals("black")) {
                        if (sibling.right != null) sibling.right.color = "black";
                        sibling.color = "red";
                        rotateLeft(sibling);
                        sibling = parent.left;
                    }

                    sibling.color = parent.color;
                    parent.color = "black";
                    if (sibling.left != null) sibling.left.color = "black";
                    rotateRight(parent);
                    node = root;
                }
            }
        }

        if (node != null) {
            node.color = "black";
        }
    }

    public static void transplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    public static Node findMinimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static Node findNode(Node root, int data) {
        while (root != null) {
            if (data < root.data) {
                root = root.left;
            } else if (data > root.data) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }

    public static void levelOrderTraversal() {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.data + "(" + (node.color.equals("black") ? "B" : "R") + ") ");

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        int[] values = {7, 3, 18, 10, 22, 8, 11, 26, 2, 6, 13};
        for (int value : values) {
            insert(value);
        }

        levelOrderTraversal();

        delete(18);
        levelOrderTraversal();

        delete(11);
        levelOrderTraversal();

        delete(3);
        levelOrderTraversal();

        delete(10);
        levelOrderTraversal();

        delete(22);
        levelOrderTraversal();
    }
}