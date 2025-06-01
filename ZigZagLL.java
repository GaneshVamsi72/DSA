// For a linked list of form : L(1)->l(2)->L(3)->L(4)...L(n-1)->L(n)
// Convert it into a zig-zag form i.e. : L(1)->L(n)->L(2)->L(n-1)->L(3)->L(n-2)...
public class ZigZagLL {

    public static class Node {
        int data;
        Node next;

        Node(int d) {
            this.data = d;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;

    public void addAtEnd(int data) {
        Node new_Node = new Node(data);

        if (head == null) {
            tail = head = new_Node;
            return;
        }

        tail.next = new_Node;
        tail = new_Node;
    }

    public static void printList() {
        if (head == null) {
            System.out.println("Linked List is empty");
            return;
        }

        Node currentNode = head;

        while (currentNode != null) {
            System.out.print(currentNode.data + " -> ");
            currentNode = currentNode.next;
        }
        System.out.println("null");
    }

    private Node getMid(Node head) {
        Node slow = head;
        // Small change in fast node initialisation to get the last node of the right half of the list as the midNode in the case of even number of elements in the list
        Node fast = head.next; 

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public void ZigZag() {
        Node mid = getMid(head);

        // Break the second half of the list and reverse it
        Node curr = mid.next;
        mid.next = null;

        Node prev = null;
        Node next;

        while (curr != null) {
            next = curr.next;
            
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        // Merge the two parts alternatively
        Node left = head;
        Node right = prev;
        Node nextL, nextR;

        while (left != null && right != null) {
            nextL = left.next;
            left.next = right;
            nextR = right.next;
            right.next = nextL;

            left = nextL;
            right = nextR;
        }
    }

    public static void main(String[] args) {
        ZigZagLL ll = new ZigZagLL();
        ll.addAtEnd(1);
        ll.addAtEnd(2);
        ll.addAtEnd(3);
        ll.addAtEnd(4);
        ll.addAtEnd(5);
        ll.addAtEnd(6);

        System.out.println("Given List : ");
        printList();

        ll.ZigZag();

        System.out.println("Zig Zag List : ");
        printList();
    }
}