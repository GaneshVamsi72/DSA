public class Queue_LinkedList {
    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class Queue {
        Node head = null;
        Node tail = null;

        public boolean isEmpty() {
            return tail == null;
        }

        public void add(int data) {
            Node newNode = new Node(data);
            if (isEmpty()) {
                head = tail = newNode;
                return;
            }

            tail.next = newNode;
            tail = tail.next;
        }

        public int remove() {
            if (isEmpty()) {
                System.out.println("Queue is Empty.");
                return -1;
            }

            int front = head.data;
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
            }
            return front;
        }

        public int peek() {
            if (isEmpty()) {
                System.out.println("Queue is Empty.");
                return -1;
            }

            int front = head.data;
            return front;
        }
    }

    public static void main(String[] args) {
        Queue q = new Queue();
        q.add(1);
        q.add(2);
        q.add(3);

        while (!q.isEmpty()) {
            System.out.print(q.peek() + " ");
            q.remove();
        }
    }
}