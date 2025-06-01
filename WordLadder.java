/*
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

1. Every adjacent pair of words differs by a single letter.
2. Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
3. sk == endWord


Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;

public class WordLadder {
    static class Pair {
        String word;
        int steps;

        public Pair(String w, int s) {
            word = w;
            steps = s;
        }
    }

    static int wordLadder(String startWord, String targetWord, String[] wordList) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(startWord, 1));

        HashSet<String> st = new HashSet<>();
        int len = wordList.length;
        for (int i = 0; i < len; i++) {
            st.add(wordList[i]);
        }

        st.remove(startWord);
        while (!q.isEmpty()) {
            Pair p = q.remove();
            String word = p.word;
            int steps = p.steps;

            if (word.equals(targetWord)) {
                return steps;
            }

            for (int i = 0; i < word.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char[] replacedArr = word.toCharArray();
                    replacedArr[i] = ch;
                    String replacedWord = new String(replacedArr);

                    if (st.contains(replacedWord)) {
                        st.remove(replacedWord);
                        q.add(new Pair(replacedWord, steps + 1));
                    }
                }
            }
        }

        return 0;
    }
    public static void main(String[] args) {
        String[] wordList = {"poon", "plee", "same", "poie", "plea", "plie", "poin"};
        
        System.out.println(wordLadder("toon", "plea", wordList));
    }
}
