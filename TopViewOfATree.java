// The top view of a binary tree is the set of nodes visible when the tree is viewed from the top.
// Given a binary tree, print the top view of it.

// ** A node x is there in the output if x is the topmost node at its horizontal distance. 
// The horizontal distance of the left child of a node x is equal to a horizontal distance of x - 1, and that of a right child is the horizontal distance of x + 1. 

// We do a level order traversal so that the topmost node at a horizontal node is visited before any other node of the same horizontal distance below it. 
// Hashing is used to check if a node at a given horizontal distance is seen or not. 

// Also refer GFG if this code does not works in some cases
// I made a change in updating time of min and max!
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class TopViewOfATree {
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

    static class Info {
        Node node;
        int HD;

        public Info(Node node, int HD) {
            this.node = node;
            this.HD = HD;
        }
    }

    static void topView(Node root) {
        if (root == null) {
            return;
        }
        Queue<Info> q = new LinkedList<>();

        HashMap<Integer, Node> map = new HashMap<>();

        int min = 0, max = 0;

        q.add(new Info(root, 0));

        while (!q.isEmpty()) {
            Info info = q.remove();
            if (!map.containsKey(info.HD)) {
                map.put(info.HD, info.node);
                min = Math.min(min, info.HD);
                max = Math.max(max, info.HD);
            }

            if (info.node.left != null) {
                q.add(new Info(info.node.left, info.HD - 1));
            }
            if (info.node.right != null) {
                q.add(new Info(info.node.right, info.HD + 1));
            }
        }

        for (; min <= max; min++) {
            System.out.print(map.get(min).data + " ");
        }
    }

    public static void main(String[] args) {
        // Building a tree with given preoder sequence
        int[] nodes = { 1, 2, 4, -1, -1, 5, -1, -1, 3, 6, -1, -1, 7, -1, -1 }; // -1 --> NULL node
        Node root = BuildTree(nodes);

        topView(root);
    }
}