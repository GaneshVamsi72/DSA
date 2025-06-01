// The trie data structure, also known as a prefix tree, is a tree-like data structure used for efficient retrieval of key-value pairs. It is commonly used for implementing dictionaries and autocomplete features, making it a fundamental component in many search algorithms.
// Trie data structure is defined as a Tree based data structure that is used for storing a collection of strings and performing efficient search, insert, delete, prefix search and sorted-traversal-of-all operations on them. The word Trie is derived from reTRIEval, which means finding something or obtaining it. 

// Trie data structure follows a property that If two strings have a common prefix then they will have the same ancestor in the trie. This particular property allows to find all words with a given prefix.

/*
Properties of a Trie Data Structure

1. Each Trie has an empty root node, with links (or references) to other nodes
2. Each node of a Trie represents a string and each edge represents a character.
3. Every node consists of hashmaps or an array of pointers, with each index representing a character and a flag to indicate if any string ends at the current node.
4. Trie data structure can contain any number of characters including alphabets, numbers, and special characters. But for this article, we will discuss strings with characters a-z. Therefore, only 26 pointers need for every node, where the 0th index represents ‘a’ and the 25th index represents ‘z’ characters.
5. Each path from the root to any node represents a word or string.
*/


// Construct the trie and then try to understand the code
public class Tries {

    static class Node {
        Node[] children = new Node[26]; // 26
        boolean endOfWord = false;

        Node() {
            for (int i = 0; i < children.length; i++) {
                children[i] = null;
            }
        }
    }

    static Node root = new Node();

    // O(L) (L - length of max length word)
    static void insert(String word) {
        Node curr = root;
        
        for (int level = 0; level < word.length(); level++) {
            int ch_i = word.charAt(level) - 'a';
            if (curr.children[ch_i] == null) {
                curr.children[ch_i] = new Node();
            }
            curr = curr.children[ch_i];
        }

        curr.endOfWord = true;
    }

    // O(L)
    static boolean Search(String key) {
        Node curr = root;
        for (int level = 0; level < key.length(); level++) {
            int ch_i = key.charAt(level) - 'a';
            if (curr.children[ch_i] == null) {
                return false;
            }
            curr = curr.children[ch_i];
        }

        return curr.endOfWord == true;
    }

    // Returns true if there is any of previously inserted string words has the prefix _____, and false otherwise.
    // O(L)
    static boolean startsWith(String prefix) {
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            int ch_i = prefix.charAt(i) - 'a';
            if (curr.children[ch_i] == null) {
                return false;  
            } else {
                curr = curr.children[ch_i];
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[] words = { "the", "a", "there", "their", "any", "thee" };
        
        for (String word : words) {
            insert(word);
        }

        System.out.println(Search("thee"));
        System.out.println(Search("thor"));

        System.out.println(startsWith("th"));
        System.out.println(startsWith("anyone"));
    }
}