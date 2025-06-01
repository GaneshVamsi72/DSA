import java.util.*;
import java.util.LinkedList;

public class Deque_JCF {
    public static void main(String[] args) {
        Deque<Integer> deq = new LinkedList<>();

        deq.addFirst(5);
        deq.addFirst(0);
        deq.addLast(6);
        deq.addLast(3);
        System.out.println(deq);

        deq.removeFirst();
        deq.removeLast();
        System.out.println(deq);

        System.out.println("First Element : " + deq.getFirst());
        System.out.println("First Element : " + deq.getLast());
    }
}