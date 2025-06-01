
// For given n lines on x-axis, use 2 lines to form a container such that it holds maximum water.
// Noted in Book!!
// Brute Force Approach - Checking every possible pair!!!
import java.util.ArrayList;

// 2 Pointer Approach
// Time Complexity - O(n)
public class ContainerWithMostWater_2PointerApproach {
    public static int BigContainerWater(ArrayList<Integer> height) {
        int maxWater = 0;
        int lp = 0;
        int rp = height.size() - 1;

        while (lp < rp) {
            int currWater = (rp - lp) * Math.min(height.get(lp), height.get(rp));
            maxWater = Math.max(maxWater, currWater);

            if (height.get(lp) < height.get(rp)) {
                lp++;
            }
            else {
                rp--;
            }
        }

        return maxWater;
    }

    public static void main(String[] args) {
        ArrayList<Integer> height = new ArrayList<>();
        height.add(1);
        height.add(8);
        height.add(6);
        height.add(2);
        height.add(5);
        height.add(4);
        height.add(8);
        height.add(3);
        height.add(7);

        System.out.println("Big Container Water = " + BigContainerWater(height));
    }
}