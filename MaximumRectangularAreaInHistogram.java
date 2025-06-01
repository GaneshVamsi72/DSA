// Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
// Notes available
// Time Complexity : O(n)

// Important Question!!

import java.util.Stack;

public class MaximumRectangularAreaInHistogram {

    public static int getMaxArea(int[] arr) {
        int maxArea = 0;
        int n = arr.length;
        int[] nsr = new int[n];
        int[] nsl = new int[n];

        Stack<Integer> s = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!s.empty() && arr[i] <= arr[s.peek()]) {
                s.pop();
            }
            if (s.empty()) {
                nsr[i] = n;
            } else {
                nsr[i] = s.peek();
            }
            s.push(i);
        }

        s = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!s.empty() && arr[i] <= arr[s.peek()]) {
                s.pop();
            }
            if (s.empty()) {
                nsl[i] = -1;
            } else {
                nsl[i] = s.peek();
            }
            s.push(i);
        }

        for (int j = 0; j < n; j++) {
            int height = arr[j];
            int width = nsr[j] - nsl[j] - 1;
            int currArea = height * width;
            maxArea = Math.max(maxArea, currArea);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = { 2, 1, 5, 6, 2, 3 };

        System.out.println(getMaxArea(heights));
    }
}