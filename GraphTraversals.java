/* THE CORE REASON WHY IT’S V + E : (Eye Opener !!!)
👉 DFS is not looping unnecessarily. 👉 It touches every node once, and 👉 every edge once while exploring.

That’s all you do — that’s why it’s not V×E, not V², not E²…

Just simple:
    V things (nodes) you visit + E things (edges) you scan → V + E

📌 Important:
    The + is not a blind math sum.

    It means:
        ⏱ Work for nodes (V) (Visiting)
        ⏱ Work for edges (E) (Checking whether DFS through this edge is done or not)

********** Separate contributions, one-time each. **********
*/
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphTraversals {

    static class Edge {

        int src;
        int dest;

        public Edge(int s, int d) {
            src = s;
            dest = d;
        }
    }

    static void createGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 4));

        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 4));
        graph[3].add(new Edge(3, 5));

        graph[4].add(new Edge(4, 2));
        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));

        graph[5].add(new Edge(5, 3));
        graph[5].add(new Edge(5, 4));
        graph[5].add(new Edge(5, 6));

        graph[6].add(new Edge(6, 5));

    }

    // O(V + E)
    static void BFT(ArrayList<Edge>[] graph) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[graph.length];

        q.add(0); // Start Vertex
        vis[0] = true;
        System.out.print("BFT from the starting vertex " + 0 + " : ");

        while (!q.isEmpty()) {
            int curr = q.remove();
            System.out.print(curr + " ");

            for (int i = 0; i < graph[curr].size(); i++) {
                Edge adj = graph[curr].get(i);
                if (!vis[adj.dest]) {
                    vis[adj.dest] = true;
                    q.add(adj.dest);
                }
            }

        }
        System.out.println();
    }

    // O(V + E)
    static void DFTUtil(ArrayList<Edge>[] graph, int curr, boolean[] vis) {
        vis[curr] = true;
        System.out.print(curr + " ");

        for (int i = 0; i < graph[curr].size(); i++) {
            int adj = graph[curr].get(i).dest;
            if (!vis[adj]) {
                DFTUtil(graph, adj, vis);
            }
        }
    }

    static void DFT(ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[graph.length];

        System.out.print("DFT from the starting vertex " + 0 + " : ");
        DFTUtil(graph, 0, vis);
        System.out.println();
    }

    // O(V + E)
    static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if (src == dest) {
            return true;
        }

        vis[src] = true;
        for (int i = 0; i < graph[src].size(); i++) {
            int adj = graph[src].get(i).dest;

            if (!vis[adj] && hasPath(graph, adj, dest, vis)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int V = 7;
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];

        createGraph(graph);

        BFT(graph);
        DFT(graph);

        System.out.println(hasPath(graph, 0, 5, new boolean[V]));
    }
}
