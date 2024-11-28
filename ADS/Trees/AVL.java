import java.util.LinkedList;
import java.util.Queue;
class AVL{
    static Node root;
    static class Node{
        int data, height;
        Node left;
        Node right;
        
        Node(int data) {
            this.data = data;
            this.height = 1;
            this.left = this.right = null;
        }
    }

    public static int height(Node root) {
        return root == null ? 0 : root.height;
    }

    public static int getBalance(Node root){
        return root == null ? 0 : height(root.left) - height(root.right);
    }

    public static Node insertNode(Node root, int val){
        if (root == null) return new Node(val);

        if(root.data > val){
            root.left = insertNode(root.left,val);
        }else if(root.data < val){
            root.right = insertNode(root.right,val);
        }else{
            return root;
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        // LL
        if(balance > 1 && root.left.data > val){
            return rightRotate(root);
        }

        // RR
        if(balance < -1 && root.right.data < val){
            return leftRotate(root);
        }

        // LR
        if(balance > 1 && root.left.data < val){
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // RL
        if(balance < -1 && root.right.data > val){
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    public static Node rightRotate(Node x) {
        Node y = x.left;

        x.left = y.right;
        y.right = x;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public static Node leftRotate(Node x) {
        Node y = x.right;

        x.right = y.left;
        y.left = x;  
        
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public static Node delete(Node root, int val){
        if(root.data > val){
            root.left = delete(root.left, val);
        }
        else if(root.data < val){
            root.right = delete(root.right, val);
        }else{ 
            // root.data == val

            // case-1 : no child
            if(root.left == null && root.right == null){
                return null;
            }

            // case-2 : one child
            if(root.left == null){
                return root.right;
            }else if(root.right == null){
                return root.left;
            }

            // case-3 : two child
            root.data = minValue(root.right);
            root.right = delete(root.right, root.data);
        }
        
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        // LL
        if(balance > 1 && root.left.data > val){
            return rightRotate(root);
        }

        // RR
        if(balance < -1 && root.right.data < val){
            return leftRotate(root);
        }

        // LR
        if(balance > 1 && root.left.data < val){
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // RL
        if(balance < -1 && root.right.data > val){
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    public static int minValue(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }

    private static void preOrder(Node root) {
        if (root != null) { 
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    private static void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    private static void postOrder(Node root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }
    }

    public static void levelOrder(Node root) {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.data + " ");

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }

    public static void insert(int val) {
        root = insertNode(root, val);
    }

    public static void main(String args[]){
        AVL t = new AVL();
        insert(30);
        insert(25);
        insert(65);
        insert(18);
        insert(27);
        insert(44);
        insert(75);
        insert(19);
        insert(50);
        insert(68);
        insert(80);
        insert(69);
        
        // preOrder(root);

        levelOrder(root);
        System.out.println();

        delete(root, 25);
        System.out.println("Level Order Traversal After Deletion:");
        levelOrder(root);
    }
}