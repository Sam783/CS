class BST{
    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = this.right = null;
        }
    }

    public static Node insert(Node root, int val){
        if(root == null){
            return new Node(val);
        }
        if(root.data > val){
            root.left = insert(root.left, val);
        }
        if(root.data < val){
            root.right = insert(root.right, val);
        }
        return root;
    }

    public static void preorder(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void inorder(Node root){
        if(root == null){
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void postorder(Node root){
        if(root == null){
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    public static boolean search(Node root, int key){
        if(root == null){
            return false;
        }
        if(root.data == key){
            return true;
        }
        if(root.data > key){
            return search(root.left, key);
        }
        if(root.data < key){
            return search(root.right, key);
        }
        return false;
    }

    public static Node delete(Node root, int val){
        if(root.data > val){
            root.left = delete(root.left, val);
        }
        else if(root.data < val){
            root.right = delete(root.right, val);
        }else{ // root.data == val

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
        return root;
    }

    public static int minValue(Node root){
        while(root.left != null){
            root = root.left;
        }
        return root.data;
    }

    public static void main(String args[]){
        BST t = new BST();
        Node root = null;
        int values[] = {8,5,3,1,4,6,10,11,14};
        for(int i=0; i<values.length; i++){
            root = insert(root,values[i]);
        }
        // root = insert(root,10);
        // root = insert(root,20);
        // preorder(root)
        inorder(root);
        delete(root,5);
        System.out.println();
        inorder(root);
        // postorder(root);
        if(search(root,4)){
            System.out.println("\nKey found");
        }else{
            System.out.println("\nKey not found");
        }
    }
}