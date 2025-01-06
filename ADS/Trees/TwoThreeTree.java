import java.io.*;
import java.util.*;

public class TwoThreeTree {
    static class Node {
        int[] keys = new int[3];
        Node[] children = new Node[4];
        int numKeys = 0;
    }

    public static Node createNode() {
        Node newNode = new Node();
        Arrays.fill(newNode.children, null);
        return newNode;
    }

    public static boolean search(Node root, int key) {
        if (root == null) {
            return false;
        }

        int i;
        for (i = 0; i < root.numKeys; i++) {
            if (key == root.keys[i]) {
                return true;
            }
            if (key < root.keys[i]) {
                return search(root.children[i], key);
            }
        }
        return search(root.children[i], key);
    }

    public static Node findParent(Node root, Node child) {
        if (root == null || root.children[0] == null) {
            return null;
        }

        for (int i = 0; i <= root.numKeys; i++) {
            if (root.children[i] == child) {
                return root;
            }
            Node result = findParent(root.children[i], child);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public static Node split(Node root, Node nodeToSplit) {
        Node newNode = createNode();
        int midKey = nodeToSplit.keys[1];

        newNode.keys[0] = nodeToSplit.keys[2];
        newNode.numKeys = 1;

        if (nodeToSplit.children[0] != null) {
            newNode.children[0] = nodeToSplit.children[2];
            newNode.children[1] = nodeToSplit.children[3];
            nodeToSplit.children[2] = nodeToSplit.children[3] = null;
        }

        nodeToSplit.numKeys = 1;

        if (root == nodeToSplit) {
            Node newRoot = createNode();
            newRoot.keys[0] = midKey;
            newRoot.children[0] = nodeToSplit;
            newRoot.children[1] = newNode;
            newRoot.numKeys = 1;
            return newRoot;
        } else {
            Node parent = findParent(root, nodeToSplit);
            int i = parent.numKeys - 1;

            while (i >= 0 && parent.keys[i] > midKey) {
                parent.keys[i + 1] = parent.keys[i];
                parent.children[i + 2] = parent.children[i + 1];
                i--;
            }

            parent.keys[i + 1] = midKey;
            parent.children[i + 2] = newNode;
            parent.numKeys++;

            if (parent.numKeys == 3) {
                root = split(root, parent);
            }
        }

        return root;
    }

    public static Node insert(Node root, int key) {
        if (root == null) {
            root = createNode();
            root.keys[0] = key;
            root.numKeys = 1;
            return root;
        }

        Node current = root;
        while (current.children[0] != null) {
            int i = 0;
            while (i < current.numKeys && key > current.keys[i]) {
                i++;
            }
            current = current.children[i];
        }

        int i = current.numKeys - 1;
        while (i >= 0 && current.keys[i] > key) {
            current.keys[i + 1] = current.keys[i];
            i--;
        }
        current.keys[i + 1] = key;
        current.numKeys++;

        if (current.numKeys == 3) {
            root = split(root, current);
        }

        return root;
    }

    public static Node deleteNode(Node root, int key) {
        if (root == null) {
            System.out.println("Tree is empty.");
            return null;
        }

        Node current = root;
        Node parent = null;
        int i = 0;  // Initialize i here

        while (current != null) {
            for (i = 0; i < current.numKeys && key > current.keys[i]; i++);
            if (i < current.numKeys && key == current.keys[i]) {
                break;
            }
            parent = current;
            current = current.children[i];
        }

        if (current == null) {
            System.out.println("Key " + key + " not found.");
            return root;
        }

        if (current.children[0] == null) {
            for (int j = i; j < current.numKeys - 1; j++) {
                current.keys[j] = current.keys[j + 1];
            }
            current.numKeys--;

            if (root == current && current.numKeys == 0) {
                return null;
            }

            if (current.numKeys == 0 && parent != null) {
                for (i = 0; i <= parent.numKeys; i++) {
                    if (parent.children[i] == current) {
                        break;
                    }
                }

                for (int j = i; j < parent.numKeys; j++) {
                    parent.children[j] = parent.children[j + 1];
                }
                parent.children[parent.numKeys] = null;

                return root;
            }

            return root;
        } else {
            Node successor = current.children[i + 1];
            Node prev = current;

            while (successor.children[0] != null) {
                prev = successor;
                successor = successor.children[0];
            }

            current.keys[i] = successor.keys[0];

            if (successor.numKeys == 1 && successor.children[0] == null) {
                for (i = 0; i <= prev.numKeys; i++) {
                    if (prev.children[i] == successor) {
                        break;
                    }
                }

                for (int j = i; j < prev.numKeys; j++) {
                    prev.children[j] = prev.children[j + 1];
                }
                prev.children[prev.numKeys] = null;

                return root;
            }

            for (i = 0; i < successor.numKeys - 1; i++) {
                successor.keys[i] = successor.keys[i + 1];
            }
            successor.numKeys--;
        }

        return root;
    }

    public static void inorderTraversal(Node root) {
        if (root == null) {
            return;
        }

        for (int i = 0; i < root.numKeys; i++) {
            inorderTraversal(root.children[i]);
            System.out.print(root.keys[i] + " ");
        }
        inorderTraversal(root.children[root.numKeys]);
    }

    public static Node insertFromFile(String filename, Node root) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            String[] tokens = line.split(" ");
            for (String token : tokens) {
                int num = Integer.parseInt(token);
                root = insert(root, num);
            }
        }
        br.close();
        return root;
    }

    public static void main(String[] args) throws IOException {
        Node root = null;
        String filename = "case3b.txt";

        root = insertFromFile(filename, root);

        System.out.println("\nIn-order Traversal: ");
        inorderTraversal(root);
        System.out.println();

        if (search(root, 25)) {
            System.out.println("\nKey found");
        } else {
            System.out.println("\nKey not found");
        }

        root = deleteNode(root, 80);
        System.out.println("After deletion:");
        inorderTraversal(root);
    }
}
