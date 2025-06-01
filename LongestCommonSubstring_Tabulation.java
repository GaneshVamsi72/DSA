// A substring is a contiguous sequence of characters within a string

/*
The longest common suffix has following optimal substructure property

If last characters match, then we reduce both lengths by 1 
    LCSuff(s1, s2, m, n) = LCSuff(s1, s2, m-1, n-1) + 1 if s1[m-1] = s2[n-1] 
If last characters do not match, then result is 0, i.e., 
    LCSuff(s1, s2, m, n) = 0 if (s1[m-1] != s2[n-1])
Now ,we consider suffixes of different substrings ending at different indexes. 
The maximum length Longest Common Suffix is the longest common substring. 
LCSubStr(s1, s2, m, n) = Max(LCSuff(s1, s2, i, j)) where 1 <= i <= m and 1 <= j <= n 
 */
public class LongestCommonSubstring_Tabulation {
    static int LCS(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n + 1][m + 1];
        int res = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    res = Math.max(res, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return res;
    }

    static int LCSSpaceOptimized(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        // This 1-D array itself can be used for simulating prev and curr rows
        int[] prev = new int[m + 1];
        int res = 0;
        for (int i = 1; i <= n; i++) {
            int[] curr = new int[m + 1];
            for (int j = 1; j < m + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];
                    res = Math.max(res, curr[j]);
                } else {
                    curr[j] = 0;
                }
            }
            // Moving the curr row's data to the prev row
            prev = curr;
        }

        return res;
    }

    public static void main(String[] args) {
        String str1 = "ABCDGH";
        String str2 = "ACDGHR";

        System.out.println(LCS(str1, str2)); // CDGH
        System.out.println(LCSSpaceOptimized(str1, str2));
    }
}