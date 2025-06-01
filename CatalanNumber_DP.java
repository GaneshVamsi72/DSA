// Catalan numbers satisfy the following recursive formula: C(0) = C(1) = 1 and Cn = ∑ (i=0) to (n−1) Ci x Cn−i−1 for n ≥ 2 

public class CatalanNumber_DP {

    static int CatalanRec(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += CatalanRec(i) * CatalanRec(n - i - 1);
        } 

        return ans;
    }

    // We can observe that the above recursive implementation does a lot of repeated work. Since there are overlapping subproblems, we can use dynamic programming for this.
    static int CatalanMemo(int n, int[] dp) {
        if (n == 0 || n == 1) {
            return 1;
        }

        int ans = 0;
        if (dp[n] != 0) {
            return dp[n];
        }

        for (int i = 0; i < n; i++) {
            ans += CatalanMemo(i, dp) * CatalanMemo(n - i - 1, dp);
        }

        return dp[n] = ans;
    }

    static int CatalanTab(int n) {
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 5;

        System.out.println(CatalanRec(n));
        System.out.println(CatalanMemo(n, new int[n + 1]));
        System.out.println(CatalanTab(n));
    }
}