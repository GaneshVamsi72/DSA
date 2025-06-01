// Variation of Unbounded Knapsack

public class CoinChange_Tabulation {

    static int NoOfWays(int[] coins, int sum) {
        int n = coins.length;
        int[][] dp = new int[n + 1][sum + 1];

        // 0 th col is initialized with 1 as there is only one way to achieve 0 sum with any number of coins (i.e., Do nothing)
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }

        // 0 th row must be initialized with 0 as there is no way to achieve any sum with 0 number of coins (except for case when sum = 0)
        // This part is default as the elements of an array are initially zero. So there is no need for the initialization of 0 th row

        for (int i = 1; i < dp.length; i++) {
            int c_i = i - 1;
            for (int j = 1; j < dp[0].length; j++) {
                if (coins[c_i] <= j) {
                    int include = dp[i][j - coins[c_i]];
                    int exclude = dp[i - 1][j];
                    dp[i][j] = include + exclude;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][sum];
    }

    public static void main(String[] args)  {
        int[] coins = { 2, 5, 3, 6 };
        int sum = 10;

        System.out.println(NoOfWays(coins, sum));
    }
} 