// Convert String1 to String2 with only insertion and deletion.
// Print number of deletions and insertions.

public class StringConversion_LCSApplication {
    static int LCS(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

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
        String str1 = "pear";
        String str2 = "sea";

        int LCS = LCS(str1, str2);

        int deletions = str1.length() - LCS;
        int insertions = str2.length() - LCS;

        System.out.println("Deletions : " + deletions);
        System.out.println("Insertions : " + insertions);
    }
}
