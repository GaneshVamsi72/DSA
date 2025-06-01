import java.util.LinkedList;
import java.util.ArrayList;

public class HashMap_Implementation {

    public static void main(String[] args) {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        hm.put("Sita", 4);
        hm.put("Ram", 3);
        hm.put("SitaRam", 7);

        hm.remove("SitaRam");

        System.out.println(hm.keySet());
    }
}

// Worst Case - O(n) when HashFunction same index coincidentally - (Rare)
class HashMap<K, V> { // Generic - <K, V>
    private class Node {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int n; // Number of nodes
    private int N; // buckets.length
    private LinkedList<Node>[] buckets;

    @SuppressWarnings("unchecked")
    HashMap() {
        this.n = 0;
        this.N = 4;
        this.buckets = new LinkedList[4];
        for (int i = 0; i < 4; i++) {
            this.buckets[i] = new LinkedList<>();
        }
    }

    private int hashFunction(K key) {
        int hc = key.hashCode();
        return Math.abs(hc) % N;
    }

    private int SearchInLL(K key, int b_idx) {
        LinkedList<Node> ll = buckets[b_idx];
        int l_idx = 0;
        for (int i = 0; i < ll.size(); i++) {
            Node temp = ll.get(i);
            if (temp.key == key) {
                return l_idx;
            }
            l_idx++;
        }

        return -1;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        LinkedList<Node>[] oldBuckets = buckets;

        buckets = new LinkedList[2 * N];
        N = 2 * N;

        for (int i = 0; i < N; i++) {
            buckets[i] = new LinkedList<>();
        }

        for (int i = 0; i < oldBuckets.length; i++) {
            LinkedList<Node> ll = oldBuckets[i];
            for (int j = 0; j < ll.size(); j++) {
                Node node = ll.get(j);
                put(node.key, node.value);
            }
        }
    }

    // O(lambda) - O(1)
    public void put(K key, V value) {
        int b_idx = hashFunction(key);
        int l_idx = SearchInLL(key, b_idx);

        if (l_idx != -1) {
            Node node = buckets[b_idx].get(l_idx);
            node.value = value;
        } else {
            buckets[b_idx].add(new Node(key, value));
            n++;
        }

        double lambda = (double) n / N;

        if (lambda > 2.0) {
            rehash();
        }
    }

    // O(lambda) - O(1)
    public boolean containsKey(K key) {
        int b_idx = hashFunction(key);
        int l_idx = SearchInLL(key, b_idx);

        if (l_idx != -1) {
            return true;
        } else {
            return false;
        }
    }

    // O(lambda) - O(1)
    public V get(K key) {
        int b_idx = hashFunction(key);
        int l_idx = SearchInLL(key, b_idx);

        if (l_idx != -1) {
            Node node = buckets[b_idx].get(l_idx);
            return node.value;
        } else {
            return null;
        }
    }

    // O(lambda) - O(1)
    public V remove(K key) {
        int b_idx = hashFunction(key);
        int l_idx = SearchInLL(key, b_idx);

        if (l_idx != -1) {
            Node node = buckets[b_idx].remove(l_idx);
            n--;
            return node.value;
        } else {
            return null;
        }
    }

    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();

        for (int i = 0; i < buckets.length; i++) {
            LinkedList<Node> ll = buckets[i];
            for (int j = 0; j < ll.size(); j++) {
                Node node = ll.get(j);
                keys.add(node.key);
            }
        }

        return keys;
    }

    public boolean isEmpty() {
        return n == 0;
    }
}