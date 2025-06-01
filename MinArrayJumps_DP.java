// Given an array arr[] where each element represents the max number of steps that can be made forward from that index. The task is to find the minimum number of jumps to reach the end of the array starting from index 0.

import java.util.Arrays;

public class MinArrayJumps_DP {

    static int minJumps(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n]; // dp[i] represents min number of jumps required to reach (n - 1) index from i th index
        Arrays.fill(dp, -1);

        dp[n - 1] = 0; // Min number of jumps required to reach n-1 from n-1

        for (int i = n - 2; i >= 0; i--) {
            int steps = nums[i];
            int ans = Integer.MAX_VALUE;
            for (int j = i + 1; j <= i + steps && j < n; j++) {
                if (dp[j] != -1) {
                    ans = Math.min(ans, dp[j] + 1);
                }
            }
            if (ans != Integer.MAX_VALUE) {
                dp[i] = ans;
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        int[] nums = { 2, 3, 1, 1, 4 };

        System.out.print(minJumps(nums));
    }
}