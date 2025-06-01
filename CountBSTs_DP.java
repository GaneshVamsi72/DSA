// Find the number of all possible BSTs with given n nodes.

// Must check the screenshot on 2 Nov 2024 which shows the relation between This and Catalan's Number !!!!!!!

// For all possible values of i, consider i as root, then [1 . . . i-1] numbers will fall in the left subtree and [i+1 . . . N] numbers will fall in the right subtree. 
// The count of all possible BST’s will be count(N) = summation of (count(i-1)*count(N-i)) where i lies in the range [1, N].

public class CountBSTs_DP {

    static int count(int n) {
        int[] dp = new int[n + 1];

        dp[0] = 1; // Empty tree
        dp[1] = 1; // Single Node tree

        // For all possible values of i, we consider i as root
        for (int i = 2; i < n + 1; i++) {
            // [1 . . . i-1] numbers will fall in the left subtree and [i+1 . . . N] numbers will fall in the right subtree
            for (int j = 0; j < i; j++) {
                int left = dp[j]; // Possible BSTs of left subtree nodes
                int right = dp[i - j - 1]; // Possible BSTs of right subtree nodes

                dp[i] += left * right;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 3; // (10, 20, 30) (Elements can be of any values and any order--> Result will be same)

        System.out.println(count(n));
    }
}