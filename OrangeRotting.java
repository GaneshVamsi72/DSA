// We have a matrix of dimension m*n where each cell in the matrix can have values 0, 1 or 2 which has the following meaning:
// 0: Empty cell 1: Cells have fresh oranges 2: Cells have rotten oranges
// We have to determine what is the minimum time required so that all the oranges become rotten. 
// A rotten orange at index [i, j] can rot other fresh oranges at indices [i-1, j], [i+1, j], [i, j-1], [i, j+1] (up, down, left and right).
// If it is impossible to rot every orange then simply return -1.

import java.util.LinkedList;
import java.util.Queue;

public class OrangeRotting {
    static class Pair {
        int i;
        int j;
        int t;

        public Pair(int i, int j, int t) {
            this.i = i;
            this.j = j;
            this.t = t;
        }
    }

    static int orangeRotting(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;

        int[][] vis = new int[n][m];
        int countFresh = 0;

        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 2) {
                    q.add(new Pair(i, j, 0));
                    vis[i][j] = 2;
                }

                if (arr[i][j] == 1) {
                    countFresh++;
                }
            }
        }

        int t = 0;
        int count = 0;
        while (!q.isEmpty()) {
            Pair p = q.remove();
            int i = p.i;
            int j = p.j;

            t = Math.max(t, p.t);

            if ((i - 1) >= 0 && (i - 1) < n && arr[i - 1][j] == 1 && vis[i - 1][j] == 0) {
                vis[i - 1][j] = 2;
                q.add(new Pair(i - 1, j, p.t + 1));
                count++;
            }

            if ((i + 1) >= 0 && (i + 1) < n && arr[i + 1][j] == 1 && vis[i + 1][j] == 0) {
                vis[i + 1][j] = 2;
                q.add(new Pair(i + 1, j, p.t + 1));
                count++;
            }

            if ((j - 1) >= 0 && (j - 1) < m && arr[i][j - 1] == 1 && vis[i][j - 1] == 0) {
                vis[i][j - 1] = 2;
                q.add(new Pair(i, j - 1, p.t + 1));
                count++;
            }

            if ((j + 1) >= 0 && (j + 1) < m && arr[i][j + 1] == 1 && vis[i][j + 1] == 0) {
                vis[i][j + 1] = 2;
                q.add(new Pair(i, j + 1, p.t + 1));
                count++;
            }
        }

        if (count != countFresh) {
            return -1;
        }

        return t;
    }

    public static void main(String[] args) {
        int[][] arr = { { 2, 1, 0, 2, 1 }, { 0, 0, 1, 2, 1 }, { 1, 0, 0, 2, 1 } };

        System.out.println(orangeRotting(arr));
    }
}