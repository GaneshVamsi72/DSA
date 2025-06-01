// You are given an integer array list nums. A number x is lonely when it appears only once, and no adjacent numbers (i.e. x + 1 and x - 1) appear in the arraylist. Return all lonely numbers in nums. You may return the answer in any order.

import java.util.ArrayList;
import java.util.Collections;

public class LonelyNumbersInArrayList {
    public static ArrayList<Integer> findLonely(ArrayList<Integer> list) {
        ArrayList<Integer> newList = new ArrayList<>();
        Collections.sort(list);
        for (int i = 1; i < list.size() - 1; i++) {
            if (list.get(i - 1) + 1 < list.get(i) && list.get(i) + 1 < list.get(i + 1)) {
                newList.add(list.get(i));
            }
        }
        if (list.size() == 1) {
            newList.add(list.get(0));
        }
        if (list.size() > 1) {
            if (list.get(0) + 1 < list.get(1)) {
                newList.add(list.get(0));
            }
            if (list.get(list.size() - 2) + 1 < list.get(list.size() - 1)) {
                newList.add(list.get(list.size() - 1));
            }
        }

        return newList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(3);

        System.out.println(findLonely(list));
    }
}
