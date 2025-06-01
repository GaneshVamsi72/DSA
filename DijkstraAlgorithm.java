// Time Complexity : O((V + E)log V)
// Here's why Dijkstra's algorithm has the time complexities mentioned:

// 1. Using a Binary Heap (common approach):
// Priority Queue Operations: Dijkstra's algorithm relies on a priority queue to pick the next vertex with the smallest distance. If you use a binary heap, extracting the minimum (the vertex with the smallest distance) takes ( O(log V) ) time. This operation happens ( V ) times (once for each vertex).
// Edge Relaxation: For each edge ( (u, v) ), we need to potentially update the distance to vertex ( v ). This involves a decrease-key operation in the priority queue, which also takes ( O(log V) ) time. Since there are ( E ) edges, the total time spent on edge relaxation is ( O(E log V) ).

// Combining these gives:
// ( V ) extractions from the heap, each taking ( O(log V) ): ( O(V log V) ).
// ( E ) edge relaxations, each taking ( O(log V) ): ( O(E log V) ).

// So, the overall time complexity is ( O((V + E) log V) ).

// 2. Using a Fibonacci Heap (more efficient for dense graphs):
// The Fibonacci heap reduces the time for the decrease-key operation to ( O(1) ) (amortized), while extracting the minimum still takes ( O(log V) ).
// As a result, the edge relaxation now takes ( O(E) ), and the extractions still take ( O(V log V) ).

// This reduces the time complexity to ( O(V log V + E) ), which is more efficient when the graph is dense (i.e., ( E ) is large).

/* Simple Explanation of Time Complexity : O((V + E) * log V)
🧠 Where Time is Spent?
For each node you process, it comes out of PQ:
→ That’s V times, and removing from PQ takes log V time
→ So: V * log V

For each edge, you may try to relax it (update the neighbor):
→ That’s E edges, and if it leads to better path, you add to PQ again
→ So: E * log V

🎉 So FEEL it like this:
Every time you "spread the shortest message", it’s like picking a node and shouting “I found a better path!”

That shout costs log V energy

You can shout from each node once, and you check each path (edge) once
*/
import java.util.ArrayList;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {
    static class Edge {
        int src;
        int dest;
        int wt;

        public Edge(int s, int d, int w) {
            src = s;
            dest = d;
            wt = w;
        }
    }

    static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));

        graph[1].add(new Edge(1, 2, 1));
        graph[1].add(new Edge(1, 3, 7));

        graph[2].add(new Edge(2, 4, 3));

        graph[3].add(new Edge(3, 5, 1));

        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 5));
    }

    static class Pair implements Comparable<Pair> {
        int v;
        int path;

        public Pair(int V, int p) {
            v = V;
            path = p;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.path - p2.path;
        }
    }

    static void ShortestPaths(ArrayList<Edge>[] graph, int src) {
        int[] dist = new int[graph.length];
        dist[src] = 0;
        for (int i = 0; i < dist.length; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        boolean[] vis = new boolean[graph.length];

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.remove();

            if (!vis[curr.v]) {
                vis[curr.v] = true;

                for (int i = 0; i < graph[curr.v].size(); i++) {
                    Edge e = graph[curr.v].get(i);
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;

                    if (dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }

        for (int i = 0; i < dist.length; i++) {
            System.out.println("0 -> " + i + " = " + dist[i]);
        }
    }

    public static void main(String[] args) {
        int V = 6;

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);

        int src = 0;

        ShortestPaths(graph, src);
    }
}
