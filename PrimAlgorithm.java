import java.util.ArrayList;
import java.util.PriorityQueue;

public class PrimAlgorithm {
    static class Edge {
        int src;
        int dest;
        int wt;

        public Edge (int s, int d, int w) {
            src = s;
            dest = d;
            wt = w;
        }
    }

    static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1, 10));
        graph[0].add(new Edge(0, 2, 15));
        graph[0].add(new Edge(0, 3, 30));

        graph[1].add(new Edge(1, 0, 10));
        graph[1].add(new Edge(1, 3, 40));

        graph[2].add(new Edge(2, 0, 15));
        graph[2].add(new Edge(2, 3, 50));

        graph[3].add(new Edge(3, 1, 40));
        graph[3].add(new Edge(3, 2, 50));

    }

    static class Pair implements Comparable<Pair> {
        int par;
        int v;
        int cost;

        public Pair(int par, int v, int cost) {
            this.par = par;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair other) {
            return this.cost - other.cost;
        }
    }

    static void MSTcost(ArrayList<Edge>[] graph) {
        boolean[] mstSet = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.add(new Pair(-1, 0, 0));
        int finalCost = 0;
        ArrayList<Edge> MST = new ArrayList<>();

        while (!pq.isEmpty()) {
            Pair curr = pq.remove();

            if (!mstSet[curr.v]) {
                mstSet[curr.v] = true;
                finalCost += curr.cost;

                // Add the edge to the MST (ignore the first edge as it has no parent)
                if (curr.cost != 0) {
                    MST.add(new Edge(curr.par, curr.v, curr.cost));
                }

                for (int i = 0; i < graph[curr.v].size(); i++) {
                    Edge e = graph[curr.v].get(i);
                    if (!mstSet[e.dest]) {
                        pq.add(new Pair(e.src, e.dest, e.wt));
                    }
                }
            }
        }

        System.out.println("Final Cost : " + finalCost);

        System.out.println("Edges in MST : ");
        for (Edge e : MST) {
            System.out.println(e.src + " - " + e.dest + " : " + e.wt);
        }
    }

    public static void main(String[] args) {
        int V = 4;
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V]; 
        createGraph(graph);

        MSTcost(graph);
    }
}