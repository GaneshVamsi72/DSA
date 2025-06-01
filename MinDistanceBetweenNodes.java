public class MinDistanceBetweenNodes {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static int idx = -1;

    public static Node BuildTree(int[] nodes) {
        idx++;
        if (nodes[idx] == -1) {
            return null;
        }
        Node newNode = new Node(nodes[idx]);
        newNode.left = BuildTree(nodes);
        newNode.right = BuildTree(nodes);

        return newNode;
    }

    // Detailed code and explanation is with LowestCommonAncestor class
    static Node LCA(Node root, int n1, int n2) {
        if (root == null || root.data == n1 || root.data == n2) {
            return root;
        }

        Node leftLCA = LCA(root.left, n1, n2);
        Node rightLCA = LCA(root.right, n1, n2);

        if (rightLCA == null) {
            return leftLCA;
        }

        if (leftLCA == null) {
            return rightLCA;
        }

        return root;
    }

    static int LCAtoN(Node root, int n) {
        if (root == null) {
            return -1;
        }

        // At Node n !!! So distance from n to n is 0 !!!
        if (root.data == n) {
            return 0;
        }

        // Searching for n in left and right subtrees
        int LCAtoN1 = LCAtoN(root.left, n);
        int LCAtoN2 = LCAtoN(root.right, n);

        if (LCAtoN1 == -1 && LCAtoN2 == -1) {
            return -1;
        }

        return (LCAtoN1 == -1 ? LCAtoN2 : LCAtoN1) + 1;
    }

    static int minDist(Node root, int n1, int n2) {
        Node LCA = LCA(root, n1, n2);

        int dist1 = LCAtoN(LCA, n1);
        int dist2 = LCAtoN(LCA, n2);
        return dist1 + dist2;
    }

    public static void main(String[] args) {
        int[] nodes = { 1, 2, 4, -1, -1, 5, -1, -1, 3, 6, -1, -1, 7, -1, -1 }; // -1 --> NULL node
        Node root = BuildTree(nodes);

        int n1 = 5, n2 = 3;
        System.out.print(minDist(root, n1, n2));
    }
}
