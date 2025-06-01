import java.util.ArrayList;
import java.util.Collections;

public class KruskalAlgorithm {
    static class Edge implements Comparable<Edge> {
        int src;
        int dst;
        int wt;

        public Edge(int s, int d, int w) {
            src = s;
            dst = d;
            wt = w;
        }

        @Override
        public int compareTo(Edge other) {
            return this.wt - other.wt;
        }
    }

    static void createGraph(ArrayList<Edge> edges) {
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 15));
        edges.add(new Edge(0, 3, 30));
        edges.add(new Edge(1, 3, 40));
        edges.add(new Edge(2, 3, 50));
    }

    static int n = 4;
    static int[] par = new int[n];
    static int[] rank = new int[n];

    static void init() {
        for (int i = 0; i < n; i++) {
            par[i] = i;
        }
    }

    static int find(int x) {
        if (par[x] == x) {
            return x;
        }

        return par[x] = find(par[x]);
    }

    static void union(int a, int b) {
        int parA = find(a);
        int parB = find(b);

        if (rank[parA] == rank[parB]) {
            par[parB] = parA;
            rank[parA]++;
        } else if (rank[parA] < rank[parB]) {
            par[parA] = parB;
        } else {
            par[parB] = parA;
        }
    }

    // O(V + E * log E)
    static void minCostMST(ArrayList<Edge> edges, int V) {
        Collections.sort(edges); // O(E * log E)
        int finalCost = 0;
        int count = 0;

        init();

        // O(V)
        for (Edge e : edges) {
            if (find(e.src) != find(e.dst)) {
                union(e.src, e.dst);
                finalCost += e.wt;
                count++;
            }
            if (count == V - 1)
                break;
        }

        System.out.println("Minimum cost MST : " + finalCost);
    }

    public static void main(String[] args) {
        int V = 4;
        ArrayList<Edge> edges = new ArrayList<>();
        createGraph(edges);

        minCostMST(edges, V);
    }
}