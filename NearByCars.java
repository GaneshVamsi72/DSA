// We are given N points in a 2D co-ordinate plane which are locations of N cars. If we are at the origin, print the nearest K cars.

import java.util.PriorityQueue;

public class NearByCars {
    static class Point implements Comparable<Point> {
        int x;
        int y;
        int dsq;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.dsq = x * x + y * y;
        }

        @Override 
        public int compareTo(Point other) {
            return this.dsq - other.dsq;
        }
    }
    public static void main(String[] args) {
        int[][] pts = { { 3, 3 }, { 5, -1 }, { -2, 4 } };
        int k = 2;

        PriorityQueue<Point> pq = new PriorityQueue<>();

        for (int i = 0; i < pts.length; i++) {
            pq.add(new Point(pts[i][0], pts[i][1]));
        }

        for (int i = 0; i < k; i++) {
            Point p = pq.remove();
            System.out.println("(" + p.x + ",  " + p.y + ")");
        }
    }
}