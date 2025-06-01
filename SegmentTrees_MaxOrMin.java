// First Refer SegmentTree_SumAndUpdate

public class SegmentTrees_MaxOrMin {
    static int[] TREE;

    static void init(int n) {
        TREE = new int[4 * n];
    }

    static int build(int[] arr, int STidx, int rangeBeg, int rangeEnd) {
        if (rangeBeg == rangeEnd) {
            return TREE[STidx] = arr[rangeBeg];
        }

        int mid = rangeBeg + (rangeEnd - rangeBeg) / 2;
        int left = build(arr, 2 * STidx + 1, rangeBeg, mid);
        int right = build(arr, 2 * STidx + 2, mid + 1, rangeEnd);

        return TREE[STidx] = Math.max(left, right);
    }

    static int getMaxInRangeUtil(int idx, int si, int sj, int qi, int qj) {
        if (sj < qi || si > qj) { // No Overlap
            return Integer.MIN_VALUE;
        } else if (si >= qi && sj <= qj) { // Complete Overlap
            return TREE[idx];
        } else { // Partial Overlap
            int mid = si + (sj - si) / 2;
            int left = getMaxInRangeUtil(2 * idx + 1, si, mid, qi, qj);
            int right = getMaxInRangeUtil(2 * idx + 2, mid + 1, sj, qi, qj);

            return Math.max(left, right);
        }
    }

    static int getMaxInRange(int[] arr, int qi, int qj) {
        int n = arr.length;
        return getMaxInRangeUtil(0, 0, n - 1, qi, qj);
    }

    static void updateUtil(int STidx, int si, int sj, int idx, int newVal) {
        if (idx < si || idx > sj) {
            return;
        }

        if (si == sj) {
            TREE[STidx] = newVal;
            return;
        }

        int mid = si + (sj - si) / 2;
        updateUtil(2 * STidx + 1, si, mid, idx, newVal);
        updateUtil(2 * STidx + 2, mid + 1, sj, idx, newVal);

        TREE[STidx] = Math.max(TREE[2 * STidx + 1], TREE[2 * STidx + 2]);
    }

    static void update(int[] arr, int idx, int newVal) {
        int n = arr.length;
        arr[idx] = newVal;
        updateUtil(0, 0, n - 1, idx, newVal);
    }

    public static void main(String[] args) {
        int[] arr = { 6, 8, -1, 2, 17, 1, 3, 2, 4 };

        init(arr.length);

        build(arr, 0, 0, arr.length - 1);

        for (int i = 0; i < TREE.length; i++) {
            System.out.print(TREE[i] + " ");
        }
        System.out.println();

        System.out.println(getMaxInRange(arr, 2, 5));

        update(arr, 2, 20);
        for (int i = 0; i < TREE.length; i++) {
            System.out.print(TREE[i] + " ");
        }
    }
}