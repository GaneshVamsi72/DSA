// Given N ropes of different lengths, the task is to connect these ropes into ropes into one rope with minimum cost, such that the cost to connect two ropes is equal to the sum of their lengths.

// Observation : Once a rope is selected and joined first..It will contribute to the cost till the completion of process of selecting and joining ropes  
// So, at every stage of joining ropes...We must select the least sized ropes first...so that whatever the contribution after wards will be minimum compared to other cases......

import java.util.PriorityQueue;

public class ConnectNRopesWithMinimumCost_Heaps {
    public static int minCost(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }

        int cost = 0;
        // Doubt Resolution : 
        // When the while loop completes, the Priority Queue pq will be indeed be left with one element. 
        // This is the combined length of all ropes, and it doesn't contribute to the total cost calculation since the problem is to minimize the cost of connecting ropes, which is defined by the sum of lengths that were combined during each operation.
        // This single rope represents the total lengths after all combinations but is not added to the cost since the cost is only calculated during the combining process.
        while (pq.size() > 1) {
            int first = pq.remove();
            int second = pq.remove();

            cost += first + second;
            pq.add(first + second);
        }

        return cost;
    }

    public static void main(String[] args) {
        int[] arr = { 4, 3, 2, 6 };

        System.out.println(minCost(arr));
    }
}