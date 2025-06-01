// Very Similar Problem is Maximum Path Sum...... Only thing to be maintained is avoiding negative numbers in the sum.......

// The diameter/Width of a tree is defined as the number of nodes on the longest path between any two nodes.
/*
Naive Approach O(n^2):
The diameter of T’s left subtree.
The diameter of T’s right subtree.
The longest path between leaves that goes through the root of T (this can be computed from the heights of the subtrees of T)
*/
public class DiameterOfATree_Approach1 {
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

    static int height(Node root) {
        if (root == null) {
            return 0;
        }

        return Math.max(height(root.left), height(root.right)) + 1;
    }

    /*
    1. The diameter function is invoked once for each node (n nodes).
    2. For each invocation, the function calls the height function twice (once for the left subtree and once for the right subtree).
    3. The height function runs in O(n) time since it traverses all nodes to compute the height.
    4. Thus, for each of the n nodes, we are calling the height function, which takes O(n) time. Therefore, the total time complexity is:

    𝑂(𝑛)×𝑂(𝑛)=𝑂(𝑛^2)
    */
    static int Diameter1(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        // Diameter through the root node
        int d = leftHeight + rightHeight + 1;
        // Maximum of the diameters in the left and right subtrees
        int maxD = Math.max(Diameter1(root.left), Diameter1(root.right));

        return Math.max(d, maxD);
    }

    // O(n)
    static class Info {
        int diameter;
        int height;

        public Info(int diameter, int height) {
            this.diameter = diameter;
            this.height = height;
        }
    }
    static Info Diameter2(Node root) {
        if (root == null) {
            return new Info(0, 0);
        }
        Info leftInfo = Diameter2(root.left);
        Info rightInfo = Diameter2(root.right);

        int d = leftInfo.height + rightInfo.height + 1;
        int maxD = Math.max(leftInfo.diameter, rightInfo.diameter);

        int diameter = Math.max(d, maxD);
        int height = 1 + Math.max(leftInfo.height, rightInfo.height);
        return new Info(diameter, height);
    }

    // Simplified Version of Diameter2 function
    // [Expected Approach] Using Bottom Up Recursive - O(n) Time and O(h) Space
    /* 
    The idea is to optimize the above approach by calculating the height in the same recursive function rather than calculating it separately.

    Step by step approach:

    1. Initialize a variable ans, which will store the diameter of the tree. (initially set to 0).
    2. Recursively traverse the binary tree. For each node, find the height of the left and right subtree. Then compare the sum of (height of left subtree + height of right subtree) with the ans variable. If it is greater than ans, then update the value of ans.
    */

    static int diameterRecur(Node root, int[] res) {
        if (root == null)
            return 0;

        // Find the height of left and right subtree (it will also find of diameter for left and right subtree).
        int lHeight = diameterRecur(root.left, res);
        int rHeight = diameterRecur(root.right, res);

        // Check if diameter of root is greater than res.
        res[0] = Math.max(res[0], 1 + lHeight + rHeight);

        // Return the height of current subtree.
        return 1 + Math.max(lHeight, rHeight);
    }
    static int Diameter3(Node root) {
        int[] res = new int[1];
        diameterRecur(root, res);
        return res[0];
    }

    public static void main(String[] args) {
        // Building a tree with given preoder sequence
        int[] nodes = { 1, 2, 4, -1, -1, 5, -1, -1, 3, 6, -1, -1, 7, -1, -1 }; // -1 --> NULL node

        Node root = BuildTree(nodes);

        System.out.println(Diameter1(root));
        
        Info info = Diameter2(root);
        System.out.println(info.diameter);

        System.out.println(Diameter3(root));
    }
}