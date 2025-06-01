
public class LinkedListSearch {

    public static class Node {
        int data;
        Node next;

        Node(int d) {
            this.data = d;
            this.next = null;
        }
    }

    public static Node head;

    public static int ItrSearch(int key) {
        Node current = head;
        int idx = 0;

        while (current != null) {
            if (current.data == key) {
                return idx;
            }
            current = current.next;
            idx++;
        }

        return -1;
    }

    public static int RecSearch(Node current, int key) {
        if (head == null) {
            return -1;
        }

        if (current.data == key) {
            return 0;
        }

        int idx = RecSearch(current.next, key);
        if (idx == -1) {
            return -1;
        }

        return idx + 1;
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

    public static void main(String[] args) {
        head = new Node(5);
        Node fir = new Node(0);
        Node sec = new Node(6);
        Node ths = new Node(3);
        Node frt = new Node(1);

        head.next = fir;
        fir.next = sec;
        sec.next = ths;
        ths.next = frt;
        frt.next = null;

        printList();

        System.out.println(ItrSearch(6));
        System.out.println(RecSearch(head, 6));
    }
}