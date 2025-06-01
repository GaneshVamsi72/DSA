import java.util.TreeMap;

// Keys are sorted 
// put, get, remove are O(log n)
// Implemented using Red Black Trees (Self Balancing BST)

public class TreeMap_JCF {
    public static void main(String[] args) {
        TreeMap<String, Integer> tm = new TreeMap<>();
        tm.put("Ram", 3);
        tm.put("Sita", 4);
        tm.put("SitaRam", 7);

        System.out.println(tm);
    }
}
