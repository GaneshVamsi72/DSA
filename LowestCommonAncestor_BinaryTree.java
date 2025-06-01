// Also check the Lowest Common Ancestor in BST Striver Video !!!!!!!!!!!!! And follow Iterative method for space optimized solution
import java.util.ArrayList;

public class LowestCommonAncestor_BinaryTree {
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

    // Root to Node (of value n) - O(n)
    static boolean getPath(Node root, int n, ArrayList<Node> path) {
        if (root == null) {
            return false;
        }

        path.add(root);

        if (root.data == n) {
            return true;
        }

        boolean InLeft = getPath(root.left, n, path);
        boolean InRight = getPath(root.right, n, path);

        if (InLeft || InRight) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    // Approach 1 - O(n)
    /*
     * Find a path from the root to n1 and store it in a vector or array.
     * Find a path from the root to n2 and store it in another vector or array.
     * Traverse both paths till the values in arrays are the same. Return the common
     * element just before the mismatch.
     */
    static Node LCA1(Node root, int n1, int n2) {
        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();

        getPath(root, n1, path1);
        getPath(root, n2, path2);

        int i = 0;

        for (; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i).data != path2.get(i).data) {
                break;
            }
        }

        Node LCA = path1.get(i - 1);

        return LCA;
    }

    // Approach 2
    // LCA is the first ancestor node, in whose subtrees, both n1 and n2 exist!! (Powerful line !!!!!!!! for this entire function)
    // O(n)

    // In simple words, with this function we are trying to find the first node(starting from the n1 and n2) which contains both n1 and n2, by assuming the nodes of values n1 and n2 as LCA (when we reach them starting from the root node!)
    static Node LCA2(Node root, int n1, int n2) {
        if (root == null) {
            return null;
        }

        if (root.data == n1 || root.data == n2) {
            return root;
        }

        // Searching in left subtree if it contains both n1 and n2
        Node leftLCA = LCA2(root.left, n1, n2);
        // Searching in right subtree if it contains both n1 and n2
        Node rightLCA = LCA2(root.right, n1, n2);

        // For the cases : 
        // 1. leftLCA = key(n1 or n2) & rightLCA = null
        // 2. leftLCA = null & rightLCA = null
        if (rightLCA == null) {
            return leftLCA;
        }

        // For the cases : leftLCA = null & rightLCA = key(n1 or n2)
        if (leftLCA == null) {
            return rightLCA;
        }

        // When leftLCA and rightLCA are not null : It means that current root is the first ancestor (Beacuse it is the node which has one key present in its left subtree and the other key present in the right subtree) !!!!

        return root;
    }

    public static void main(String[] args) {
        int[] nodes = { 1, 2, 4, -1, -1, 5, -1, -1, 3, 6, -1, -1, 7, -1, -1 }; // -1 --> NULL node
        Node root = BuildTree(nodes);

        int n1 = 5, n2 = 3;

        System.out.println(LCA1(root, n1, n2).data);
        System.out.println(LCA2(root, n1, n2).data);
    }
}