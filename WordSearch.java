/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 20, 2014
 Problem:    Word Search
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/word-search/
 Notes:
 Given a 2D board and a word, find if the word exists in the grid.
 The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are
 those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 For example,
 Given board =
 [
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
 ]
 word = "ABCCED", -> returns true,
 word = "SEE", -> returns true,
 word = "ABCB", -> returns false.

 Solution: DFS.
 */
public class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        if (m == 0) return false;
        int n = board[0].length;
        if (n == 0) return false;
        if (word.length() == 0) return true;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == word.charAt(0) && existRe(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean existRe(char[][] board, int i, int j, String word, int cur, boolean[][] visited) {
        if (cur == word.length()) return true;
        int m = board.length;
        int n = board[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n) return false;
        if (visited[i][j] == true || (board[i][j] != word.charAt(cur))) return false;
        visited[i][j] = true;
        if (existRe(board, i+1, j, word, cur+1,visited)) return true;
        if (existRe(board, i-1, j, word, cur+1,visited)) return true;
        if (existRe(board, i, j+1, word, cur+1,visited)) return true;
        if (existRe(board, i, j-1, word, cur+1,visited)) return true;
        visited[i][j] = false;
        return false;
    }
}


ednesday, July 15, 2015
Word Search II (Boggle Game) -- Leetcode
Question:
Given a 2D board and a list of words from the dictionary, find all words in the board.
Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
For example,
Given words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.
click to show hint.
You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?
If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.

Answer:
public class Solution {

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<String>();
        //to avoid duplicate
        Set<String> sset = new HashSet<String>();
        String tmp = new String("");
        int m = board.length;
        int n = board[0].length;

        Trie trie = new Trie();
        for(int i=0; i< words.length; ++i){
            trie.insert(words[i]);
        }
        boolean[][] visited = new boolean[m][n];
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                visited[i][j] = false;
            }
        }

        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
               DFS(board, i, j, visited, tmp, trie, sset);
            }
        }
        for(String s : sset){
            res.add(s);
        }
        return res;
    }

    public void DFS(char[][] board, int i, int j, boolean[][] visited, String str, Trie trie, Set<String> sset){
        str = str += board[i][j];

        if(!trie.startsWith(str)){
         str = str.substring(0,str.length()-1);
            return;
        }

        if(trie.search(str)){
            sset.add(str);
        }
        visited[i][j] = true;

        int m = board.length;
        int n = board[0].length;
        if(j-1 >= 0 && !visited[i][j-1]){
            DFS(board, i, j-1, visited, str, trie, sset);
        }
        if(j+1 < n && !visited[i][j+1]){
            DFS(board, i, j+1, visited, str, trie, sset);
        }
        if(i-1 >= 0 && !visited[i-1][j]){
            DFS(board, i-1, j, visited, str, trie, sset);
        }
        if(i+1 < m && !visited[i+1][j]){
            DFS(board, i+1, j, visited, str, trie, sset);
        }

        //backtrack, important!
        visited[i][j] = false;
        str = str.substring(0,str.length()-1);
        return;
    }


//Trie tree
  class TrieNode {
    // Initialize your data structure here.
    public char c;
    // important!
    public boolean isEnd;
    public int count;
    public ArrayList<TrieNode> children;

    public TrieNode(char cha) {
        this.c = cha;
        isEnd = false;
        count = 1;
        children = new ArrayList<TrieNode>();
    }
    //return the node in the chilren == cha
    public TrieNode rtnchildNode(char cha){
        for(TrieNode child : this.children){
            if(child.c == cha){
                return child;
            }
        }
        return null;
    }
  }

  class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode(' ');
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode cur = root;
        TrieNode next = null;

        for(int i=0;i< word.length(); ++i){
            next = cur.rtnchildNode(word.charAt(i));

            if(next!=null){
                next.count++;
                cur=next;

            }else{
                TrieNode insertNewNode = new TrieNode(word.charAt(i));
                cur.children.add(insertNewNode);
                cur=insertNewNode;
            }
            if(i== word.length()-1){
                cur.isEnd = true;
            }
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode cur = root;
        TrieNode next = null;

        for(int i=0; i< word.length(); ++i){
            next = cur.rtnchildNode(word.charAt(i));
            if(next != null){
                cur = next;
            }else{
                return false;
            }
            //important!Judge the tail char whether 'isEnd'
            if(i == word.length()-1){
                if(cur.isEnd == true){
                    return true;
                }
            }
        }
        return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        TrieNode next = null;

        for(int i=0; i< prefix.length(); ++i){
            next = cur.rtnchildNode(prefix.charAt(i));

            if(next != null){
                cur = next;
            }else{
                return false;
            }
        }
        return true;
    }
  }
}