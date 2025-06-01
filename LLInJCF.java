import java.util.LinkedList;

public class LLInJCF {

    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<>();

        ll.addLast(5);
        ll.addLast(0);

        ll.addFirst(6);
        ll.addFirst(3);

        System.out.println(ll);

        ll.removeLast();
        ll.removeFirst();

        System.out.println(ll);
    }
}