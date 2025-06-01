// Given an array arr[] of size n, the task is to divide it into two sets S1 and S2 such that the absolute difference between their sums is minimum. 

// Variant of Target Sum Subset Problem ==> 0/1 - Knapsack Problem

/* Thought Process :-
Let:
1. totalSum = sum of all elements in the array.
2. The problem reduces to finding a subset S1 such that the sum of its elements (sum(S1)) is as close as possible to totalSum / 2.

Why? If sum(S1) is close to totalSum / 2, the difference |sum(S1) - sum(S2)| is minimized, because sum(S2) = totalSum - sum(S1).
*/
public class MinimumPartitioning_DP { 

    static int MinPartition(int[] arr) {
        int n = arr.length;

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        int W = sum / 2;

        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (arr[i - 1] <= j) {
                    int exclude = dp[i - 1][j];
                    int include = arr[i - 1] + dp[i - 1][j - arr[i - 1]];

                    dp[i][j] = Math.max(exclude, include);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        int sum1 = dp[n][W];
        int sum2 = sum - sum1;

        return Math.abs(sum2 - sum1);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 6, 11, 5 };

        System.out.println(MinPartition(arr));
    }
}