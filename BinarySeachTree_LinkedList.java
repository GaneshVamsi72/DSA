
import java.util.ArrayList;

public class BinarySeachTree_LinkedList {

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

    public static Node findInorderSuccessor(Node root) {
        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    public static Node delete(Node root, int val) {
        if (root.data < val) {
            root.right = delete(root.right, val);
        } else if (root.data > val) {
            root.left = delete(root.left, val);
        } else {
            // Case 1 : Node with no child nodes
            if (root.left == null && root.right == null) {
                return null;
            }

            // Case 2 : Node with one child node
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }

            // Case 3 : Node with two child nodes
            Node inorderSuccessor = findInorderSuccessor(root.right);
            root.data = inorderSuccessor.data;
            root.right = delete(root.right, inorderSuccessor.data);
        }

        return root;
    }

    // Time Complexity of search : O(H) H - Height of the tree
    public static boolean search(Node root, int key) {
        if (root == null) {
            return false;
        }

        if (root.data == key) {
            return true;
        }

        if (root.data > key) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

    public static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static int count(Node root) {
        if (root == null) {
            return 0;
        }

        int leftCount = count(root.left);
        int rightCount = count(root.right);

        return leftCount + rightCount + 1;
    }

    public static void printInRange(Node root, int k1, int k2) {
        if (root == null) {
            return;
        }
        if (root.data >= k1 && root.data <= k2) {
            printInRange(root.left, k1, k2);
            System.out.print(root.data + " ");
            printInRange(root.right, k1, k2);
        } else if (root.data < k1) {
            printInRange(root.right, k1, k2);
        } else if (root.data > k2) {
            printInRange(root.left, k1, k2);
        }
    }

    public static void rootToLeaf(Node root, ArrayList<Integer> path) {
        if (root == null) {
            return;
        }

        path.add(root.data);

        if (root.left == null && root.right == null) {
            System.out.println(path);
        }

        rootToLeaf(root.left, path);
        rootToLeaf(root.right, path);
        path.remove(path.size() - 1);
    }

    public static int find(Node root, int target, int closest) {
        if (root == null) {
            return closest;
        }

        if (Math.abs(root.data - target) < Math.abs(closest - target)) {
            closest = root.data;
        }

        if (target < root.data) {
            return find(root.left, target, closest);
        } else if (target > root.data) {
            return find(root.right, target, closest);
        } else {
            return root.data;
        }
    }

    public static void findClosest(Node root, int target) {
        if (root == null) {
            System.out.println("Tree is Empty.");
            return;
        }

        int closest = find(root, target, root.data);
        System.out.println("Closest to " + target + " is " + closest);
    }

    static int count = 0;
    static int result = -1;
    // Time Complexity - O(n)
    // Space Complexity - O(H)
    public static void findKthSmallest(Node root, int k) {
        if (root == null) {
            return;
        }

        findKthSmallest(root.left, k);
        count++;
        if (count == k) {
            result = root.data;
            return;
        }
        findKthSmallest(root.right, k);
    }

    public static void main(String[] args) {
        int[] val = {5, 0, 6, 3, 1, 8};
        Node root = null;

        for (int i = 0; i < val.length; i++) {
            root = insert(root, val[i]);
        }

        System.out.print("Inorder traversal : ");
        inorder(root);

        System.out.println();

        if (search(root, 0)) {
            System.out.println(0 + " is present in the tree!");
        } else {
            System.out.println(0 + " is not present in the tree!");
        }

        root = delete(root, 0);
        root = delete(root, 8);

        System.out.println("Inorder traversal of BST after deletion of 0 & 8 : ");
        inorder(root);

        System.out.println();

        System.out.println("Number of nodes in the tree : " + count(root));

        printInRange(root, 1, 5);

        System.out.println();

        rootToLeaf(root, new ArrayList<>());

        findClosest(root, 3);

        findKthSmallest(root, 3);
        System.out.println("3rd Smallest in the BST is : " + result);
    }
}
