import java.util.Stack;

// This is done by making add operation (pushing at bottom) in O(n) and remove operation in O(1)
// This can also be done by making add operation in O(1) and remove operation (removing from bottom) in O(n)

public class QueueUsingTwoStacks {
    static class Queue1 {
        static Stack<Integer> s1 = new Stack<>();
        static Stack<Integer> s2 = new Stack<>();

        boolean isEmpty() {
            return s1.empty();
        }

        // add - O(n)
        void add(int data) {
            // No need for checking for empty condition !!

            // Transferring all elements to auxiliary Stack
            while (!s1.empty()) {
                s2.push(s1.pop());
            }

            // Pushing the current data to the bottom of the main Stack
            s1.push(data);

            // Transferring all elements from auxiliary Stack
            while (!s2.empty()) {
                s1.push(s2.pop());
            }
        }

        // remove - O(1)
        int remove() {
            if (isEmpty()) {
                System.out.println("Queue is Empty!");
                return -1;
            }

            return s1.pop();
        }

        // peek - O(1)
        int peek() {
            if (isEmpty()) {
                System.out.println("Queue is Empty!");
                return -1;
            }

            return s1.peek();
        }
    }

    static class Queue2 {
        static Stack<Integer> s3 = new Stack<>();
        static Stack<Integer> s4 = new Stack<>();

        boolean isEmpty() {
            return s3.empty();
        }

        void add(int data) {
            s3.push(data);
        }

        int remove() {
            if (isEmpty()) {
                System.out.println("Queue is Empty");
                return -1;
            }

            while (!s3.empty()) {
                s4.push(s3.pop());
            }

            int data = s4.pop();

            while (!s4.empty()) {
                s3.push(s4.pop());
            }

            return data;
        }

        int peek() {
            if (isEmpty()) {
                System.out.println("Queue is Empty");
                return -1;
            }

            while (!s3.empty()) {
                s4.push(s3.pop());
            }

            int data = s4.peek();

            while (!s4.empty()) {
                s3.push(s4.pop());
            }

            return data;
        }
    }

    public static void main(String[] args) {
        Queue1 q1 = new Queue1();

        q1.add(1);
        q1.add(2);
        q1.add(3);

        while (!q1.isEmpty()) {
            System.out.print(q1.peek() + " ");
            q1.remove();
        }

        System.out.println();

        Queue2 q2 = new Queue2();

        q2.add(1);
        q2.add(2);
        q2.add(3);

        while (!q2.isEmpty()) {
            System.out.print(q2.peek() + " ");
            q2.remove();
        }
    }
}