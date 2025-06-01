// Find the longest string in words such that every prefix of it is also in words.

public class LongestWordWithAllPrefixes_Tries {
    static class Node {
        Node[] children = new Node[26];
        boolean endOfWord;

        public Node() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    static Node root = new Node();

    static void insert(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            int ch_i = word.charAt(i) - 'a';
            if (curr.children[ch_i] == null) {
                curr.children[ch_i] = new Node();
            }
            curr = curr.children[ch_i];
        }

        curr.endOfWord = true;
    }

    static String ans = "";

    static void longestWord(Node root, StringBuilder temp) {
        if (root == null) {
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null && root.children[i].endOfWord == true) {
                char ch = (char)(i + 'a');
                temp.append(ch);
                if (temp.length() > ans.length()) {
                    ans = temp.toString();
                }
                longestWord(root.children[i], temp);
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        String[] words = { "a", "banana", "app", "appl", "ap", "apply", "apple" };

        for (String word : words) {
            insert(word);
        }

        longestWord(root, new StringBuilder(""));
        System.out.println(ans);
        // Answer can be apply and apple but we only considered lexicographical order
    }
}