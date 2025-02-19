class QueueLL {
    Node head;
    Node tail;
    int currsize = 0;
    int maxsize = 10;

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }

    public boolean isFull() {
        return currsize == maxsize;
    }

    public void Enqueue(int data) {
        if (isFull()) {
            System.out.println("Queue is Full");
            return;
        }
        Node newNode = new Node(data);
        if (tail == null) {
            tail = head = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        currsize++;
    }

    public int Dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is Empty");
            return -1;
        }
        int front = head.data;
        if (head == tail) {
            tail = null;
        }
        head = head.next;
        currsize--;

        return front;
    }

    public int front() {
        if (isEmpty()) {
            System.out.println("Queue is Empty");
            return -1;
        }
        return head.data;
    }

    public void display() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String args[]) {
        QueueLL q = new QueueLL();
        q.Enqueue(10);
        q.Enqueue(20);
        q.Enqueue(30);
        q.display();
        q.Dequeue();
        q.display();
        System.out.println(q.isEmpty());
    }
}
