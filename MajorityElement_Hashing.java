
// Given an integer array of size n, find all elements that appear more than n / 3 times.
import java.util.HashMap;
import java.util.Set;

// Time Complexity : O(n)
// Space Complexity : O(n)
public class MajorityElement_Hashing {
    public static void main(String[] args) {
        int[] num = { 1, 3, 2, 5, 1, 3, 1, 5, 1 };

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i : num) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }

            // Above five lines can be replaced by following line.
            // map.put(i, map.getOrDefault(i, 0) + 1)
        }

        Set<Integer> keys = map.keySet();
        for (Integer key : keys) {
            int val = map.get(key);
            if (val > num.length / 3) {
                System.out.println(key + " -> " + val);
            }
        }
    }
}
