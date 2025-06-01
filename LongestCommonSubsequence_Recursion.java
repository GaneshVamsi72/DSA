// A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

// Important !!!!!!
// Naive Approach 
/*
The idea is to compare the last two characters. While comparing the strings S1 and S2 two cases arise:

1. Match : Make the recursion call for the remaining strings (strings of lengths m-1 and n-1) and add 1 to result.
2. Do not Match : Make two recursive calls. First for lengths m-1 and n, and second for m and n-1. Take the maximum of two results. 
Base case : If any of the strings become empty, we return 0.
 */
public class LongestCommonSubsequence_Recursion {

    static int LCS(String str1, String str2, int n, int m) {
        if (n == 0 || m == 0) {
            return 0;
        } else if (str1.charAt(n - 1) == str2.charAt(m - 1)) {
            return 1 + LCS(str1, str2, n - 1, m - 1);
        } else {
            return Math.max(LCS(str1, str2, n - 1, m), LCS(str1, str2, n, m - 1));
        }
    }

    public static void main(String[] args) {
        String str1 = "abcdge";
        String str2 = "abedg";

        System.out.println(LCS(str1, str2, str1.length(), str2.length()));
    }
}
