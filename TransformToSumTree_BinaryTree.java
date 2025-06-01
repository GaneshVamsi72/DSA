// Draw the input and output to understand the aim of the question
public class TransformToSumTree_BinaryTree {

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

    static int Transform(Node root) {
        if (root == null) {
            return 0;
        }

        // Store the original value before transformation
        int originalValue = root.data;

        // Recursively transform left and right subtrees
        int leftSum = Transform(root.left);
        int rightSum = Transform(root.right);

        // Update the current node's data with the sum of left and right subtree values
        root.data = leftSum + rightSum;

        // Return the sum of values under this subtree (including the original node data)
        return originalValue + root.data;
    }

    static void PreOrder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            PreOrder(root.left);
            PreOrder(root.right);
        }
    }

    public static void main(String[] args) {
        int[] nodes = { 1, 2, 4, -1, -1, 5, -1, -1, 3, 6, -1, -1, 7, -1, -1 }; // -1 --> NULL node
        Node root = BuildTree(nodes);

        Transform(root);
        PreOrder(root);
    }
}