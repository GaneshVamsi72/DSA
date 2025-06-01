// Given a number N. The task is to generate and print all binary numbers with decimal values from 1 to N.

import java.util.LinkedList;
import java.util.Queue;

public class GenBinaryNum {
    public static void genBinNum(int N) {
        Queue<String> q = new LinkedList<>();
        q.add("1");

        while (N != 0) {
            String s1 = q.remove();
            System.out.println(s1);

            String s2 = s1;
            q.add(s1 + "0");
            q.add(s2 + "1");
            N--;
        }
    }

    public static void main(String[] args) {
        int N = 10;

        genBinNum(N);
    }
}