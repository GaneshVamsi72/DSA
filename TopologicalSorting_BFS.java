// A directed acyclic graph has at least one vertex with in-degree 0 and one vertex with out-degree 0.

// There is no need for visited array because at every time we are adding vertices in the queue only when the indegree of the vertex is zero that means we can not visit again that vertex through another vertex
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Kahn's Algorithm
public class TopologicalSorting_BFS {

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

    static void calcIndeg(ArrayList<Edge>[] graph, int[] indeg) {
        for (int v = 0; v < graph.length; v++) {
            for (int j = 0; j < graph[v].size(); j++) {
                Edge e = graph[v].get(j);
                indeg[e.dest]++;
            }
        }
    }

    // O(V + E)
    static void topSort(ArrayList<Edge>[] graph) {
        int[] indegree = new int[graph.length];
        calcIndeg(graph, indegree);

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int curr = q.remove();
            System.out.print(curr + " ");

            for (int i = 0; i < graph[curr].size(); i++) {
                Edge e = graph[curr].get(i);
                indegree[e.dest]--;

                if (indegree[e.dest] == 0) {
                    q.add(e.dest);
                }
            }
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
