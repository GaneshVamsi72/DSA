// Important !

// The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.

// Time Complexity : O(n)
// Reason : The loop iterates through the entire array once (from right to left), which takes O(n) time.
// Within the loop, we perform stack operations (push and pop) for each element. Since each element is pushed onto the stack once and popped at most once, the total number of stack operations is also O(n).

import java.util.Stack;

public class NextGreaterElement {

    public static void main(String[] args) {
        int[] arr = { 6, 8, 0, 1, 3 };

        Stack<Integer> s = new Stack<>();

        int[] nxtGreater = new int[arr.length];

        for (int i = nxtGreater.length - 1; i >= 0; i--) {
            while (!s.empty() && arr[s.peek()] <= arr[i]) {
                s.pop();
            }

            if (s.empty()) {
                nxtGreater[i] = -1;
            } else {
                nxtGreater[i] = arr[s.peek()];
            }

            s.push(i);
        }

        for (int i = 0; i < nxtGreater.length; i++) {
            System.out.print(nxtGreater[i] + " ");
        }
    }
}