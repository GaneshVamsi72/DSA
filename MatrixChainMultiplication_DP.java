// Given the dimension of a sequence of matrices in an array arr[], where the dimension of the ith matrix is (arr[i-1] * arr[i]), the task is to find the most efficient way to multiply these matrices together such that the total number of element multiplications is minimum.
// When two matrices of size m*n and n*p when multiplied, they generate a matrix of size m*p and the number of multiplications performed is m*n*p.

// Check the Screenshots on Nov 19, 20, 21 2024

import java.util.Arrays;

public class MatrixChainMultiplication_DP {

    // Matrix Ai has dimension arr[i-1] x arr[i]

    // O(2^n)
    static int MCM_Rec(int[] arr, int i, int j) {
        // If there is only one matrix
        if (i == j) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        // For every k, we make two subproblems : (a) Chain from i to k  (b) Chain from k + 1 to j
        for (int k = i; k < j; k++) {
            int cost1 = MCM_Rec(arr, i, k);
            int cost2 = MCM_Rec(arr, k + 1, j);

            // After the minCost of two subproblems get calculated, we have to calculate the cost of resultant matrix of those two subproblem matrices
            int cost3 = arr[i - 1] * arr[k] * arr[j];

            int cost = cost1 + cost2 + cost3;
            ans = Math.min(ans, cost);
        }
        
        return ans;
    }

    static void printDP(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // O(n^3)
    static int MCM_Memo(int[] arr, int i, int j, int[][] dp) {
        if (i == j) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int ans = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int cost1 = MCM_Memo(arr, i, k, dp);
            int cost2 = MCM_Memo(arr, k + 1, j, dp);
            int cost3 = arr[i - 1] * arr[k] * arr[j];

            int cost = cost1 + cost2 + cost3;
            ans = Math.min(ans, cost);
        }

        return dp[i][j] = ans;
    }

    // O(n ^ 3)
    static int MCM_Tab(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];

        // Refer Recursion Base Case !!!!!!
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 0; // Not required... But still !!!!!!
        }

        // One new thing about this is that dp array is initialized diagonally and after completion of filling process we will get to know that only upper triangle is filled !!!! (Because i <= j (always) (Converse is meaningless for this problem!!))
        // Check the ScreenShot on 21 Nov 24

        // Draw the Table and dry run for understanding Tabulation (Must !)

        /* 
        Explanation : (Bottom Up Approach)
        We have already computed for subproblem of length 1 (Base Case)
        We have to do this for each length-subproblem
            For a given length :
                Think about variation of starting point i and ending point j
                i can be (Gap of len must be maintained when we go near to end):-
                    1, 2, 3, ... (n - len)
                j can be (For a given i, j must be in such a way that i and j together become a subproblem of length len):- 
                    i + len - 1
                Now for a given (i, j) :
                    k must be varied from i + 1 to j - 1 such that current subproblem can be computed from previous subproblems
        */
        for (int len = 2; len < n; len++) {
            for (int i = 1; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost1 = dp[i][k];
                    int cost2 = dp[k + 1][j];
                    int cost3 = arr[i - 1] * arr[k] * arr[j];

                    int cost = cost1 + cost2 + cost3;
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        printDP(dp);

        return dp[1][n - 1];
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 3 }; // Contains 1 to (n - 1) matrices
        int n = arr.length;

        System.out.println("Using Recursion : ");
        System.out.println(MCM_Rec(arr, 1, n - 1));
        System.out.println();

        // dp[i][j] stores the minimum number of scalar multiplications needed to multiply the matrix chain from the i th matrix to the j th matrix
        int[][] dp = new int[n][n]; 
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println("Using Memoization : ");
        System.out.println(MCM_Memo(arr, 1, n - 1, dp));
        printDP(dp);

        System.out.println("Using Tabulation : ");
        System.out.println(MCM_Tab(arr));
    }
}