// 0-1 Knapsack 
// O(2^n)
public class Knapsack_DynamicProgramming {
    static int MaxProfitKnapsack(int[] val, int[] wt, int W, int n) {
        if (W == 0 || n == 0) {
            return 0;
        }

        // We can put that item into the bag
        if (wt[n - 1] <= W) {
            // We will put it
            int ans1 = val[n - 1] + MaxProfitKnapsack(val, wt, W - wt[n - 1], n - 1);
            // We will not put it
            int ans2 = MaxProfitKnapsack(val, wt, W, n - 1);
            return Math.max(ans1, ans2);
        } else { // We can not put that item into the bag
            // We will not put it
            return MaxProfitKnapsack(val, wt, W, n - 1);
        }
    }

    public static void main(String[] args) {
        int[] val = { 15, 14, 10, 45, 30 };
        int[] wt = { 2, 5, 1, 3, 4 };
        int W = 7;

        System.out.println(MaxProfitKnapsack(val, wt, W, val.length));
    }
}