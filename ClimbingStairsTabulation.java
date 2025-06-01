// There are n stairs, a person standing at the bottom wants to climb stairs to reach the nth stair. The person can climb either 1 stair or 2 stairs at a time, the task is to count the number of ways that a person can reach at the top.

// The person can reach nth stair from either (n-1)th stair or from (n-2)th stair. Hence, for each stair n, we try to find out the number of ways to reach n-1th stair and n-2th stair and add them to give the answer for the nth stair. Therefore the Recurrence relation for such an approach comes out to be : 

// ways(n) = ways(n-1) + ways(n-2)

// O(n) (Tabulation) Bottom-Up Approach

public class ClimbingStairsTabulation {
    static int NoOfWays(int n) {
        int[] ways = new int[n + 1];

        ways[0] = 1;
        ways[1] = 1;

        for (int i = 2; i < ways.length; i++) {
            ways[i] = ways[i - 1] + ways[i - 2];
        }

        return ways[n];
    }

    public static void main(String[] args) {
        System.out.println(NoOfWays(5));
    }
}
