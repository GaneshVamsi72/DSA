// Screenshots on Nov 17, 18 2024

// A node that was discovered before curr node in DFS, is the ancestor of curr.

// A vertex in an undirected graph is an articulation point (or cut vertex) if removing it (and edges through it) increases the connected components of graph.

// Naive approach: A simple approach is to one by one remove all vertices and see if removal of a vertex causes disconnected graph.

/* Tarjan’s Algorithm: 
The idea is to use DFS (Depth First Search). In DFS, follow vertices in a tree form called the DFS tree. In the DFS tree, a vertex u is the parent of another vertex v, if v is discovered by u. 

In DFS tree, a vertex u is an articulation point if one of the following two conditions is true. 
1. u is the root of the DFS tree and it has at least two children. 
2. u is not the root of the DFS tree and it has a child v such that no vertex in the subtree rooted with v has a back edge to one of the ancestors in DFS tree of u. 
*/

// Time Complexity: O(V + E), For DFS it takes O(V + E) time.

/* 
Why ? For visited neighbour : low[curr] = min(low[curr], disc[neigh]) and not low[curr] = min(low[curr], low[neigh])
Because it fails!!!
Screenshot on 18 Nov 2024
*/
import java.util.ArrayList;

public class ArticulationPoint_TarjanAlgorithm {
    static class Edge {
        int src;
        int dst;

        public Edge(int s, int d) {
            src = s;
            dst = d;
        }
    }

    static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 2));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 1));

        graph[3].add(new Edge(3, 0));
        graph[3].add(new Edge(3, 4));

        graph[4].add(new Edge(4, 3));
    }
    
    static int time = 0;

    static void DFS(ArrayList<Edge>[] graph, int curr, int par, int[] disc, int[] low, boolean[] vis, boolean[] isAP) {
        vis[curr] = true;

        disc[curr] = low[curr] = ++time;

        int children = 0;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);

            int neigh = e.dst;

            if (par == neigh) {
                continue;
            } else if (vis[neigh]) {
                low[curr] = Math.min(low[curr], disc[neigh]);
            } else {
                children++;
                DFS(graph, neigh, curr, disc, low, vis, isAP);
                low[curr] = Math.min(low[curr], low[neigh]);
                if (par != -1 && disc[curr] <= low[neigh]) { // It means that there is no way that neigh can be visited without going through curr (Bridge case and Part of a cycle case) (Must check the screenshots)
                    isAP[curr] = true;
                }
            }
        }

        // If curr is root of DFS tree and has two or more disconnected children.
        if (par == -1 && children > 1) {
            isAP[curr] = true;
        }
    }

    static void Tarjan(ArrayList<Edge>[] graph, int V) {
        int[] disc = new int[V];
        int[] low = new int[V];

        boolean[] vis = new boolean[V];
        boolean[] isAP = new boolean[V]; // For preventing Multiple times display of AP

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                DFS(graph, i, -1, disc, low, vis, isAP);
            }
        }

        System.out.print("Articulation Points : ");
        for (int i = 0; i < V; i++) {
            if (isAP[i]) {
                System.out.print(i + " ");
            }
        }
    }

    public static void main(String[] args) {
        int V = 5;

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);

        Tarjan(graph, V);
    }
}