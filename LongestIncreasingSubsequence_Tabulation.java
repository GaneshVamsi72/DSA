import java.util.Arrays;
import java.util.HashSet;

public class LongestIncreasingSubsequence_Tabulation {

    static int LongestCommonSubsequence(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int m = arr2.length;

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[n][m];
    }

    static int LIS(int[] arr1) {
        HashSet<Integer> set = new HashSet<>();

        for (int i : arr1) {
            set.add(i);
        }

        int[] arr2 = new int[set.size()]; // For storing Sorted Unique Elements

        int i = 0;
        for (int num : set) {
            arr2[i++] = num;
        }

        Arrays.sort(arr2);

        // Because Longest Common Subsequence of arr1 and arr2(Sorted Unique Elements Array) will be the Longest Increasing Subsequence of arr1.
        return LongestCommonSubsequence(arr1, arr2);
    }

    public static void main(String[] args) {
        int[] arr = { 50, 3, 10, 7, 40, 80 };

        System.out.println(LIS(arr));
    }
}
