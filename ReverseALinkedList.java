public class ReverseALinkedList {
    public static Node head;
    public static Node tail;

    public static class Node {
        int data;
        Node next;

        Node(int new_data) {
            this.data = new_data;
            this.next = null;
        }
    }

    public void reverseIterative() {
        Node prevNode = null;
        Node currNode = head;
        tail = head;
        Node nextNode;

        while (currNode != null) {
            nextNode = currNode.next;

            currNode.next = prevNode;

            prevNode = currNode;
            currNode = nextNode;
        }

        head = prevNode;
    }

    public Node reverseRecursive(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node newHead = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    public static void printList() {
        if (head == null) {
            System.out.println("Linked List is empty.");
            return;
        }

        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        ReverseALinkedList linkedList = new ReverseALinkedList();

        head = new Node(1);
        Node sNode = new Node(2);
        Node tNode = new Node(3);
        tail = new Node(4);

        head.next = sNode;
        sNode.next = tNode;
        tNode.next = tail;

        System.out.println("Given Linked List : ");
        printList();

        linkedList.reverseIterative();

        System.out.println("Reversed Linked List (Iterative) : ");
        printList();

        Node newHead = linkedList.reverseRecursive(head);
        head = newHead;
        System.out.println("Reversed Linked List (Recursive) : ");
        printList();
    }
}