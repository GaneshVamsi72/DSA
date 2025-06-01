public class DoublyLinkedList {
    public class Node {
        int data;
        Node prev;
        Node next;

        Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;
    public static int size;

    public void insertAtBeg(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }

        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }

    public void insertAt(int data, int pos) {
        Node temp = head;
        Node newNode = new Node(data);

        if (head == null) {
            insertAtBeg(data);
            return;
        }

        if (pos == 1) {
            insertAtBeg(data);
            return;
        }

        for (int i = 1; i < pos - 1; i++) {
            temp = temp.next;
            if (temp == null) {
                System.out.println("Invalid Postion.");
                return;
            }
        }

        if (temp.next == null) {
            insertAtEnd(data);
            return;
        }

        temp.next.prev = newNode;
        newNode.prev = temp;
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    public int delFirst() {
        if (head == null) {
            System.out.println("DLL is already empty!");
            return Integer.MIN_VALUE;
        }

        int val = head.data;

        if (size == 1) {
            head = tail = null;
            size--;
            return val;
        }

        head = head.next;
        head.prev = null;
        size--;
        return val;
    }

    public int delLast() {
        if (head == null) {
            System.out.println("DLL is already empty!");
            return Integer.MIN_VALUE;
        }

        int val = tail.data;

        if (size == 1) {
            tail = head = null;
            size--;
            return val;
        }

        tail = tail.prev;
        tail.next = null;
        size--;
        return val;
    }

    public int delNode(int pos) {
        if (head == null) {
            System.out.println("List is already empty");
            return Integer.MIN_VALUE;
        }

        if (pos < 1 || pos > size) {
            System.out.println("Invalid Position!");
            return Integer.MIN_VALUE;
        }

        if (pos == 1) {
            int val = head.data;
            if (size == 1) {
                head = tail = null;
                size--;
                return val;
            } else {
                return delFirst();
            }
        }

        Node temp = head;
        for (int i = 1; i < pos - 1; i++) {
            temp = temp.next;
        }

        if (temp.next == tail) {
            return delLast();
        }

        int val = temp.next.data;
        temp.next = temp.next.next;
        temp.next.prev = temp;

        size--;

        return val;
    }

    public void display() {
        if (head == null) {
            System.out.println("DLL is already empty!");
            return;
        }
        Node temp = head;
        System.out.print("null <-> ");
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        DoublyLinkedList DLL = new DoublyLinkedList();

        DLL.insertAtBeg(4);
        DLL.display();
        System.out.println(size);

        DLL.insertAtEnd(3);
        DLL.display();
        System.out.println(size);

        DLL.insertAtBeg(2);
        DLL.display();
        System.out.println(size);

        DLL.insertAtEnd(1);
        DLL.display();
        System.out.println(size);

        DLL.insertAt(7, 2);
        DLL.display();
        System.out.println(size);

        DLL.insertAt(6, 1);
        DLL.display();
        System.out.println(size);

        
        DLL.delFirst();
        DLL.display();
        System.out.println(size);

        DLL.delLast();
        DLL.display();
        System.out.println(size);

        DLL.delNode(3);
        DLL.display();
        System.out.println(size);
    }
}