class Stack {
    int top = -1;
    int maxsize = 3;
    int a[] = new int[maxsize];

    public static void main(String args[]) {
        Stack st = new Stack();
        st.push(10);
        st.push(20);
        st.display();
        System.out.println("Peek Element: " + st.peek());
        st.pop();
        st.display();
    }

    public void push(int data) {
        if (isFull()) {
            System.out.println("Stack is Full");
            return;
        }
        top++;
        a[top] = data;
    }

    public void pop() {
        if (isEmpty()) {
            System.out.println("Stack is Empty");
            return;
        }
        top--;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is Empty");
            return -1;
        }
        return a[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == a.length - 1;
    }

    public void display() {
        for (int i = 0; i <= top; i++) {
            System.out.println(a[i]);
        }
    }
}
