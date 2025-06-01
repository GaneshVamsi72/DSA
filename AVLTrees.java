public class AVLTrees {
    static class Node {
        int data, height;
        Node left, right;

        Node(int data) {
            this.data = data;
            height = 1;
        }
    }

    public static Node root;

    public static int height(Node root) {
        if (root == null) {
            return 0;
        }

        return root.height;
    }

    // Right rotate subtree rooted with y
    public static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        // x is new root
        return x;
    }

    // Left rotate subtree rooted with x
    public static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));

        // y is new root
        return y;
    }

    // Get Balance factor of a Node
    public static int getBalance(Node root) {
        if (root == null) {
            return 0;
        }

        return height(root.left) - height(root.right);
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }

        if (data < root.data) {
            root.left = insert(root.left, data);
        } else if (data > root.data) {
            root.right = insert(root.right, data);
        } else {
            return root; // Duplicates are not allowed
        }

        root.height = 1 + Math.max(height(root.left), height(root.right));

        int bf = getBalance(root);

        // Left Left Case
        if (bf > 1 && data < root.left.data) {
            return rightRotate(root);
        }

        // Right Right Case
        if (bf < -1 && data > root.right.data) {
            return leftRotate(root);
        }

        // Left Right Case
        if (bf > 1 && data > root.left.data) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Left Case
        if (bf < -1 && data < root.right.data) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root; // return if AVL is balanced
    }

    public static Node getMinNode(Node root) {
        Node curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }

        return curr;
    }

    public static Node delete(Node root, int data) {
        if (root == null) {
            return root;
        }

        if (data < root.data) {
            root.left = delete(root.left, data);
        } else if (data > root.data) {
            root.right = delete(root.right, data);
        } else {
            // Node with only one child or no child
            if (root.left == null || root.right == null) {
                Node temp = null;

                if (root.left == null) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                // No child node
                if (temp == null) {
                    temp = root;
                    root = null;
                } else // One child case
                    root = temp; // Copy the contents of the non-empty child
            } else {
                Node min = getMinNode(root.right);
                root.data = min.data;
                root.right = delete(root.right, min.data);
            }
        }

        // If the tree had only one node then return as it becomes null after deletion (Also applicable to current node in recursion)
        if (root == null) {
            return root;
        }

        root.height = 1 + Math.max(height(root.left), height(root.right));

        int bf = getBalance(root);

        // Left Left Case
        if (bf > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }

        // Left Right Case
        if (bf > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (bf < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }

        // Right Left Case
        if (bf < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    public static void PreOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        PreOrder(root.left);
        PreOrder(root.right);
    }

    public static void main(String[] args) {
        int[] arr = { 5, 0, 6, 3, 1, 8 };
        for (int i = 0; i < arr.length; i++) {
            root = insert(root, arr[i]);
        }

        PreOrder(root);

        root = delete(root, 6);
        root = delete(root, 8);

        System.out.println();
        PreOrder(root);
    }
}