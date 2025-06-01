import java.util.Stack;

public class ValidParenthesis {

    public static boolean isValid(String str) {
        Stack<Character> s = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '(' || ch == '{' || ch == '[') {
                s.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                if (s.empty()) {
                    return false;
                }
                char top = s.pop();

                if (ch == ')' && top != '(' || ch == '}' && top != '{' || ch == ']' && top != '[') {
                    return false;
                }
            }
        }
        
        return s.empty();
    }

    public static void main(String[] args) {
        String str = "({)[]";

        System.out.println(isValid(str));
    }
}