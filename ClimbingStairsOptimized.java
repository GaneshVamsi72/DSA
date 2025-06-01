// There are n stairs, a person standing at the bottom wants to climb stairs to reach the nth stair. The person can climb either 1 stair or 2 stairs at a time, the task is to count the number of ways that a person can reach at the top.

// The person can reach nth stair from either (n-1)th stair or from (n-2)th stair. Hence, for each stair n, we try to find out the number of ways to reach n-1th stair and n-2th stair and add them to give the answer for the nth stair. Therefore the Recurrence relation for such an approach comes out to be : 

// ways(n) = ways(n-1) + ways(n-2)

// O(2^n) (Recursion) reduced to O(n) (Memoization) // Top-Down Approach
import java.util.Arrays;

public class ClimbingStairsOptimized {

    static int NoOfWays(int n, int[] ways) {
        if (n == 0)
            return 1;
        if (n == -1)
            return 0;

        if (ways[n] != -1)
            return ways[n];

        return ways[n] = NoOfWays(n - 1, ways) + NoOfWays(n - 2, ways);
    }

    public static void main(String[] args) {
        int n = 5;

        int[] ways = new int[n + 1];
        Arrays.fill(ways, -1);
        System.out.println(NoOfWays(n, ways));
    }
}
