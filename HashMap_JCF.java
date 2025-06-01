import java.util.HashMap;
import java.util.Set;

public class HashMap_JCF {
    public static void main(String[] args) {
        HashMap<String, Integer> hm = new HashMap<>();

        // Insert - O(1)
        hm.put("Ram", 3);
        hm.put("Sita", 4);
        hm.put("SitaRam", 7);

        System.out.println(hm);

        // Get - O(1)
        int value = hm.get("Ram");
        System.out.println(value);
        System.out.println(hm.get("Rama"));

        // ContainsKey - O(1)
        System.out.println(hm.containsKey("Raghav"));
        System.out.println(hm.containsKey("Ram"));

        // remove - O(1)
        System.out.println(hm.remove("SitaRam"));
        System.out.println(hm.remove("Raghav"));
        System.out.println(hm);

        System.out.println(hm.size());

        System.out.println(hm.isEmpty());

        // Iteration
        Set<String> keys = hm.keySet();

        for (String k : keys) {
            System.out.println("Key = " + k + ", " + "Value = " + hm.get(k));
        }

        hm.clear();
    }
}