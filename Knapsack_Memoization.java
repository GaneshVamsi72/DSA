// 0-1 Knapsack
// Refer Knapsack_DynamnicProgramming File

// O(n * W)
// Reason : 
// Subproblems: In the memoization approach, we solve subproblems for each item and each possible weight capacity. This means we have (n) items and (W) possible capacities, leading to (n * W) subproblems.
// Memoization Table: We use a 2D table (or array) to store the results of these subproblems. The table has dimensions (n * W), and filling each cell in this table takes constant time (O(1)).
// Recursive Calls: Each subproblem is solved once and stored in the table. When a subproblem is encountered again, the result is retrieved from the table in constant time, avoiding redundant calculations.

import java.util.Arrays;

public class Knapsack_Memoization {

    static int MaxProfitKnapsack(int[] val, int[] wt, int W, int n, int[][] dp) {
        if (W == 0 || n == 0) {
            return 0;
        }

        if (dp[n][W] != -1) {
            return dp[n][W];
        }

        // We can put that item into the bag
        if (wt[n - 1] <= W) {
            // We will put it
            int ans1 = val[n - 1] + MaxProfitKnapsack(val, wt, W - wt[n - 1], n - 1, dp);
            // We will not put it
            int ans2 = MaxProfitKnapsack(val, wt, W, n - 1, dp);
            return dp[n][W] = Math.max(ans1, ans2);
        } else { // We can not put that item into the bag
            // We will not put it
            return dp[n][W] = MaxProfitKnapsack(val, wt, W, n - 1, dp);
        }
    }

    public static void main(String[] args) {
        int[] val = { 15, 14, 10, 45, 30 };
        int[] wt = { 2, 5, 1, 3, 4 };
        int W = 7;

        int[][] dp = new int[val.length + 1][W + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(MaxProfitKnapsack(val, wt, W, val.length, dp));
    }
}