public class KthAncestorOfNode {
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

    // Draw Binary Tree and then think about the logic
    static int KthAncestor(Node root, int n, int k) {
        if (root == null) {
            return -1;
        }

        if (root.data == n) {
            return 0;
        }

        int leftRes = KthAncestor(root.left, n, k);
        int rightRes = KthAncestor(root.right, n, k);

        if (leftRes + 1 == k || rightRes + 1 == k) {
            System.out.println(root.data);
        }

        if (leftRes == -1 && rightRes == -1) {
            return -1;
        }

        return (leftRes != -1 ? leftRes : rightRes) + 1;
    }

    public static void main(String[] args) {
        int[] nodes = { 1, 2, 4, -1, -1, 5, -1, -1, 3, 6, -1, -1, 7, -1, -1 }; // -1 --> NULL node
        Node root = BuildTree(nodes);

        int n = 5;
        int k = 1;

        KthAncestor(root, n, k);
    }
}