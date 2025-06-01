import java.util.LinkedList;
import java.util.Queue;

public class BuildTreePreOrderCode {
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

    public static class BinaryTree {
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

        // Time Complexity of Traversals - O(n)
        public static void preOrder(Node root) {
            if (root == null) {
                return;
            }
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }

        public static void inOrder(Node root) {
            if (root == null) {
                return;
            }
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }

        public static void postOrder(Node root) {
            if (root == null) {
                return;
            }
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }

        public static void levelOrder(Node root) {
            if (root == null) {
                return;
            }

            Queue<Node> q = new LinkedList<>();

            q.add(root);
            q.add(null);

            while (!q.isEmpty()) {
                Node curr = q.remove();
                if (curr == null) {
                    System.out.println();
                    if (q.isEmpty()) {
                        return;
                    }
                    q.add(null);
                } else {
                    System.out.print(curr.data + " ");
                    if (curr.left != null) {
                        q.add(curr.left);
                    }
                    if (curr.right != null) {
                        q.add(curr.right);
                    }
                }
            }
        }

        public static int getHeight(Node root) {
            if (root == null) {
                return 0;
            }

            int leftHeight = getHeight(root.left);
            int rightHeight = getHeight(root.right);

            int Height = Math.max(leftHeight, rightHeight) + 1;

            return Height;
        }

        public static int getCount(Node root) {
            if (root == null) {
                return 0;
            }

            int leftCount = getCount(root.left);
            int rightCount = getCount(root.right);

            int count = leftCount + rightCount + 1;

            return count;
        }
    }

    public static void main(String[] args) {
        // Building a tree with given preoder sequence
        int[] nodes = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 }; // -1 --> NULL node
        Node root = BinaryTree.BuildTree(nodes);

        System.out.print("PreOrder Travesal : ");
        BinaryTree.preOrder(root);
        System.out.println();

        System.out.print("InOrder Travesal : ");
        BinaryTree.inOrder(root);
        System.out.println();

        System.out.print("PostOrder Travesal : ");
        BinaryTree.postOrder(root);
        System.out.println();

        System.out.println("LevelOrder Travesal : ");
        BinaryTree.levelOrder(root);

        System.out.println("Height of the tree is : " + BinaryTree.getHeight(root));
        System.out.println("Number of nodes in the tree is : " + BinaryTree.getCount(root));
    }
}