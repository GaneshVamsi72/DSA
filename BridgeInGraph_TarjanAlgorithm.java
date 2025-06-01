// An edge in an undirected connected graph is a bridge if removing it disconnects the graph.

// A bridge in a graph, also known as a cut edge, is an edge that, when removed, increases the number of connected components in the graph.

/* 
Naive Approach: (Beautifulllllllllllllllllllllll)
Below is the idea to solve the problem:
One by one remove all edges and see if the removal of an edge causes a disconnected graph. 
*/

/*
ALGORITHM: –

To implement this algorithm, we need the following data structures –

    visited[ ] = to keep track of the visited vertices to implement DFS
    disc[ ] = to keep track when for the first time that particular vertex is reached
    low[ ] = to keep track of the lowest possible time by which we can reach that vertex ‘other than parent’ so that if edge from parent is removed can the particular node can be reached other than parent.

We will traverse the graph using DFS traversal but with slight modifications i.e. while traversing we will keep track of the parent node by which the particular node is reached because we will update the low[node] = min(low[all it’s adjacent node except parent]) hence we need to keep track of the parent.

While traversing adjacent nodes let ‘v’ of a particular node let ‘u’, then 3 cases arise –

1. v is parent of u then, skip that iteration.
2. v is visited then, update the low of u i.e. low[u] = min(low[u] , disc[v]) this arises when a node can be visited by more than one node, but low is to keep track of the lowest possible time so we will update it.
3. v is not visited then, 
    call the DFS to traverse ahead
    now update the low[u] = min(low[u], low[v]) as we know v can’t be parent cause we have handled that case first.
    now check if (low[v] > disc[u]) i.e. the lowest possible to time to reach ‘v’ is greater than ‘u’ this means we can’t reach ‘v’ without ‘u’ so the edge   u -> v is a bridge.
*/

/* Time Complexity: O(V+E),
    The above approach uses simple DFS along with Tarjan’s Algorithm. 
    So time complexity is the same as DFS which is O(V+E) for adjacency list representation of the graph.
*/

// Have to work on it more for better understanding !!!!
// Must watch Jenny Lecture 
// Must Dry Run

// Why low?? ->  It is used to keep track if the neighbour can be visited through node having lesser discovery time than current parent (Apna College version : Stores Hum aur hamari neighbours sabse lowest discovery time vaali kaunsi node tak pahuch sakthe hein) (Jenny Version : I know someone (meaning I have an edge with someone) who has discovey time less than yours)
// Whether the current edge is bridge ? is checked at the time of backtracking

import java.util.ArrayList;

public class BridgeInGraph_TarjanAlgorithm {

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
        graph[3].add(new Edge(3, 5));

        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));

        graph[5].add(new Edge(5, 3));
        graph[5].add(new Edge(5, 4));
    }

    static int time = 0;

    static void DFS(ArrayList<Edge>[] graph, int curr, int par, int[] disc, int[] low, boolean[] vis) {
        vis[curr] = true;
        disc[curr] = low[curr] = ++time;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);

            int neigh = e.dst;

            if (neigh == par) {
                continue;
            } else if (!vis[neigh]) {
                DFS(graph, neigh, curr, disc, low, vis);
                low[curr] = Math.min(low[curr], low[neigh]);
                if (disc[curr] < low[neigh]) { // This means we can’t reach neigh without curr so the edge   u -> v is a bridge.
                    System.out.println(e.src + " ----- " + e.dst);
                }
            } else {
                low[curr] = Math.min(low[curr], disc[neigh]);
            }
        } 

    }

    static void Tarjan(ArrayList<Edge>[] graph, int V) {
        int[] disc = new int[V];
        int[] low = new int[V];

        boolean[] vis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                DFS(graph, i, -1, disc, low, vis);
            }
        }
    }

    public static void main(String[] args) {
        int V = 6;

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);

        Tarjan(graph, V);
    }
}