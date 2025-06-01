// Implementing Insertion, Deletion and Printing in Circular Linked List
// Can contain minor mistakes
public class CicularLinkedList {

    static class Node {

        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    };

    public static Node head;
    public static Node tail;

    public static void insertAtBegNode(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = tail = newNode;
            tail.next = head;
            return;
        }

        newNode.next = head;
        head = newNode;
        tail.next = head;
    }

    public static void insertAtEndNode(int data) {
        Node newNode = new Node(data);

        if (tail == null) {
            tail = head = newNode;
            tail.next = head;
            return;
        }

        tail.next = newNode;
        tail = newNode;
        tail.next = head;
    }

    public static void insertAt(int data, int pos) {
        if (head == null) {
            insertAtBegNode(data);
            return;
        }

        if (pos == 1) {
            insertAtBegNode(data);
            return;
        }

        Node newNode = new Node(data);
        Node temp = head;

        for (int i = 1; i < pos - 1; i++) {
            temp = temp.next;
            if (temp == head) {
                System.out.println("Invalid position.");
                return;
            }
        }

        newNode.next = temp.next;
        temp.next = newNode;

        // Updating circularity : If newNode is inserted at the end !!
        if (newNode.next == head) {
            tail = newNode;
        }
    }

    public static void deleteNode(int key) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Node curr = head, prev = null;

        // If head node itself holds the key to be deleted
        if (curr.data == key) {
            tail.next = head.next;
            head = head.next;
            return;
        }

        // If the key is somewhere other than the head
        do {
            prev = curr;
            curr = curr.next;
            if (curr.data == key) {
                prev.next = curr.next;
                if (curr == tail) {
                    tail = prev;
                }
                return;
            }
        } while (curr != head);

        System.out.println("Key not found in the list.");
    }

    public static void display() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node temp = head;
        do {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        } while (temp != head);
        System.out.println("null");
    }

    public static void main(String[] args) {
        insertAtBegNode(5);
        insertAtEndNode(0);
        insertAt(6, 1);
        insertAtBegNode(3);
        insertAtEndNode(1);

        deleteNode(0);

        display();
    }
}