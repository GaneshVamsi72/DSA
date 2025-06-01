public class LinkedList {

    public static class Node {
        int value;
        Node next;

        Node(int d) {
            this.value = d;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;
    public static int size;

    public void addAtBeginning(int data) {
        Node new_Node = new Node(data);
        size++;
        if (head == null) {
            head = tail = new_Node;
            return;
        }

        new_Node.next = head;
        head = new_Node;
    }

    public void addAtEnd(int data) {
        Node new_Node = new Node(data);
        size++;
        if (head == null) {
            tail = head = new_Node;
            return;
        }

        tail.next = new_Node;
        tail = new_Node;
    }

    public void insertAt(int idx, int data) {
        if (idx == 0) {
            addAtBeginning(data);
            return;
        }
        Node new_Node = new Node(data);
        size++;
        Node temp = head;
        int i = 0;

        while (i < idx - 1) {
            temp = temp.next;
            if (temp == null) {
                System.out.println("Index out of range.");
                return;
            }
            i++;
        }

        if (temp.next == null) {
            addAtEnd(data);
            return;
        }

        new_Node.next = temp.next;
        temp.next = new_Node;
    }

    

    public int removeFirst() {
        if (size == 0) {
            System.out.println("Linked List is empty.");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int val = head.value;
            head = tail = null;
            size = 0;
            return val;
        }
        int val = head.value;
        head = head.next;
        size--;
        return val;
    }

    public int removeLast() {
        if (size == 0) {
            System.out.println("Linked List is empty.");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int val = tail.value;
            tail = head = null;
            size--;
            return val;
        }

        int val = tail.value;
        Node prev = head;
        for (int i = 0; i < size - 2; i++) {
            prev = prev.next;
        }
        prev.next = null;
        tail = prev;
        size--;
        return val;
    }

    public void delNthFromEnd(int n) {
        if (head == null) {
            return;
        }

        if (n == size) {
            head = head.next;
            return;
        }

        Node prev = head;

        for (int i = 1; i < size - n; i++) {
            prev = prev.next;
        }

        Node next = prev.next.next;
        prev.next = next;

        size--;
    }

    public static void printList() {
        if (head == null) {
            System.out.println("Linked List is empty");
            return;
        }

        Node currentNode = head;

        while (currentNode != null) {
            System.out.print(currentNode.value + " -> ");
            currentNode = currentNode.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();

        // Assign value values
        head = new Node(1);
        size++;
        Node secNode = new Node(2);
        size++;
        tail = new Node(3);
        size++;

        // Connect nodes
        head.next = secNode;
        secNode.next = tail;

        // printing node-value
        printList();

        linkedList.addAtBeginning(0);
        linkedList.addAtEnd(5);
        linkedList.insertAt(4, 4);

        printList();

        System.out.println("Size : " + size);

        linkedList.removeFirst();
        linkedList.removeLast();

        printList();

        System.out.println("Size : " + size);

        linkedList.delNthFromEnd(2);

        printList();

        System.out.println("Size : " + size);
    }
}