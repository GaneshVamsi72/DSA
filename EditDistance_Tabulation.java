// String Editing
// Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
// You have the following three operations permitted on a word:
// 1. Insert: Insert any character before or after any index of s1
// 2. Remove: Remove a character of s1
// 3. Replace: Replace a character at any index of s1 with some other character.

public class EditDistance_Tabulation {

    static int minOperations(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int addOp = 1 + dp[i][j - 1]; // Adding the current character in s2 to s1 and using result from dp array for previous part of the strings
                    int delOp = 1 + dp[i - 1][j]; // Deleting the current character in s1 and using result from dp array for previous part of the strings
                    int repOp = 1 + dp[i - 1][j - 1]; // Replacing the current character in s1 and using result from dp array for previous part of the strings

                    dp[i][j] = Math.min(addOp, Math.min(delOp, repOp));
                }
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        String str1 = "intention";
        String str2 = "execution";

        System.out.println(minOperations(str1, str2));
    }
}