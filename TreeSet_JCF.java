import java.util.TreeSet;

public class TreeSet_JCF {
    public static void main(String[] args) {

        TreeSet<Integer> set = new TreeSet<>();

        // O(log n)
        set.add(2);
        set.add(3);
        set.add(3);
        set.add(1);
        set.add(0);
        set.add(5);
        set.add(5);
        set.add(9);

        System.out.println(set);

        // O(log n)
        set.remove(0);

        System.out.println(set);
    }
}
