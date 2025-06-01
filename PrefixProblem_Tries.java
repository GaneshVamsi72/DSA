// Find the shortest unique prefix for every word in a given list.
// Assume no word is prefix of another.

// A Simple Solution is to consider every prefix of every word (starting from the shortest to largest), and if a prefix is not prefix of any other string, then print it. 
/*
An Efficient Solution is to use Trie. The idea is to maintain a count in every node. 
Below are steps.
1) Construct a Trie of all words. Also maintain frequency of every node (Here frequency is number of times node is visited during insertion). Time complexity of this step is O(N) where N is total number of characters in all words. 
2) Now, for every word, we find the character nearest to the root with frequency as 1. The prefix of the word is path from root to this character. To do this, we can traverse Trie starting from root. For every node being traversed, we check its frequency. If frequency is one, we print all characters from root to this node and don't traverse down this node.
Time complexity if this step also is O(N) where N is total number of characters in all words. 
*/

// O(n * m)
// where n is the length of the array and m is the length of the longest word.

public class PrefixProblem_Tries {

    static class Node {
        Node[] children = new Node[26];
        int freq;

        Node() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
            freq = 1;
        }
    }

    static Node root = new Node();

    static void insert(Node root, String str) {
        Node curr = root;
        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';

            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();  
            } else {
                curr.children[idx].freq++;
            }

            curr = curr.children[idx];
        }

    }

    // O(L) (L = levels = longest word)
    static void uniquePrefix(Node root, String ans) {
        if (root == null) {
            return;
        }

        if (root.freq == 1) {
            System.out.println(ans);
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                uniquePrefix(root.children[i], ans + (char)(i + 'a'));
            }
        }
    }

    public static void main(String[] args) {
        String[] arr = { "zebra", "dog", "duck", "dove" };

        for (String str : arr) {
            insert(root, str);
        }
        
        root.freq = -1;

        uniquePrefix(root, "");
    }
}