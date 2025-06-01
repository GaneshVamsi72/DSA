// Time Complexity : O(n * log n)

public class MergeSortOnLL {

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

    private Node merge(Node a, Node b) {
        Node dummy = new Node(-1);
        Node temp = dummy;

        while (a != null && b != null) {
            if (a.data <= b.data) {
                temp.next = a;
                a = a.next;
            } else {
                temp.next = b;
                b = b.next;
            }
            temp = temp.next;
        }

        if (a != null) {
            temp.next = a;
        }

        if (b != null) {
            temp.next = b;
        }

        return dummy.next;
    }

    public Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node mid = getMid(head);

        Node rightHead = mid.next;
        mid.next = null;

        Node sortedLeft = mergeSort(head);
        Node sortedRight = mergeSort(rightHead);

        return merge(sortedLeft, sortedRight);
    }

    public static void main(String[] args) {
        MergeSortOnLL ll = new MergeSortOnLL();
        ll.addAtEnd(5);
        ll.addAtEnd(0);
        ll.addAtEnd(6);
        ll.addAtEnd(3);
        ll.addAtEnd(1);
        ll.addAtEnd(8);

        System.out.println("Given List : ");
        printList();
        head = ll.mergeSort(head);
        System.out.println("Sorted List : ");
        printList();
    }
}