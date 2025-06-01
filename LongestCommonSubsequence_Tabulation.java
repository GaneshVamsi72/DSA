public class LongestCommonSubsequence_Tabulation {

    static int LCS(String str1, String str2, int n, int m) {
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        String str1 = "abcdge";
        String str2 = "abedg";

        System.out.println(LCS(str1, str2, str1.length(), str2.length()));
    }
}
