// Given a text and a wildcard pattern, implement wildcard pattern matching algorithm that finds if wildcard pattern is matched with text. The matching should cover the entire text(not partial text). The wildcard pattern can include the characters '?' and '*'
/*
'?' - matches any single character
'*' - matches any sequence of characters (including the empty sequence)
*/
// LCS Variation

/*
Naive Recursive Solution:

We begin matching from the last characters of the both pattern and text. The following cases arise.

Case 1: The character is ‘*’ . Here two cases arises as follows:  

We can ignore ‘*’ character and move to next character in the Pattern.
We match ‘*’ with one or more characters in Text. Here we will move to next in the text.

Case 2: The character is ‘?’ 
We ignore current character in text and move to next character in the Pattern and Text.

Case 3: The character is not a wildcard character 
If current character in Text matches with current character in Pattern, we move to next character in the Pattern and Text. If they do not match, we return false.

*/
// Try Space Optimized one !!!!!!!!!
public class WildCardMatching_DP {

    static boolean isMatch(String t, String p) {
        int n = t.length();
        int m = p.length();
        // dp[i][j] will be true if t[0..i-1] matches p[0..j-1]
        boolean[][] dp = new boolean[n + 1][m + 1];

        // Empty pattern matches with empty string
        dp[0][0] = true;

        // pattern = "" !!!
        for (int i = 1; i <= n; i++) {
            dp[i][0] = false;
        }

        // text = "" !!!
        for (int j = 1; j <= m; j++) {
            if ((p.charAt(j - 1) >= 'a' && p.charAt(j - 1) <= 'z') || (p.charAt(j - 1) == '?')) {
                dp[0][j] = false;
            } else if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                /* If the current characters match, or 
                the pattern has a '?',
                inherit the result from the previous state. */

                if (t.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } 
                /* If the current character in the pattern is '*', we either:
                1. Ignore the '*' (dp[i][j-1]) or
                2. Match '*' with one character from the string (dp[i-1][j]) */
                else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = (dp[i - 1][j] || dp[i][j - 1]);
                }   
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        String text = "baaabab";
        String pattern = "****ba*?***ab";

        System.out.println(isMatch(text, pattern));
    }
}