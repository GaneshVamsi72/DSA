
// Find if any pair in a Sorted and Rotated ArrayList has a target sum.
import java.util.ArrayList;
// Two Pointer Aproach : 
// Find the break point where list.get(i) > list.get(i + 1)
// Assign lp = i + 1
// Assign rp = i

// Time Complexity : O(n)
public class PairSumInASortedAndRotatedArrayList {
    public static boolean PairSum(ArrayList<Integer> list, int target) {
        int bp = 0;
        while (list.get(bp) < list.get(bp + 1)) {
            bp++;
        }

        int lp = bp + 1;
        int rp = bp;

        int n = list.size();

        while (lp != rp) {
            int currSum = list.get(lp) + list.get(rp);
            if (currSum == target) {
                return true;
            } else if (currSum < target) {
                lp = (lp + 1) % n;
            } else {
                rp = (n + rp - 1) % n;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(15);
        list.add(6);
        list.add(8);
        list.add(9);
        list.add(10);

        int target = 16;

        System.out.println(PairSum(list, target));
    }
}
