// We have an array arr[] of size N and an integer K. Find the maximum for each and every contiguous subarray of size K.
// Sample Input 1: N = 9, K = 3, arr = 1 2 3 1 4 5 2 3 6 
// Sample Output 1: 3 3 4 5 5 5 6

// Have to understand better !!!!!!
// Also learn solving Leetcode Problem (Good Extension of this problem !) :- 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit (Check the solution submitted in Leetcode. Take the help of GPT !!)

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class MaximumOfAllSubArraysOfSizeK {

    // O(n)
    public static void printMax1(int[] arr, int k) {
        Deque<Integer> Dq = new LinkedList<>();

        // Initialize Dq with the first k elements
        for (int i = 0; i < k; i++) {
            while (!Dq.isEmpty() && arr[i] >= arr[Dq.getLast()]) {
                Dq.removeLast();
            }
            Dq.addLast(i);
        }

        // Process the remaining elements
        for (int i = k; i < arr.length; i++) {
            // Output the front element
            System.out.print(arr[Dq.getFirst()] + " ");

            // Remove elements that are out of the current window
            while (!Dq.isEmpty() && Dq.getFirst() <= i - k) {
                Dq.removeFirst();
            }

            // Remove elements that are not needed in the current window
            while (!Dq.isEmpty() && arr[i] >= arr[Dq.getLast()]) {
                Dq.removeLast();
            }

            Dq.addLast(i);
        }

        System.out.print(arr[Dq.removeFirst()]);
    }

    static class Pair implements Comparable<Pair> {
        int val;
        int idx;

        public Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public int compareTo(Pair other) {
            return other.val - this.val;
        }
    }

    // O(n * log n) more specifically -> (O(n * log k))
    static void printMax2(int[] arr, int k) {
        int[] res = new int[arr.length - k + 1] ;
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        // Process first 3 elements
        for (int i = 0; i < k; i++) {
            pq.add(new Pair(arr[i], i));
        }
        // The maximum element in the first window
        res[0] = pq.peek().val;


        for (int i = k; i < arr.length; i++) {
            // Remove elements that are out of the current window
            while(!pq.isEmpty() && pq.peek().idx <= i - k) {
                pq.remove();
            }

            pq.add(new Pair(arr[i], i));
            res[i - k + 1] = pq.peek().val;
        }

        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 1, 4, 5, 2, 3, 6 };
        int k = 3;

        printMax1(arr, k);
        System.out.println();
        printMax2(arr, k);
    }
}