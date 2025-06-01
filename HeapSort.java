// O(n * logn)

public class HeapSort {

    public static void heapify(int[] arr, int i, int size) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        int max_idx = i;

        if (left < size && arr[left] > arr[max_idx]) {
            max_idx = left;
        }
        if (right < size && arr[right] > arr[max_idx]) {
            max_idx = right;
        }

        if (max_idx != i) {
            int temp = arr[i];
            arr[i] = arr[max_idx];
            arr[max_idx] = temp;

            heapify(arr, max_idx, size);
        }
    }

    public static void Sort(int[] arr) {
        // 1.Build MaxHeap

        // Heapify all nodes other than leaf level nodes
        int n = arr.length;
        for (int i = n / 2; i >= 0; i--) {
            heapify(arr, i, n);
        }

        // 2.Push Largest to end one by one
        for (int i = n - 1; i > 0; i--) {
            // Swap root node and last node
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, 0, i);
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 0, 6, 3, 1, 8};

        Sort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
