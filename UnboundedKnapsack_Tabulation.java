// Repetition of items allowed

// Optimal Sub-structure: To consider all subsets of items, there can be two cases for every item.

// Case 1: The item is included in the optimal subset. We do not reduce the number of items in the recursive calls in this case. This is where it differs from bounded knapsack.
// Case 2: The item is not included in the optimal set.
// Therefore, the maximum value that can be obtained from ‘n’ items is the max of the following two values.

// Also refer GeeksForGeeks Approaches

// Variant : Rod Cutting
/*
Given a rod of length n inches and an array of prices that includes prices of all pieces of size smaller than n. 
Determine the maximum value obtainable by cutting up the rod and selling the pieces.
    length = 1 2 3 4 5 6 7 8 
    price = 1 5 8 9 10 17 17 20
    rodLength = 8 
*/
public class UnboundedKnapsack_Tabulation {
    static int MaxProfitKnapsack(int[] val, int[] wt, int W) {
        int n = val.length;

        int[][] dp = new int[n + 1][W + 1]; 
        
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                int v_i = val[i - 1]; // i th item value
                int wt_i = wt[i - 1]; // i th item weight
                // Is allowed in the bag of weight j
                if (wt_i <= j) {
                    int include = v_i + dp[i][j - wt_i]; // Real Difference between 0-1 Knapsack and Unbounded Knapsack lies in this line
                    int exclude = dp[i - 1][j];

                    dp[i][j] = Math.max(include, exclude);
                } else { // Not allowed in the bag of weight j
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][W];
    }
    public static void main(String[] args) {
        int[] val = { 15, 14, 10, 45, 30 };
        int[] wt = { 2, 5, 1, 3, 4 };
        int W = 7;

        System.out.println(MaxProfitKnapsack(val, wt, W));
    }
}