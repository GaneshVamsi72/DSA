// Given an input string and a dictonary of words, find out if the input string can be broken into a space-separated sequence of dictionary words.

// The Word Break Problem is a classic problem where you determine if a given string can be segmented into valid words from a dictionary. Using a Trie can make this efficient, especially when looking up words from the dictionary repeatedly.

/* 
Approach :
1. Construct a Trie:

    Insert all dictionary words into a Trie, where each node represents a character.
    Mark the end of each word in the dictionary as a terminal node.
2. Check for Word Break:

    Use a depth-first search (DFS) with memoization to check if each substring of s exists in the Trie.
    If a valid prefix exists in the Trie, recursively check the remaining part of the string.
*/

/*
1. Trie is used because Trie is used for searching strings in an array of strings efficiently. Here we have to break keyword into substrings (somehow) and validate them by checking if they exist in the given dictionary.
2. For breaking the keyword into substrings, we use recursion for trying all possible breakings....
3. If there is no combination of substrings exist, then we return false.
*/

// Screen shot available(Recursion tree) on 30 Oct 2024 for tabulation approach understanding
// Also Refer GFG Dynamic Programming Approach(Tabulation)
// Solved on leet code using DP (Check it out whenever revising!!)
// Must learn the DP approach as this recursion appraoch leads to exceeded Time Limit

// Using Dp tabulation is still Higher Time Complexity
// Must look forward to optimize this !!\

// Also refer following solution
// Beautifulllllllllllllll
/*
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        return helper(s, s.length(), wordDict, new int[s.length()+1]);

    }

    // dp[i] will store the result of word break for each substring starting from position i 
    public boolean helper(String s, int n, List<String> wordDict, int[] dp) {
        // if we found word break for whole string, return true
        if (s.isEmpty()) return true;

        // if we have computed the result for given String s earlier  
        if (dp[n - s.length()] != 0) return dp[n - s.length()] == 1;

        // for each word in dictionary, check if it is prefix of given string
        for (String word : wordDict) {
            int l = word.length();
            
            // if word is prefix, then s will definitely be of lesser length than word's length
            if (s.length() >= l) {
                
                // if word is prefix of s, then break s and look for word break in remaining string
                if (s.startsWith(word)) {
                    boolean b = helper(s.substring(l), n, wordDict, dp);
                    
                    // if we found word break for remaining string, return true
                    if (b) {
                        dp[n - s.length()] = 1;
                        return b;
                    }
                }
            }
        }

        // if we didn't find word break for given string s, return false
        dp[n - s.length()] = -1;
        return false;

    }
}
*/
public class WordBreakProblem_Tries {

    static class Node {
        Node[] children = new Node[26]; // 26
        boolean endOfWord = false;

        public Node() {
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

    /*
    L: The maximum length of words in the dictionary.

    m: The number of words in the dictionary.

    n: The length of the input string key.

    Without memoization, the recursive function would theoretically have exponential time complexity, approximated as 𝑂(2^𝑛), due to all possible ways of breaking the string.
    
    With memoization (if applied), the time complexity can be significantly reduced to approximately 𝑂(𝑛×𝐿) because:

    Each position in key (from 0 to 𝑛) is processed only once. 
    For each position, the Trie search for each prefix takes 𝑂(𝐿) time.
    */
    static boolean canBreak(String key) {
        if (key.length() == 0) {
            return true;
        }
        for (int i = 1; i <= key.length(); i++) {
            String part1 = key.substring(0, i);
            String part2 = key.substring(i);

            if (Search(part1) && canBreak(part2)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String[] words = { "i", "like", "sam", "samsung", "mobile", "ice" };
        String key = "ilikesamsung";

        for (String word : words) {
            insert(word);
        }

        System.out.print(canBreak(key));
    }
}