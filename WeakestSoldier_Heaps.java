
import java.util.PriorityQueue;

// We are given an m x n binary matrix of 1's (soldiers) and 0's (civilians). The soldiers are positioned in front of the civilians. That is, all the 1's will appear to the left of all the 0's in each row.
// A row i is weaker than a row j if one of the following is true :
// 1. The number of soldiers in row i is less than the number of soldiers in row j.
// 2. Both rows have the same number of soldiers and i < j.
// Find the K weakest rows.

public class WeakestSoldier_Heaps {
    static class RowInfo implements Comparable<RowInfo> {
        int count;
        int idx;

        public RowInfo(int c, int i) {
            count = c;
            idx = i;
        }

        @Override
        public int compareTo(RowInfo other) {
            if (this.count != other.count) {
                return this.count - other.count;
            } else {
                return this.idx - other.idx;
            }
        }
    }

    public static void main(String[] args) {
        int[][] army = { { 1, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 1, 0, 0, 0 },
                { 1, 0, 0, 0 } };
        int k = 2;
        PriorityQueue<RowInfo> pq = new PriorityQueue<>();
        for (int i = 0; i < army.length; i++) {
            int count = 0;
            for (int j = 0; j < army[0].length; j++) {
                if (army[i][j] != 0) {
                    count++;
                } else {
                    break;
                }
            }
            pq.add(new RowInfo(count, i));
        }

        for (int i = 0; i < k; i++) {
            RowInfo r = pq.remove();
            System.out.println("Row " + r.idx);
        }
    }
}