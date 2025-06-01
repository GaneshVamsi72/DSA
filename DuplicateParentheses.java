// Given a balanced expression, find if it contains duplicate parentheses or not.
// A set of parentheses are duplicate if the same subexpression is surrounded by multiple parentheses.

// Time Complexity : O(n)
import java.util.Stack;

public class DuplicateParentheses {
    public static boolean isDuplicate(String str) {

        Stack<Character> s = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == ')') {
                int count = 0;
                while (s.pop() != '(') {
                    count++;
                }

                if (count < 1) {
                    return true;
                }
            } else {
                s.push(ch);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String exp = "((a+b)+(c+d))";

        System.out.println(isDuplicate(exp));
    }
}
