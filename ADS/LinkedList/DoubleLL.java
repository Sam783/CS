class DoubleLL {
    Node head, tail;
    class Node {
        int data;
        Node prev, next;
        Node(int data){
            this.data = data;
            this.prev = this.next = null;
        }
    }

    public void add(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }

    public void delete(){
        if(head == null){
            System.out.println("Queue is empty");
            return;
        }
        head = head.next;
        head.prev = null;
    }

    public void display(){
        Node curr = head;
        while (curr != null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String args[]){
        DoubleLL list = new DoubleLL();
        list.add(10);
        list.add(20);
        list.add(30);
        list.display();
        list.delete();
        list.display();
    }
}
