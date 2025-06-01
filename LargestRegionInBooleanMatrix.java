// We have a matrix where each cell contains either a '0' or a '1', and any cell containing a 1 is called a filled cell. Two cells are said to be connected if they are adjacent to each other horizontally, vertically, or diagonally. If one or more filled cells are also connected, they form a region, find the size of the largest region.

public class LargestRegionInBooleanMatrix {
    static int ROW, COL, count;

    static boolean isSafe(int[][] M, int row, int col, boolean[][] vis) {
        return ((row >= 0) && (row < ROW) && (col >= 0) && (col < COL) && (M[row][col] == 1 && !vis[row][col]));
    }

    static void DFS(int[][] M, int row, int col, boolean[][] vis) {
        int[] rowNbr = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] colNbr = { -1, 0, 1, -1, 1, -1, 0, 1 };

        vis[row][col] = true;

        for (int i = 0; i < 8; i++) {
            if (isSafe(M, row + rowNbr[i], col + colNbr[i], vis)) {
                count++;
                DFS(M, row + rowNbr[i], col + colNbr[i], vis);
            }
        }
    }

    static int largestRegion(int[][] M) {
        boolean[][] vis = new boolean[ROW][COL];
        int res = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (M[i][j] == 1 && !vis[i][j]) {
                    count = 1;
                    DFS(M, i, j, vis);
                    res = Math.max(res, count);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] M = { { 0, 0, 1, 1, 0 },
                     { 1, 0, 1, 1, 0 }, 
                     { 0, 1, 0, 0, 0 }, 
                     { 0, 0, 0, 0, 1 } };
        ROW = 4;
        COL = 5;
        System.out.println(largestRegion(M));
    }
}
