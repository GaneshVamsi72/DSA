public class DisjointSetUnionDS {
    static int n = 7;
    static int[] par = new int[n];
    static int[] rank = new int[n];

    static void init() {
        for (int i = 0; i < n; i++) {
            par[i] = i;
        }
    }

    // k is a constant
    
    // O(4k) -> O(4) -> O(1)
    static int find(int i) {
        if (par[i] == i)
            return i;

        return par[i] = find(par[i]);
    }

    // O(4k) -> O(k) -> O(1)
    static void union(int a, int b) {
        int parA = find(a);
        int parB = find(b);

        if (rank[parA] == rank[parB]) {
            rank[parA]++;
            par[parB] = parA;
        } else if (rank[parA] < rank[parB]) {
            par[parA] = parB;
        } else {
            par[parB] = parA;
        }
    }

    public static void main(String[] args) {
        init();

        union(1, 3);
        System.out.println(find(3));
        union(2, 4);
        union(3, 6);
        union(1, 4);
        System.out.println(find(3));
        System.out.println(find(4));
        union(1, 5);
    }
}