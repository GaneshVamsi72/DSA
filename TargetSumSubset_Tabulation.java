// Variation of 0-1 Knapsack
// First Refer Recursion method somehow and then proceed
// Given a set of non-negative integers and a value sum, the task is to check if there is a subset of the given set whose sum is equal to the given sum.

// For the recursive approach, there will be two cases. 
// 1. Consider the ‘last’ element to be a part of the subset. Now the new required sum = required sum – value of ‘last’ element.
// 2. Don’t include the ‘last’ element in the subset. Then the new required sum = old required sum.
// In both cases, the number of available elements decreases by 1.

// If last element is greater than sum, then ignore it.
// Else, check if sum can be obtained by any of the following
// (a) including the last element
// (b) excluding the last element

// To solve the problem we can use the Dynamic programming approach(Tabulation).
// So we will create a 2D array of size (n + 1) * (sum + 1) of type boolean. 
// The state dp[i][j] will be true if there exists a subset of elements from set[0 . . . i] with sum value = ‘j’.

// O(n * target)
public class TargetSumSubset_Tabulation {
    static boolean isSubsetSum(int[] num, int target) {
        int n = num.length;
        boolean[][] dp = new boolean[n + 1][target + 1];

        // 0 th col will be filled with true... Because there will be always empty
        // subset for making target = 0
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        // 0 th row will be filled with false(except dp[0][0])... Because we can never
        // make target with 0 items except for target = 0

        // Filling dp in bottom up manner
        for (int i = 1; i < dp.length; i++) {
            int num_i = i - 1;
            for (int j = 1; j < dp[0].length; j++) {
                if (num[num_i] > j) { // Can not be taken
                    dp[i][j] = dp[i - 1][j];
                } else { // Can be taken
                    boolean include = dp[i - 1][j - num[num_i]];
                    boolean exclude = dp[i - 1][j];

                    dp[i][j] = include || exclude;
                }
            }
        }

        return dp[n][target];
    }

    public static void main(String[] args) {
        int[] num = { 4, 2, 7, 1, 3 };
        int target = 10;

        System.out.println(isSubsetSum(num, target));
    }
}