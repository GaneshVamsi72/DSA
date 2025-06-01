// A subarray is a contiguous part of array

// Sum(0, j) - Sum(0, i - 1) = Sum(i, j)
// ==> Sum(0, j) - Sum(0, i - 1) = k
// ==> Sum(0, j) - k = Sum(0, i - 1)

// Difficult one to understand !!! (Did not get it!!!)
// Take the help of Chat Gpt

import java.util.HashMap;

public class SubArraySumEqualToK_Hashing {
    public static void main(String[] args) {
        int[] arr = { 10, 2, -2, -20, 10 };
        int k = -10;

        // HashMap of Key: Sum, Value: Count
        HashMap<Integer, Integer> map = new HashMap<>();
        
        map.put(0, 1); // Why ????????????
        /*
        The reason for this is that if at any point currSum is exactly equal to k, you want to count that subarray. In this case, the subarray starts at index 0, so the sum before this subarray is 0.
        Adding map.put(0, 1) ensures that you account for subarrays that start from index 0 when currSum equals k. 
        */

        int currSum = 0;
        int ans = 0;

        for (int j = 0; j < arr.length; j++) {
            currSum += arr[j];
            // This simulates the iteration over subarray sums of current sum subarray 
            if (map.containsKey(currSum - k)) {
                ans += map.get(currSum - k);
            }
            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        }

        System.out.println(ans);
    }
}
