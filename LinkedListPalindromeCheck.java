// Important Problem

public class LinkedListPalindromeCheck {
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

    public static Node getMid() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static boolean isPalindrome() {
        if (head == null || head == null) {
            return true;
        }

        Node midNode = getMid();

        Node prev = null;
        Node curr = midNode;
        Node next;
        while (curr != null) {
            next = curr.next;

            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node rightHead = prev;
        Node leftHead = head;

        // Understand why not leftHead != null && rightHead != null in while condition!!!!
        while (rightHead != null) {
            if (leftHead.data != rightHead.data) {
                return false;
            } else {
                leftHead = leftHead.next;
                rightHead = rightHead.next;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        head = new Node(2);
        Node second = new Node(3);
        Node third = new Node(1);
        Node fourth = new Node(3);
        tail = new Node(2);

        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = tail;

        System.out.println(isPalindrome());
    }
}