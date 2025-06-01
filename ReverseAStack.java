import java.util.Stack;

public class ReverseAStack {
    public static void pushAtBottom(Stack<Integer> s, int val) {
        if (s.empty()) {
            s.push(val);
            return;
        }

        int top = s.pop();
        pushAtBottom(s, val);
        s.push(top);
    }

    public static void reverse(Stack<Integer> s) {
        if (s.empty()) {
            return;
        }

        int top = s.pop();
        reverse(s);
        pushAtBottom(s, top);
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();

        s.push(1);
        s.push(2);
        s.push(3);

        reverse(s);

        while (!s.empty()) {
            System.out.print(s.pop() + " ");
        }
    }
}