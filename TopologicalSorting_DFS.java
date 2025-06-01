// Directed Acyclic Graph(DAG) is a directed graph with no cycles.

// ***** Topological sorting is used only for DAGs (not for non-DAGs) *****

// It is a linear order of vertices such that every directed edge u->v, the vertex u comes before v in the order.

import java.util.ArrayList;
import java.util.Stack;

public class TopologicalSorting_DFS {

    static class Edge {
        int dest;

        public Edge(int d) {
            dest = d;
        }
    }

    static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[5].add(new Edge(0));
        graph[5].add(new Edge(2));

        graph[4].add(new Edge(0));
        graph[4].add(new Edge(1));

        graph[2].add(new Edge(3));

        graph[3].add(new Edge(1));
    }

    static void sortUtil(ArrayList<Edge>[] graph, int curr, boolean[] vis, Stack<Integer> stack) {
        vis[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.dest]) {
                sortUtil(graph, e.dest, vis, stack);
            }

        }
        stack.add(curr);
    }

    // O(V + E)
    static void topSort(ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[graph.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < graph.length; i++) { // For connected components graph
            if (!vis[i]) {
                sortUtil(graph, i, vis, stack);
            }
        }

        while (!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static void main(String[] args) {
        int V = 6;

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);

        topSort(graph);
    }
}
