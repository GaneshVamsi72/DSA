// There are n cities connected by some number of flights. You are given an array flights where flights[i] = [from, to, price] indicates that there is a flight.
// You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops.
// If there is no such route, return -1.

// All values are positive

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CheapestFlightsWithinKStops {

    static class Edge {
        int src;
        int dst;
        int wt;

        public Edge(int s, int d, int w) {
            src = s;
            dst = d;
            wt = w;
        }
    }

    static void createGraph(int[][] flights, ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < flights.length; i++) {
            int src = flights[i][0];
            int dst = flights[i][1];
            int wt = flights[i][2];

            graph[src].add(new Edge(src, dst, wt));
        }
    }

    static class Info {
        int v;
        int cost;
        int stops;

        public Info(int v, int cost, int stops) {
            this.v = v;
            this.cost = cost;
            this.stops = stops;
        }
    }

    static int cheapestFLight(int n, int[][] flights, int src, int dest, int k) {
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[n];
        createGraph(flights, graph);

        int[] dist = new int[n];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[src] = 0;

        Queue<Info> q = new LinkedList<>();

        q.add(new Info(src, 0, 0));

        while (!q.isEmpty()) {
            Info i = q.remove();

            if (i.stops > k)
                break;

            for (int j = 0; j < graph[i.v].size(); j++) {
                Edge e = graph[i.v].get(j);
                int v = e.dst;
                int wt = e.wt;

                // i.cost is used instead of dist[u]
                // Because dist[u] can be updated anytime in the future.... But i item in the queue can be of different dist[u]
                // So in order to keep track of that we must use i.cost instead of dist[u]
                // Check the screenshot on 25 Sep for further clarification
                if (i.cost + wt < dist[v]) {
                    dist[v] = i.cost + wt;
                    q.add(new Info(v, dist[v], i.stops + 1));
                }
            }
        }

        return dist[dest] == Integer.MAX_VALUE ? -1 : dist[dest];
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] flights = { { 0, 1, 100 }, { 1, 2, 100 }, { 2, 0, 100 }, { 1, 3, 600 }, { 2, 3, 200 } };
        int src = 0, dst = 3, k = 1;

        System.out.println(cheapestFLight(n, flights, src, dst, k));
    }
}
