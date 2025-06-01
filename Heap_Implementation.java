
import java.util.ArrayList;

public class Heap_Implementation {

    static class MinHeap {

        ArrayList<Integer> arr = new ArrayList<>();

        public boolean isEmpty() {
            return arr.isEmpty();
        }

        // O(log n)
        public void add(int data) {
            // Append to the list
            arr.add(data);

            // Index of the recently appended child
            int x = arr.size() - 1;
            // Index of parent of the child
            int par = (x - 1) / 2;

            while (x > 0 && arr.get(x) < arr.get(par)) {
                // Swap
                int temp = arr.get(x);
                arr.set(x, arr.get(par));
                arr.set(par, temp);

                x = par;
                par = (x - 1) / 2;
            }
        }

        public int peek() {
            return arr.get(0);
        }

        // O(log n)
        private void heapify(int idx) {
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;

            int min_idx = idx;

            if (left < arr.size() && arr.get(min_idx) > arr.get(left)) {
                min_idx = left;
            }

            if (right < arr.size() && arr.get(min_idx) > arr.get(right)) {
                min_idx = right;
            }

            if (min_idx != idx) {
                int temp = arr.get(idx);
                arr.set(idx, arr.get(min_idx));
                arr.set(min_idx, temp);

                heapify(min_idx);
            }
        }

        // O(log n)
        public int remove() {
            int data = arr.get(0);

            // Swap root node and last node
            int temp = arr.get(0);
            arr.set(0, arr.get(arr.size() - 1));
            arr.set(arr.size() - 1, temp);

            // Delete the last node
            arr.remove(arr.size() - 1);

            heapify(0);

            return data;
        }
    }

    public static void main(String[] args) {
        MinHeap h = new MinHeap();

        h.add(5);
        h.add(0);
        h.add(6);
        h.add(3);
        h.add(1);
        h.add(8);

        while (!h.isEmpty()) {
            System.out.print(h.peek() + " ");
            h.remove();
        }
    }
}
