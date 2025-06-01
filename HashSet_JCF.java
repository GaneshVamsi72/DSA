// No duplicates 
// Unordered
// NULL is allowed
// Implemented using HashMap

import java.util.HashSet;
import java.util.Iterator;

public class HashSet_JCF {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();

        // O(1)
        set.add(2);
        set.add(3);
        set.add(3);
        set.add(1);
        set.add(0);
        set.add(5);
        set.add(5);
        set.add(9);

        System.out.println(set);

        // O(1)
        set.remove(0);

        System.out.println(set);

        // O(1)
        if (set.contains(0)) {
            System.out.println("Given set contains 0");
        } else {
            System.out.println("Given set does not contain 0");
        }

        System.out.print("Iteration : ");
        Iterator<Integer> itr = set.iterator();
        while(itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
    }
}
