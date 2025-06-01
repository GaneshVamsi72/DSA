
import java.util.LinkedHashMap;

// Keys insertion order is maintained
// Doubly Linked list is used for implementation
// Same as HashMap

public class LinkedHashMap_JCF {
    public static void main(String[] args) {
        LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>();
        lhm.put("Ram", 3);
        lhm.put("Sita", 4);
        lhm.put("SitaRam", 7);

        System.out.println(lhm);
    }
}