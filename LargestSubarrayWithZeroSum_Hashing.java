// A subarray is a contiguous part of array
/*
The idea is based on the observation that for two different indices i and j (where j > i) if the prefix sums Si and Sj are equal, it means that the sum of the elements between indices i+1 and j is zero. This is because:

Sj – Si = arr[i+1] + arr[i+2] + …… + arr[j]
If Si = Sj, then: Sj – Si = 0
This means the subarray from i+1 to j has a sum of zero.
 */
import java.util.HashMap;

public class LargestSubarrayWithZeroSum_Hashing {
    public static void main(String[] args) {
        int[] arr = { 15, -2, 2, -8, 1, 7, 10 };

        // HashMap of Key : Sum, Value : Idx 
        HashMap<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        int len = 0;

        for (int j = 0; j < arr.length; j++) {
            sum += arr[j];
            if (map.containsKey(sum)) {
                len = Math.max(len, j - map.get(sum));
            } else {
                map.put(sum, j);
            }
        }

        System.out.println(len);
    }
}