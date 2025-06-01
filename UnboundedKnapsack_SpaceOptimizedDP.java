// Watching Explanation (which is available on Take U Forward Channel) and having Good Understanding of Tabulation method is must

// This is achieved using 1-D Array because.... While dry running the Tabulation code, we can observe that for every row filling only value in previous col of current row and value in same col of previous row are useful... So this can be simulated using just 1-D array by overwriting the value right there so that in the next iteration of the outer loop the values can act as values in same col of previous row 
// Above thing can be understood by clearly watching the video

// If using only 1-D array confuses....Then use prev and curr arrays
public class UnboundedKnapsack_SpaceOptimizedDP {

    static int MaxProfitKnapsack(int[] val, int[] wt, int W) {
        int n = val.length;
        int[] dp = new int[W + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < W + 1; j++) {
                // You have to observe the fact that no matter what exclude case will be evaluated and include case will be evaluated only when wt_i <= j is true !!! 
                int exclude = dp[j];
                int include = Integer.MIN_VALUE;

                if (wt[i] <= j) {
                    include = val[i] + dp[j - wt[i]];
                }

                dp[j] = Math.max(exclude, include);
            }
        }

        return dp[W];
    }

    public static void main(String[] args) {
        int[] val = { 15, 14, 10, 45, 30 };
        int[] wt = { 2, 5, 1, 3, 4 };
        int W = 7;

        System.out.println(MaxProfitKnapsack(val, wt, W));
    }
}