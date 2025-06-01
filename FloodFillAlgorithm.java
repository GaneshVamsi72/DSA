
public class FloodFillAlgorithm {
    // O(n * m)
    static void floodFillRecursive(int[][] image, boolean[][] vis, int row, int col, int startColor, int newColor) {
        if (row < 0 || row >= image.length || col < 0 || col >= image[0].length || image[row][col] != startColor || vis[row][col]) {
            return;
        }

        image[row][col] = newColor;
        vis[row][col] = true;

        floodFillRecursive(image, vis, row + 1, col, startColor, newColor);
        floodFillRecursive(image, vis, row - 1, col, startColor, newColor);
        floodFillRecursive(image, vis, row, col + 1, startColor, newColor);
        floodFillRecursive(image, vis, row, col - 1, startColor, newColor);
    }

    static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int startColor = image[sr][sc];

        if (startColor == color) {
            return image;
        }

        boolean[][] vis = new boolean[image.length][image[0].length];
        floodFillRecursive(image, vis, sr, sc, startColor, color);

        return image;
    }

    public static void main(String[] args) {
        int[][] image = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };

        int sr = 1;
        int sc = 1;
        int color = 2;

        image = floodFill(image, sr, sc, color);

        for (int[] a : image) {
            for (int i : a) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}