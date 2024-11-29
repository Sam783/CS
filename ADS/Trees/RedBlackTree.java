class RedBlackTree {
    static Node root;
    static class Node{
        int data;
        String color;
        Node left, right;
        Node parent;

        Node(int data){
            this.left = this.right = this.parent = null;
            this.data = data;
            this.color = "red";
        }
    }

    public static void insert(int data){
        Node node = new Node(data);
        Node temp = root, parent = null;
        while(temp != null){
            parent = temp;
            if(node.data < temp.data){
                temp = temp.left;
            }else if (node.data > temp.data){
                temp = temp.right;
            }else{
                return;
            }
        }

        if(parent == null){
            root = node;
        }else if(node.data < parent.data){
            parent.left = node;
        }else{
            parent.right = node;
        }

        node.parent = parent;
        fixViolation(node);
    }

    public static void fixViolation(Node node) {
        Node parent, grandparent;
        while(node != root && node.parent.color.equals("red")){
            parent = node.parent;
            grandparent = parent.parent;

            if(parent == grandparent.left){
                Node uncle = grandparent.right;
                if(uncle != null && uncle.color.equals("red")){
                    grandparent.color = "red";
                    parent.color = "black";
                    uncle.color = "black";
                    node = grandparent;
                }else{
                    if(node == parent.right){
                        node = parent;
                        rotateLeft(node);
                    }
                    parent.color = "black";
                    grandparent.color = "red";
                    rotateRight(grandparent);
                }
            }
            else{
                Node uncle = grandparent.left;
                if(uncle != null && uncle.color.equals("red")){
                    grandparent.color = "red";
                    parent.color = "black";
                    uncle.color = "black";
                    node = grandparent;
                }else{
                    if(node == parent.left){
                        node = parent;
                        rotateRight(node);
                    }
                    parent.color = "black";
                    grandparent.color = "red";
                    rotateLeft(grandparent);
                }
            }
        }
        root.color = "black";
    }

    public static Node rotateLeft(Node node){
        Node rightChild = node.right;
        node.right = rightChild.left;

        if(rightChild.left != null){
            rightChild.left.parent = node;
        }

        rightChild.left = node;
        rightChild.parent = node.parent;

        if(node.parent == null){
            root = rightChild;
        }
        else if(node == node.parent.left){
            node.parent.left = rightChild;
        }
        else{
            node.parent.right = rightChild;
        }

        node.parent = rightChild;
        return rightChild;
    }

    public static Node rotateRight(Node node){
        Node leftChild = node.left;
        node.left = leftChild.right;

        if(leftChild.right != null){
            leftChild.right.parent = node;
        }

        leftChild.right = node;
        leftChild.parent = node.parent;

        if(node.parent == null){
            root = leftChild;
        }
        else if(node == node.parent.right){
            node.parent.right = leftChild;
        }
        else{
            node.parent.left = leftChild;
        }

        node.parent = leftChild;
        return leftChild;
    }

    public static void inorder(Node root) {
        if(root != null){
            inorder(root.left);
            System.out.print(root.data + "(" + (root.color.equals("black") ? "B" : "R") + ") ");
            inorder(root.right);
        }
    }

    public static void main(String[] args){
        RedBlackTree tree = new RedBlackTree();
        
        for(int i = 1; i <= 10; i++){
            tree.insert(i);
        }

        System.out.println("Inorder Traversal:");
        inorder(root);
    }
}
