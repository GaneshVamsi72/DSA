// Connected component in an undirected graph refers to a group of vertices that are connected to each other through edges, but not connected to other vertices outside the group.

// Strongly Connected Component is a subset of vertices where every vertex in the subset is reachable from every other vertex in the same subset by traversing the directed edges.

/*
Kosaraju’s Algorithm involves two main phases:

1. Performing Depth-First Search (DFS) on the Original Graph:
    We first do a DFS on the original graph and record the finish times of nodes (i.e., the time at which the DFS finishes exploring a node completely).
2. Performing DFS on the Transposed Graph:
    We then reverse the direction of all edges in the graph to create the transposed graph.
    Next, we perform a DFS on the transposed graph, considering nodes in decreasing order of their finish times recorded in the first phase.
    Each DFS traversal in this phase will give us one SCC.

Here’s a simplified version of Kosaraju’s Algorithm:

1. DFS on Original Graph: Record finish times.
2. Transpose the Graph: Reverse all edges.
3. DFS on Transposed Graph: Process nodes in order of decreasing finish times to find SCCs. 
*/

import java.util.ArrayList;
import java.util.Stack;

public class StronglyConnectedComponents {
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

        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));

        graph[1].add(new Edge(1, 0));

        graph[2].add(new Edge(2, 1));

        graph[3].add(new Edge(3, 4));
    }

    static void DFS(ArrayList<Edge>[] graph, boolean[] vis, int curr) {
        vis[curr] = true;
        System.out.print(curr + " ");

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.dst]) {
                DFS(graph, vis, e.dst);
            }
        }
    }

    static void topSort(ArrayList<Edge>[] graph, int curr, Stack<Integer> s, boolean[] vis) {
        vis[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.dst]) {
                topSort(graph, e.dst, s, vis);
            }
        }

        s.push(curr);
    }

    // O(V + E)
    static void Kosaraju(ArrayList<Edge>[] graph, int V) {
        Stack<Integer> s = new Stack<>();
        boolean[] vis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                topSort(graph, i, s, vis);
            }
        }
        
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] transpose = new ArrayList[V];

        for (int i = 0; i < transpose.length; i++) {
            vis[i] = false;
            transpose[i] = new ArrayList<>();
        }

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                Edge e = graph[i].get(j);
                transpose[e.dst].add(new Edge(e.dst, e.src));
            }
        }

        while (!s.empty()) {
            int curr = s.pop();

            if (!vis[curr]) {
                System.out.print("SCC -> ");
                DFS(transpose, vis, curr);
                System.out.println();
            } 
        }
    }

    public static void main(String[] args) {
        int V = 5;

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);

        Kosaraju(graph, V);
    }
}