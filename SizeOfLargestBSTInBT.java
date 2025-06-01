// Not clear !!!!!!!!!!
// Also not having clarity whether it is correct code or not
// There's a screenshot of correct code available in the gallery of phone (11 August 2024)

// A variant of this problem is Maximum Sum BST in Binary Tree
// There's code available in this folder (14 August 2024)

public class SizeOfLargestBSTInBT {

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

    static class nodeInfo {

        boolean isBST;
        int size;
        int min; // Min value in subtree
        int max; // Max value in subtree

        public nodeInfo() {
        }

        public nodeInfo(boolean isBST, int size, int min, int max) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    static int maxSize = 0;

    static nodeInfo largestBST(Node root) {

        if (root == null) {
            return new nodeInfo(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        nodeInfo leftInfo = largestBST(root.left);
        nodeInfo rightInfo = largestBST(root.right);

        int size = leftInfo.size + rightInfo.size + 1;
        int min = Math.min(root.data, Math.min(leftInfo.min, rightInfo.min));
        int max = Math.max(root.data, Math.max(leftInfo.max, rightInfo.max));

        if (root.data <= leftInfo.max || root.data >= rightInfo.min) {
            return new nodeInfo(false, size, min, max);
        }

        if (leftInfo.isBST && rightInfo.isBST) {
            maxSize = Math.max(maxSize, size);
            return new nodeInfo(true, size, min, max);
        }

        return new nodeInfo(false, size, min, max);
    }

    public static void main(String[] args) {
        /*
                                50
                              /     \
                            30       60
                           /   \    /   \
                          5     20  45   70
                                        /   \
                                       65   80
         */

        Node root = new Node(50);

        root.left = new Node(30);
        root.left.left = new Node(5);
        root.left.right = new Node(20);

        root.right = new Node(60);
        root.right.left = new Node(45);
        root.right.right = new Node(70);
        root.right.right.left = new Node(65);
        root.right.right.right = new Node(80);

        nodeInfo Info = largestBST(root);
        System.out.println("Size of largest BST in the given BT is : " + maxSize);
    }
}
