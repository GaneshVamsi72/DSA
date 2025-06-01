import java.util.ArrayList;
public class CycleDetection_UndirectedGraph_DFS {

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

        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 4));

        graph[3].add(new Edge(3, 1));
        // graph[3].add(new Edge(3, 4));
        // graph[3].add(new Edge(3, 5));

        graph[4].add(new Edge(4, 2));
        // graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));

        // graph[5].add(new Edge(5, 3));
        graph[5].add(new Edge(5, 4));
        graph[5].add(new Edge(5, 6));

        graph[6].add(new Edge(6, 5));
    }

    static boolean detectCycleUtil(ArrayList<Edge>[] graph, int curr, boolean[] vis, int par) {
        vis[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            /* Three cases
                On an average, At every vertex, these are the cases that will be present.......
               
                Case 1 - Adjacent vertex is visited and the parent vertex is not equal to the adjacent vertex 
                        Return true then
                Case 2 - Adjacent vertex is visited and the parent vertex is equal to the adjacent vertex 
                        Do nothing
                Case 3 - Adjacent vertex is not visited, then
                        Return what the function returns for further traversal through that vertex
            */ 
            // Better follow the above cases order if you have doubt in this following order.........
            if (!vis[e.dest] ) {
                if(detectCycleUtil(graph, e.dest, vis, curr)) {
                    return true;
                }
            } else if (vis[e.dest] && e.dest != par) {
                return true;
            }
        }

        return false;
    }

    // O(V + E)
    static boolean detectCycle(ArrayList<Edge>[] graph) {
        boolean[] vis = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) { // For connected components graph
            if (!vis[i]) {
                if (detectCycleUtil(graph, i, vis, -1)) {
                    return true;
                } 
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int V = 7;

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);

        System.out.println(detectCycle(graph));
    }
}
