// Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
// Have to understand better !!

// Naive Recursion Approach:
/*
At every position, we determine the maximum possible side length for a square if we consider that position as the top left corner.
Therefore, we can calculate the total number of squares as sum of these lengths
*/

// Why this DP Approach works
/*
1. If any of the three adjacent positions has a smaller value, we can't form a larger square
2. Adding 1 accounts for the current cell itself
 */
// Final Answer
/*
1. The sum of all values in the DP table gives the total count of all possible squares
2. Each value in dp[i][j] contributes to that many squares ending at position (i,j)
 */
public class SquareSubMatricesCount_DP {
    static int countSquares(int[][] matrix) {
        // dp[i][j] represents the size of the largest square submatrix ending at position (i,j)
        int[][] dp = new int[matrix.length][matrix[0].length];

        int ans = 0;
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = matrix[i][0];
        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = matrix[0][j];
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                } else {
                    // If matrix[i][j] == 0, Then there can be no square submatrix formed

                    dp[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                ans += dp[i][j];
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 0, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 1, 1, 1 }
        };

        System.out.print(countSquares(matrix));
    }
}
