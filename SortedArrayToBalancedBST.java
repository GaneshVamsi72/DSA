
public class SortedArrayToBalancedBST {

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

    static Node buildBST(int[] arr, int s, int e) {
        if (s > e) {
            return null;
        }

        int mid = s + (e - s) / 2;

        Node root = new Node(arr[mid]);
        root.left = buildBST(arr, s, mid - 1);
        root.right = buildBST(arr, mid + 1, e);

        return root;
    }

    static void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 6, 8, 10, 11, 12};

        Node root = buildBST(arr, 0, arr.length - 1);
        preOrder(root);
    }
}