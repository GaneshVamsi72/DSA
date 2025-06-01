// *Important !!!!*
// You are given n activities with their start and end times. Select the maximum number of activities that can be performed by a single person, assuming that a person can only work on a single activity at a time.
// Activities are sorted according to end time.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ActivitySelection {

    public static void main(String[] args) {
        int[] start = {0, 1, 3, 5, 5, 8};
        int[] end = {6, 2, 4, 7, 9, 9};

        int activites[][] = new int[start.length][3];
        for (int i = 0; i < start.length; i++) {
            activites[i][0] = i;
            activites[i][1] = start[i];
            activites[i][2] = end[i];
        }

        Arrays.sort(activites, Comparator.comparingDouble(o -> o[2]));

        ArrayList<Integer> ans = new ArrayList<>();

        int maxAct = 1;
        ans.add(activites[0][0]);
        int prevAct = activites[0][2];
        for (int i = 1; i < activites.length; i++) {
            // If this activity has start time greater than or equal to the finish time of previously selected activity, then select it
            if (prevAct <= activites[i][1]) {
                maxAct++;
                ans.add(activites[i][0]);
                prevAct = activites[i][2];
            }
        }

        System.out.println("Max number of activities that can be preformed by a single person is " + maxAct);
        System.out.print("Sequence of activites : ");
        for (int i = 0; i < ans.size(); i++) {
            System.out.print("A" + ans.get(i) + " ");
        }
    }
}
