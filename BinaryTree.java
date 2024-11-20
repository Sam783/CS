import java.util.*;
class BinaryTree{
    static int idx = -1;
    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static Node buildTree(int nodes[]){
        idx++;
        if(nodes[idx] == -1){
            return null;
        }

        Node newNode = new Node(nodes[idx]);
        newNode.left = buildTree(nodes);
        newNode.right = buildTree(nodes);

        return newNode;
    }

    public static void preorder(Node root){
        // if(root == null){
        //     return;
        // }
        // System.out.print(root.data + " ");
        // preorder(root.left);
        // preorder(root.right);

        if(root == null){
            return;
        }

        Stack<Node> st = new Stack<Node>();
        st.push(root);
        while(!st.isEmpty()){
            root = st.pop();
            System.out.print(root.data + " ");
            
            if(root.right != null){
                st.push(root.right);
            }

            if(root.left != null){
                st.push(root.left);
            }
        }
    }

    public static void inorder(Node root){
        // if(root == null){
        //     return;
        // }
        // inorder(root.left);
        // System.out.print(root.data + " ");
        // inorder(root.right);
    }

    public static void postorder(Node root){
        if(root == null){
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }
    
    public static void levelorder(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while(!q.isEmpty()){
            Node current = q.poll();

            if(current == null){
                System.out.println();
                if (!q.isEmpty()) {
                    q.add(null);
                }
            }else {
                System.out.print(current.data + " ");
                if (current.left != null) {
                    q.add(current.left);
                }
                if (current.right != null) {
                    q.add(current.right);
                }
            }
        }
    }

    public static int count(Node root){
        if(root == null){
            return 0;
        }
        return 1 + count(root.left) + count(root.right);
    }

    public static int sum(Node root){
        if(root == null){
            return 0;
        }
        return root.data + sum(root.left) + sum(root.right);
    }
    
    public static int height(Node root){
        if(root == null){
            return 0;
        }

        int left_height = height(root.left);
        int right_height = height(root.right);

        return Math.max(left_height,right_height) + 1;
    }

    public static void main(String args[]){
        int nodes[] = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BinaryTree tree = new BinaryTree();
        Node root = buildTree(nodes);
        preorder(root);
        // inorder(root);
        // postorder(root);
        // levelorder(root);
        System.out.println("\n" + count(root));
        System.out.println(sum(root));
        System.out.println(height(root));
    }
}

