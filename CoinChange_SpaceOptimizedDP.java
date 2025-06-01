// Understand Unbounded Knapsack Space Optimized DP Approach First !!!!

public class CoinChange_SpaceOptimizedDP {

    static int NoOfWays(int[] coins, int sum) {
        int n = coins.length;
        int[] dp = new int[sum + 1];

        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < dp.length; j++) {
                int exclude = dp[j];
                int include = 0;

                if (coins[i] <= j) {
                    include = dp[j - coins[i]];
                }

                dp[j] = exclude + include;
            }
        }
        
        return dp[sum];
    }

    public static void main(String[] args) {
        int[] coins = { 2, 5, 3, 6 };
        int sum = 10;

        System.out.println(NoOfWays(coins, sum));
    }
}