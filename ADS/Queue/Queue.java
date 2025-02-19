class Queue {
    int front = 0, rear = 0, maxsize = 10;
    int a[] = new int[maxsize];

    public static void main(String args[]) {
        Queue q = new Queue();
        q.push(10);
        q.push(20);
        q.push(30);
        q.pop();
        q.display();
    }

    public void push(int data) {
        if (isFull()) {
            System.out.println("Queue is Full");
            return;
        }
        a[rear++] = data;
    }

    public void pop() {
        if (isEmpty()) {
            System.out.println("Queue is Empty");
            return;
        }
        front++;
    }

    public int front() {
        if (isEmpty()) {
            System.out.println("Queue is Empty");
            return -1;
        }
        return a[front];
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return rear == a.length - 1;
    }

    public void display() {
        for (int i = front; i < rear; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
