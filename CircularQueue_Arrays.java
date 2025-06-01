public class CircularQueue_Arrays {
    public static class Queue {
        int arr[];
        int size;
        int front;
        int rear;

        Queue(int n) {
            arr = new int[n];
            size = n;
            front = -1;
            rear = -1;
        }

        public boolean isEmpty() {
            return rear == -1 && front == -1;
        }

        public boolean isFull() {
            return (rear + 1) % size == front;
        }

        public void enque(int val) {
            if (isFull()) {
                System.out.println("Queue is Full!.");
                return;
            }

            if (isEmpty()) {
                front = 0;
                rear = 0;
            } else {
                rear = (rear + 1) % size;
            }

            arr[rear] = val;
        }

        public int dequeue() {
            if (isEmpty()) {
                System.out.println("Queue is already Empty!");
                return -1;
            }

            int val = arr[front];
            if (rear == front) {
                front = -1;
                rear = -1;
            } else {
                front = (front + 1) % size;
            }
            return val;
        }

        public int peek() {
            if (isEmpty()) {
                System.out.println("Queue is Empty!.");
                return -1;
            }

            int val = arr[front];
            return val;
        }
    }

    public static void main(String[] args) {
        Queue q = new Queue(3);
        q.enque(1);
        q.enque(2);
        q.enque(3);

        System.out.print(q.dequeue() + " ");
        q.enque(4);
        System.out.print(q.dequeue() + " ");
        q.enque(5);

        while (!q.isEmpty()) {
            System.out.print(q.peek() + " ");
            q.dequeue();
        }
    }
}