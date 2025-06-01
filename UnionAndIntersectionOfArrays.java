import java.util.HashSet;

public class UnionAndIntersectionOfArrays {
    
    public static void main(String[] args) {
        int[] arr1 = { 7, 3, 9 };
        int[] arr2 = { 6, 3, 9, 2, 9, 4 };
        HashSet<Integer> set = new HashSet<>();
        
        // Union - O(n + m)
        for (int i = 0; i < arr1.length; i++) {
            set.add(arr1[i]);
        }
        for (int i = 0; i < arr2.length; i++) {
            set.add(arr2[i]);
        }

        System.out.println("Union : " + set.size() + " " + set);

        set.clear();

        // Intersection - O(n + m)
        int count = 0;
        for (int i = 0; i < arr1.length; i++) {
            set.add(arr1[i]);
        }
        HashSet<Integer> intersect = new HashSet<>();
        for (int i = 0; i < arr2.length; i++) {
            if (set.contains(arr2[i])) {
                count++;
                set.remove(arr2[i]);
                intersect.add(arr2[i]);
            }
        }

        System.out.println("Intersection : " + count + " " + intersect);
    }
}
