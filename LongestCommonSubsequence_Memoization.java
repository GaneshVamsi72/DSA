public class LongestCommonSubsequence_Memoization {

    static int LCS(String str1, String str2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return 0;
        }

        if (dp[n][m] != -1) {
            return dp[n][m];
        }

        if (str1.charAt(n - 1) == str2.charAt(m - 1)) {
            return dp[n][m] = 1 + LCS(str1, str2, n - 1, m - 1, dp);
        } else {
            return dp[n][m] = Math.max(LCS(str1, str2, n, m - 1, dp), LCS(str1, str2, n - 1, m, dp));
        }
    }

    public static void main(String[] args) {
        String str1 = "abcdge";
        String str2 = "abedg";
        int n = str1.length();
        int m = str2.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i != 0 && j != 0) {
                    dp[i][j] = -1;
                }
            }
        }

        System.out.println(LCS(str1, str2, n, m, dp));
    }
}