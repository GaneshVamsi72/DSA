
// Find if any pair in a Sorted ArrayList has a target sum.
// Still have to really understand this approach.
import java.util.ArrayList;

public class PairSum_2PointerApproach {

    public static boolean PairSum(ArrayList<Integer> list, int target) {
        int lp = 0;
        int rp = list.size() - 1;

        while (lp != rp) {
            int currSum = list.get(lp) + list.get(rp);
            if (currSum == target) {
                return true;
            }

            if (currSum < target) {
                lp++;
            }

            if (currSum > target) {
                rp--;
            }
        }
        
        return false;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        int target = 4;

        System.out.println(PairSum(list, target));
    }
}