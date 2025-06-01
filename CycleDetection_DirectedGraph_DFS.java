// Watch Striver !!!!!!

// Undirected Graph Cycle Detection Approach will not work here.........
// Because in undirected graph, it doesn't matter if we take different paths and have a visited neighbour and it is not the parent node because the edges are undirected !
// But in directed graph, it does matter. !! Just because we had a visited neighbour and it is not the parent node because the edges are directed !!
// We should have a node in the same path that is visited and not the parent node !!
// That is the reason we use stack (visited array for the current path !!!)
import java.util.ArrayList;

public class CycleDetection_DirectedGraph_DFS {

    static class Edge {

        int src;
        int dest;

        public Edge(int s, int d) {
            src = s;
            dest = d;
        }
    }

    static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 2));

        graph[1].add(new Edge(1, 0));

        graph[2].add(new Edge(2, 3));

        graph[3].add(new Edge(3, 0));
    }

    static boolean detectCycleUtil(ArrayList<Edge>[] graph, int curr, boolean[] vis, boolean[] stack) {
        vis[curr] = true;
        stack[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (stack[e.dest]) {
                return true;
            } else if (!vis[e.dest]) {
                if (detectCycleUtil(graph, e.dest, vis, stack)) {
                    return true;
                }
            }
        }

        stack[curr] = false;

        return false;
    }

    // O(V + E)
    static boolean detectCycle(ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[graph.length];
        boolean[] stack = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) { // For connected components graph
            if (!vis[i]) {
                if (detectCycleUtil(graph, i, vis, stack)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int V = 4;

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);

        System.out.println(detectCycle(graph));
    }
}
