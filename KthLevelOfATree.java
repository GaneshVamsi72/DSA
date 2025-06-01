public class KthLevelOfATree {
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

    static Node BuildTree(int[] nodes) {
        idx++;
        if (nodes[idx] == -1) {
            return null;
        }
        Node newNode = new Node(nodes[idx]);
        newNode.left = BuildTree(nodes);
        newNode.right = BuildTree(nodes);

        return newNode;
    }

    // O(n)
    static void KthLevel(Node root, int l, int k) {
        if (root == null) {
            return;
        }

        if (l == k) {
            System.out.print(root.data + " ");
            return;
        }

        KthLevel(root.left, l + 1, k);
        KthLevel(root.right, l + 1, k);
    }

    public static void main(String[] args) {
        int[] nodes = { 1, 2, 4, -1, -1, 5, -1, -1, 3, 6, -1, -1, 7, -1, -1 }; // -1 --> NULL node
        Node root = BuildTree(nodes);

        int k = 3;
        KthLevel(root, 1, k);
    }
}