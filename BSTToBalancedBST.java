
import java.util.ArrayList;

public class BSTToBalancedBST {

    static class Node {

        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static Node insert(Node root, int val) {
        if (root == null) {
            root = new Node(val);
            return root;
        }

        if (root.data > val) {
            // insert in the left subtree
            root.left = insert(root.left, val);
        } else {
            // insert in the right subtree
            root.right = insert(root.right, val);
        }

        return root;
    }

    static void getInOrder(ArrayList<Integer> inOrder, Node root) {
        if (root == null) {
            return;
        }

        getInOrder(inOrder, root.left);
        inOrder.add(root.data);
        getInOrder(inOrder, root.right);
    }

    static Node buildBST(ArrayList<Integer> inOrder, int s, int e) {
        if (s > e) {
            return null;
        }

        int mid = s + (e - s) / 2;

        Node root = new Node(inOrder.get(mid));
        root.left = buildBST(inOrder, s, mid - 1);
        root.right = buildBST(inOrder, mid + 1, e);

        return root;
    }

    // O(n)
    static Node balanceBST(Node root) {
        // Creating Sorted array by inOrder sequence - O(n)
        ArrayList<Integer> inOrder = new ArrayList<>();
        getInOrder(inOrder, root);

        // Sorted Array to Balanced BST - O(n)
        return buildBST(inOrder, 0, inOrder.size() - 1);
    }

    static void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static void main(String[] args) {
        int[] val = {8, 6, 5, 3, 10, 11, 12};
        Node root = null;
        for (int i = 0; i < val.length; i++) {
            root = insert(root, val[i]);
        }


        root = balanceBST(root);
        preOrder(root);
    }
}
