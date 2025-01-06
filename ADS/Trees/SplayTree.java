import java.util.LinkedList;
import java.util.Queue;

class SplayTree {
    class Node {
        int key;
        Node left, right, parent;

        Node(int key) {
            this.key = key;
            this.left = this.right = this.parent = null;
        }
    }

    private Node root;

    // Right rotation
    private void rotateRight(Node x) {
        Node y = x.left;
        if (y != null) {
            x.left = y.right;
            if (y.right != null) y.right.parent = x;
            y.parent = x.parent;
        }
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        if (y != null) y.right = x;
        x.parent = y;
    }

    // Left rotation
    private void rotateLeft(Node x) {
        Node y = x.right;
        if (y != null) {
            x.right = y.left;
            if (y.left != null) y.left.parent = x;
            y.parent = x.parent;
        }
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        if (y != null) y.left = x;
        x.parent = y;
    }

    // Splay operation
    private void splay(Node x) {
        while (x.parent != null) {
            if (x.parent.parent == null) {
                // Zig step
                if (x == x.parent.left) {
                    rotateRight(x.parent);
                } else {
                    rotateLeft(x.parent);
                }
            } else if (x == x.parent.left && x.parent == x.parent.parent.left) {
                // Zig-Zig step
                rotateRight(x.parent.parent);
                rotateRight(x.parent);
            } else if (x == x.parent.right && x.parent == x.parent.parent.right) {
                // Zig-Zig step
                rotateLeft(x.parent.parent);
                rotateLeft(x.parent);
            } else if (x == x.parent.right && x.parent == x.parent.parent.left) {
                // Zig-Zag step
                rotateLeft(x.parent);
                rotateRight(x.parent);
            } else {
                // Zig-Zag step
                rotateRight(x.parent);
                rotateLeft(x.parent);
            }
        }
    }

    // Search operation
    public Node search(int key) {
        Node node = root;
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else {
                splay(node); // Splay the found node to the root
                return node;
            }
        }
        return null;
    }

    // Insert operation
    public void insert(int key) {
        Node node = root, parent = null;
        while (node != null) {
            parent = node;
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else {
                splay(node);
                return;
            }
        }
        Node newNode = new Node(key);
        newNode.parent = parent;

        if (parent == null) {
            root = newNode; // Tree was empty
        } else if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        splay(newNode); // Splay the newly inserted node to the root
    }

    // Delete operation
    public void delete(int key) {
        Node node = search(key);
        if (node == null) return; // Key not found

        splay(node); // Bring the node to the root

        if (node.left != null) {
            Node leftSubtree = node.left;
            leftSubtree.parent = null;

            Node maxNode = leftSubtree;
            while (maxNode.right != null) {
                maxNode = maxNode.right;
            }

            splay(maxNode);
            maxNode.right = node.right;
            if (node.right != null) {
                node.right.parent = maxNode;
            }
            root = maxNode;
        } else if (node.right != null) {
            root = node.right;
            root.parent = null;
        } else {
            root = null;
        }
    }

    // In-order traversal (for debugging)
    public void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.key + " ");
            inOrderTraversal(node.right);
        }
    }

    public void printTree() {
        inOrderTraversal(root);
        System.out.println();
    }

    // Level-order traversal
    public void levelOrderTraversal() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.key + " ");

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        SplayTree tree = new SplayTree();

        tree.insert(12);
        tree.insert(10);
        tree.insert(35);
        tree.insert(40);
        tree.insert(54);
        tree.insert(11);
        tree.insert(56);


        System.out.println("Level-order traversal:");
        tree.levelOrderTraversal();

        tree.search(30);
        System.out.println("Level-order traversal after splaying 30:");
        tree.levelOrderTraversal();

        tree.delete(30);
        System.out.println("Level-order traversal after deleting 30:");
        tree.levelOrderTraversal();
    }
}