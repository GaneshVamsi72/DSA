import java.util.ArrayList;
import java.util.Collections;

public class ArrayList_Basic {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        // Add Element - O(1)
        System.out.println("Adding an element : ");
        list.add(8);
        list.add(3);
        list.add(5);
        list.add(0);

        System.out.println(list);

        // Adding an element at an specific positon - O(n)
        System.out.println("Adding an element at a specific position : ");
        list.add(3, 6);
        list.add(2, 1);

        System.out.println(list);

        // Get Element - O(1)
        System.out.println("Getting an element : ");
        System.out.println(list.get(2));
        System.out.println(list.get(3));

        // Remove Element - O(n)
        System.out.println("Removing an element : ");
        System.out.println(list.remove(2));
        System.out.println(list.remove(3));

        System.out.println(list);

        // Set Element at Index - O(n)
        System.out.println("Setting an element at a given index : ");
        list.set(2, 6);

        System.out.println(list);

        // Contains Element - O(n)
        System.out.println("Checking whether the list contains the given element : ");
        System.out.println(list.contains(3));
        System.out.println(list.contains(9));

        // Size of an ArrayList
        System.out.print("Size of the list is : ");
        System.out.println(list.size());

        // Sorting an ArrayList
        System.out.println("Ascending list : ");
        Collections.sort(list);
        System.out.println(list);

        System.out.println("Descending list : ");
        Collections.sort(list, Collections.reverseOrder());
        System.out.println(list);

        // Multidimensional ArrayLists
        System.out.println("MultiDimensional ArrayList Implementation : ");
        ArrayList<ArrayList<Integer>> mainList = new ArrayList<>();

        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(5);
        list1.add(0);
        mainList.add(list1);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(6);
        list2.add(3);
        mainList.add(list2);

        ArrayList<Integer> list3 = new ArrayList<>();
        list3.add(1);
        list3.add(8);
        mainList.add(list3);

        System.out.println(mainList);

        for (int i = 0; i < mainList.size(); i++) {
            ArrayList<Integer> currList = mainList.get(i);
            System.out.print("List " + (i + 1) + " : ");
            for (int j = 0; j < currList.size(); j++) {
                System.out.print(currList.get(j) + " ");
            }
            System.out.println();
        }
    }
}