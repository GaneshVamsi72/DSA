// Important !!
// Must check the notes for approach

public class ValidateBST {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node insert(Node root, int val) {
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

    public static boolean isValidBST(Node root, int min, int max) {
        if (root == null) {
            return true;
        }

        if (root.data <= min || root.data >= max) {
            return false;
        } 

        return (isValidBST(root.left, min, root.data) && isValidBST(root.right, root.data, max));
    }

    public static void main(String[] args) {
        int[] val = { 5, 0, 6, 3, 1, 8 };
        Node root = null;

        for (int i = 0; i < val.length; i++) {
            root = insert(root, val[i]);
        }

        System.out.println(isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
}
