public class MirrorABST {
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

    // Time Complexity : O(n)
    public static Node Mirror(Node root) {
        if (root == null) {
            return root;
        }

        Node leftSubMirror = Mirror(root.left);
        Node rightSubMirror = Mirror(root.right);

        root.left = rightSubMirror;
        root.right = leftSubMirror;

        return root;
    }

    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        int[] val = { 5, 0, 6, 3, 1, 8, 4 };
        Node root = null;

        for (int i = 0; i < val.length; i++) {
            root = insert(root, val[i]);
        }

        System.out.println("Inorder traversal of given tree : ");
        inorder(root);

        System.out.println();

        root = Mirror(root);
        System.out.println("Inorder traversal of Mirrored tree : ");
        inorder(root);
    }
}