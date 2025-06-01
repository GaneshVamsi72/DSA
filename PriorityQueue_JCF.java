
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueue_JCF {

    static class Student implements Comparable<Student> {

        String name;
        int rank;

        Student(String name, int rank) {
            this.name = name;
            this.rank = rank;
        }

        @Override
        public int compareTo(Student s2) {
            return this.rank - s2.rank;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();

        pq1.add(5);
        pq1.add(0);
        pq1.add(6);
        pq1.add(3);
        pq1.add(1);

        while (!pq1.isEmpty()) {
            System.out.print(pq1.peek() + " ");
            pq1.remove();
        }

        System.out.println();

        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Comparator.reverseOrder());
        pq2.add(5);
        pq2.add(0);
        pq2.add(6);
        pq2.add(3);
        pq2.add(1);

        while (!pq2.isEmpty()) {
            System.out.print(pq2.peek() + " ");
            pq2.remove();
        }

        System.out.println();

        PriorityQueue<Student> pq3 = new PriorityQueue<>();

        pq3.add(new Student("S", 3));
        pq3.add(new Student("V", 2));
        pq3.add(new Student("I", 7));
        pq3.add(new Student("R", 6));

        while (!pq3.isEmpty()) {
            System.out.println(pq3.peek().name + "->" + pq3.peek().rank);
            pq3.remove();
        }
    }
}
