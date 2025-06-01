// Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

// O(n^2) 
public class SubtreeOfAnotherTree {

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
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

    static boolean areIdentical(Node root, Node subroot) {
        if (root == null && subroot == null) {
            return true;
        }
        if (root == null || subroot == null) {
            return false;
        }

        return root.data == subroot.data && areIdentical(root.left, subroot.left) && areIdentical(root.right, subroot.right);
    }

    static boolean isSubtree(Node root, Node subroot) {
        if (subroot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }

        if (areIdentical(root, subroot)) {
            return true;
        }

        return isSubtree(root.left, subroot) || isSubtree(root.right, subroot);
    }

    public static void main(String[] args) {
        // Building a tree with given preoder sequence
        int[] nodes1 = { 1, 2, 4, -1, -1, 5, -1, -1, 3, 6, -1, -1, 7, -1, -1 }; // -1 --> NULL node
        Node root = BuildTree(nodes1);
        idx = -1;
        int[] nodes2 = { 2, 4, -1, -1, 5, -1, -1 };
        Node subroot = BuildTree(nodes2);

        System.out.println(isSubtree(root, subroot));
    }
}
