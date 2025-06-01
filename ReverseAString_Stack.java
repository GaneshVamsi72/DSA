import java.util.Stack;

public class ReverseAString_Stack {
    public static String reverse(String str) {
        Stack<Character> s = new Stack<>();
        int idx = 0;

        while (idx < str.length()) {
            s.push(str.charAt(idx));
            idx++;
        }

        StringBuilder result = new StringBuilder("");
        while (!s.empty()) {
            result.append(s.pop());
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String str = "maRayiS";
        str = reverse(str);
        System.out.println(str);
    }
}