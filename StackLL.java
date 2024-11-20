class StackLL{
    int maxsize = 3;
    int currsize = 0;
    Node head;
    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public void push(int data){
        if(currsize == maxsize){
            System.out.println("Stack is full");
            return;
        }
        Node newnode = new Node(data);
        newnode.next = head;
        head = newnode;
        currsize++;
    }

    public int pop(){
        if(currsize == 0){
            System.out.println("Stack is empty");
        }
        head = head.next;
        currsize--;
        return head.data;
    }

    public int peek(){
        if(currsize == 0){
            System.out.println("Stack is empty");
        }
        return head.data;
    }

    public boolean isEmpty() {
        return currsize == 0;
    }

    public boolean isFull() {
        return currsize == maxsize;
    }

    public void display(){
        Node curr = head;
        while(curr != null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

    public static void main(String args[]){
        StackLL st = new StackLL();
        st.push(10);
        st.push(20);
        st.push(30);
        st.pop();
        st.push(40);
        st.display();;
        System.out.println(st.isEmpty());
        System.out.println(st.isFull());
    }
}