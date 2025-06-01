public class ReverseADLL {
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

    public void reverseDLL() {
        Node prevNode = null;
        Node currNode = head;
        Node nextNode;

        while (currNode != null) {
            nextNode = currNode.next;

            currNode.next = prevNode;
            currNode.prev = nextNode;

            prevNode = currNode;
            currNode = nextNode;
        }

        head = prevNode;
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
        ReverseADLL DLL = new ReverseADLL();

        DLL.insertAtBeg(1);
        DLL.insertAtBeg(3);
        DLL.insertAtBeg(6);
        DLL.insertAtBeg(0);
        DLL.insertAtBeg(5);

        DLL.display();

        DLL.reverseDLL();

        DLL.display();
    }
}
