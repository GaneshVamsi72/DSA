// 0-1 Knapsack
// First revise Memoization
// O(n * W)
public class Knapsack_Tabulation {

    static int MaxProfitKnapsack(int[] val, int[] wt, int W) {
        int n = val.length;
        // dp[i][j] represents the max profit subproblem of i items and j weight
        int[][] dp = new int[n + 1][W + 1];
        // Initializing 0th row and 0th col as 0 is not needed as initial value of an array elements is 0 
        
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < W + 1; j++) {
                int v_i = val[i - 1]; // i th item value
                int wt_i = wt[i - 1]; // i th item weight
                // Is allowed in the bag of weight j
                if (wt[i - 1] <= j) {
                    int include = v_i + dp[i - 1][j - wt_i];
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