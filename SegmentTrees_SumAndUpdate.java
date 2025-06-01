// Segment Tree is a versatile data structure used in computer science and data structures that allows efficient querying and updating of intervals or segments of an array. It is particularly useful for problems involving range queries, such as finding the sum, minimum, maximum, or any other operation over a specific range of elements in an array. The tree is built recursively by dividing the array into segments until each segment represents a single element. This structure enables fast query and update operations with a time complexity of O(log n), making it a powerful tool in algorithm design and optimization.

public class SegmentTrees_SumAndUpdate {
    static int[] TREE;

    static void init(int n) {
        TREE = new int[4 * n]; // 4 * n is just extra space !! Actually we only need ( 2 * n - 1 ) for a n size
                               // array (Check the Screenshot on Nov 26)
    }

    // O(n)
    static int build(int[] arr, int STidx, int rangeBeg, int rangeEnd) {
        if (rangeBeg == rangeEnd) {
            return TREE[STidx] = arr[rangeBeg];
        }

        int mid = rangeBeg + (rangeEnd - rangeBeg) / 2;

        int l = build(arr, 2 * STidx + 1, rangeBeg, mid); // Left child
        int r = build(arr, 2 * STidx + 2, mid + 1, rangeEnd); // Right child

        TREE[STidx] = l + r;

        return TREE[STidx];
    }

    // Must Dry Run
    // O(log n) (Base 2)
    static int getSumUtil(int idx, int si, int sj, int qi, int qj) {
        // Must check the screenshots on Nov 27
        if (qj < si || qi > sj) { // Non Overlapping Case
            return 0;
        } else if (si >= qi && sj <= qj) { // Completely Overlapping Case
            return TREE[idx];
        } else { // Partial Overlapping Case
            int mid = (si + sj) / 2;
            int left = getSumUtil(2 * idx + 1, si, mid, qi, qj);
            int right = getSumUtil(2 * idx + 2, mid + 1, sj, qi, qj);

            return left + right;
        }
    }

    static int getSum(int[] arr, int qi, int qj) {
        return getSumUtil(0, 0, arr.length - 1, qi, qj);
    }

    static void updateUtil(int i, int si, int sj, int idx, int diff) {
        if (idx > sj || idx < si) {
            return;
        }

        TREE[i] += diff;
        if (si == sj) { // Leaf Node
            return;
        }

        int mid = (si + sj) / 2;
        updateUtil(2 * i + 1, si, mid, idx, diff);
        updateUtil(2 * i + 2, mid + 1, sj, idx, diff);
    }

    static void update(int[] arr, int idx, int newVal) {
        int n = arr.length;

        int diff = newVal - arr[idx];
        arr[idx] = newVal;

        updateUtil(0, 0, n - 1, idx, diff);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8 };

        init(arr.length);
        build(arr, 0, 0, arr.length - 1);

        for (int i = 0; i < TREE.length; i++) {
            System.out.print(TREE[i] + " ");
        }

        System.out.println();
        System.out.println(getSum(arr, 2, 5));

        update(arr, 2, 2);
        for (int i = 0; i < TREE.length; i++) {
            System.out.print(TREE[i] + " ");
        }
    }
}