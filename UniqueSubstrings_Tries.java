// Given a string of length n of lowercase alphabet characters, we need to count total number of distinct substrings of this string.

// Understand the new defintion of substring in the screenshot on 13 Nov 2024
// Also Check GFG - Count number of Distinct Substrings in a String

// Rough Overview
/*
Steps:
1. Generate all suffixes of the string.
2. For each suffix, find all of its prefixes.
3. Count the unique prefixes. 

Using a Trie (Prefix Tree):
- Create the Trie Structure: Start by initializing an empty Trie. Each node of the Trie will represent a character of the substring, and we'll build the Trie with all possible substrings.

- Insert All Substrings: For each possible starting position in the string, insert every suffix starting from that position into the Trie. This approach ensures that every unique substring is captured because the Trie will only store unique paths for unique substrings.

- Count the Nodes: After building the Trie, count the total number of nodes in the Trie. Each node in the Trie represents a unique substring of the original string. The Trie structure will prevent duplicate substrings from creating additional nodes, so simply counting nodes will give you the count of unique substrings.
*/

// Time Complexity - O(n ^ 2)
/*
1. Insertion of Suffixes into the Trie:

For each suffix starting at index 𝑖 in the string of length 𝑛, we insert it into the Trie. Each insertion operation can take 𝑂(𝑛) time in the worst case (for the longest suffix).
Since there are 𝑛 suffixes, the overall complexity for inserting all suffixes would be 𝑂(𝑛^2)

2. Counting Nodes in the Trie:

After all suffixes are inserted, each node in the Trie represents a unique substring.
Counting the nodes in the Trie is done by a Depth-First Search (DFS) traversal, which takes 𝑂(𝑁), where N is the total number of nodes in the Trie.
Since each character of each suffix can create a node, the Trie can have up to 𝑂(𝑛^2) nodes in the worst case.
*/

// Needs attention !!!!!!
public class UniqueSubstrings_Tries {

    static class Node {
        Node[] children = new Node[26];
        boolean endOfWord = false;

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

    static int CountNodes(Node root) {
        if (root == null) {
            return 0;
        }

        // Current Node 
        int count = 1;

        // Count from children
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                count += CountNodes(root.children[i]); 
            }
        }

        return count; 
    }

    public static void main(String[] args) {
        String str = "ababa";

        // Inserting suffixes
        for (int i = 0; i < str.length(); i++) {
            String suff = str.substring(i);
            insert(suff);
        }
        // Leads to storing unique prefixes of suffixes and leading to the count of unique substrings
        System.out.println(CountNodes(root));
    }
}