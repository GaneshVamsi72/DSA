
import java.util.PriorityQueue;

public class ConnectingCitiesWithMinimumCost {
    static class Pair implements Comparable<Pair> {
        int dst;
        int cst;

        public Pair(int dst, int cst) {
            this.dst = dst;
            this.cst = cst;
        }

        @Override
        public int compareTo(Pair other) {
            return this.cst - other.cst;
        }
    }

    static int minCostConnection(int[][] cities) {
        boolean[] mstSet = new boolean[cities.length];

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, 0));
        int cost = 0;

        while (!pq.isEmpty()) {
            Pair curr = pq.remove();

            if (!mstSet[curr.dst]) {
                mstSet[curr.dst] = true;
                cost += curr.cst;

                for (int i = 0; i < cities[curr.dst].length; i++) {
                    if (cities[curr.dst][i] != 0 && !mstSet[i]) {
                        pq.add(new Pair(i, cities[curr.dst][i]));
                    }
                }
            }
        }

        return cost;
    }

    public static void main(String[] args) {
        int[][] cities = { { 0, 1, 2, 3, 4 },
                { 1, 0, 5, 0, 7 },
                { 2, 5, 0, 6, 0 },
                { 3, 0, 6, 0, 0 },
                { 4, 7, 0, 0, 0 } };

        System.out.println(minCostConnection(cities));
    }
}