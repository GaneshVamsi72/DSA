// Have to understand better!... Problem in OOP
public class Stack_LinkedList {
    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class Stack {
        public Node head = null;

        public boolean isEmpty() {
            return head == null;
        }

        public void push(int data) {
            Node newNode = new Node(data);
            if (isEmpty()) {
                head = newNode;
                return;
            }
            newNode.next = head;
            head = newNode;
        }

        public int pop() {
            if (isEmpty()) {
                System.out.println("Stack Underflow!!");
                return -1;
            }
            int top = head.data;
            head = head.next;
            return top;
        }

        public int peek() {
            if (isEmpty()) {
                System.out.println("Stack is Empty!!");
                return -1;
            }
            return head.data;
        }
    }

    public static void main(String[] args) {
        Stack S = new Stack();
        S.push(1);
        S.push(2);
        S.push(3);

        while (!S.isEmpty()) {
            System.out.print(S.peek() + " ");
            S.pop();
        }
    }
}