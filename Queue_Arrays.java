public class Queue_Arrays {
    public static class Queue {
        int arr[];
        int size;
        int rear;

        Queue(int n) {
            arr = new int[n];
            size = n;
            rear = -1;
        }

        public boolean isEmpty() {
            return rear == -1;
        }

        public boolean isFull() {
            return rear == size - 1;
        }

        public void add(int data) {
            if (isFull()) {
                System.out.println("Queue is Full.");
                return;
            }

            arr[++rear] = data;
        }

        public int remove() {
            if (isEmpty()) {
                System.out.println("Queue is Empty.");
                return -1;
            }

            int front = arr[0];

            for (int i = 0; i < rear; i++) {
                arr[i] = arr[i + 1];
            }
            rear--;

            return arr[front];
        }

        public int peek() {
            if (isEmpty()) {
                System.out.println("Queue is Empty.");
                return -1;
            }

            return arr[0];
        }
    }

    public static void main(String[] args) {
        Queue q = new Queue(5);

        q.add(1);
        q.add(2);
        q.add(3);

        while (!q.isEmpty()) {
            System.out.print(q.peek() + " ");
            q.remove();
        }
    }
}