
import java.util.ArrayList;

public class Merge2BSTs {

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

    static void getInOrder(Node root, ArrayList<Integer> arr) {
        if (root == null) {
            return;
        }

        getInOrder(root.left, arr);
        arr.add(root.data);
        getInOrder(root.right, arr);
    }

    static Node buildBST(ArrayList<Integer> arr, int s, int e) {
        if (s > e) {
            return null;
        }

        int m = (s + e) / 2;
        Node root = new Node(arr.get(m));
        root.left = buildBST(arr, s, m - 1);
        root.right = buildBST(arr, m + 1, e);

        return root;
    }

    // O(n + m)
    static Node merge(Node root1, Node root2) {
        ArrayList<Integer> arr1 = new ArrayList<>();
        getInOrder(root1, arr1);

        ArrayList<Integer> arr2 = new ArrayList<>();
        getInOrder(root2, arr2);

        int i = 0, j = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        while (i < arr1.size() && j < arr2.size()) {
            if (arr1.get(i) <= arr2.get(j)) {
                arr.add(arr1.get(i));
                i++;
            } else {
                arr.add(arr2.get(j));
                j++;
            }
        }
        while (i < arr1.size()) {
            arr.add(arr1.get(i));
            i++;
        }
        while (j < arr2.size()) {
            arr.add(arr2.get(j));
            j++;
        }

        return buildBST(arr, 0, arr.size() - 1);
    }

    static void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static void main(String[] args) {
        /*
                    2
                  /   \
                 1     4
         
                BST-1
         */

        Node root1 = new Node(2);
        root1.left = new Node(1);
        root1.right = new Node(4);

        /*
                    9
                  /   \
                 3     12
         
                BST-2
         */
        Node root2 = new Node(9);
        root2.left = new Node(3);
        root2.right = new Node(12);

        Node root = merge(root1, root2);
        preOrder(root);
    }
}
