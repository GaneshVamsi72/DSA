import java.util.*;
import java.util.LinkedList;

// In this implementation of Stack, any queue can simulate as the main Stack!!!
// So It is necessary to check where the elements are !! when performing an operation

public class StackUsingTwoQueues {
    static class Stack1 {
        static Queue<Integer> q1 = new LinkedList<>();
        static Queue<Integer> q2 = new LinkedList<>();

        public boolean isEmpty() {
            return q1.isEmpty() && q2.isEmpty();
        }

        // push - O(1)
        public void push(int data) {
            if (!q1.isEmpty()) {
                q1.add(data);
            } else {
                q2.add(data);
            }
        }

        // pop - O(n)
        public int pop() {
            if (isEmpty()) {
                System.out.println("Stack is Empty!");
                return -1;
            }

            int top = -1;

            if (!q1.isEmpty()) {
                while (!q1.isEmpty()) {
                    top = q1.remove();
                    if (q1.isEmpty()) {
                        break;
                    }
                    q2.add(top);
                }
            } else {
                while (!q2.isEmpty()) {
                    top = q2.remove();
                    if (q2.isEmpty()) {
                        break;
                    }
                    q1.add(top);
                }
            }

            return top;
        }

        // peek - O(n)
        public int peek() {
            if (isEmpty()) {
                System.out.println("Stack is Empty!");
                return -1;
            }

            int top = -1;

            if (!q1.isEmpty()) {
                while (!q1.isEmpty()) {
                    top = q1.remove();
                    q2.add(top);
                }
            } else {
                while (!q2.isEmpty()) {
                    top = q2.remove();
                    q1.add(top);
                }
            }

            return top;
        }

    }

    static class Stack2 {
        static Queue<Integer> q1 = new LinkedList<>();
        static Queue<Integer> q2 = new LinkedList<>();

        public boolean isEmpty() {
            return q1.isEmpty() && q2.isEmpty();
        }

        // add - O(n)
        public void push(int data) {
            if (isEmpty()) {
                q1.add(data);
                return;
            }

            if (q1.isEmpty()) {
                q1.add(data);

                while (!q2.isEmpty()) {
                    q1.add(q2.remove());
                }
            } else {
                q2.add(data);

                while (!q1.isEmpty()) {
                    q2.add(q1.remove());
                }
            }
        }

        // pop - O(1)
        public int pop() {
            if (isEmpty()) {
                System.out.println("Stack is Empty!");
                return -1;
            }

            int top = -1;

            if (!q1.isEmpty()) {
                top = q1.remove();
            } else {
                top = q2.remove();
            }

            return top;
        }

        // peek - O(1)
        public int peek() {
            if (isEmpty()) {
                System.out.println("Stack is Empty!");
                return -1;
            }

            int top = -1;

            if (!q1.isEmpty()) {
                top = q1.peek();
            } else {
                top = q2.peek();
            }

            return top;
        }
    }

    public static void main(String[] args) {
        Stack1 s1 = new Stack1();

        s1.push(1);
        s1.push(2);
        s1.push(3);

        while (!s1.isEmpty()) {
            System.out.print(s1.peek() + " ");
            s1.pop();
        }

        System.out.println();

        Stack2 s2 = new Stack2();

        s2.push(1);
        s2.push(2);
        s2.push(3);

        while (!s2.isEmpty()) {
            System.out.print(s2.peek() + " ");
            s2.pop();
        }
    }
}