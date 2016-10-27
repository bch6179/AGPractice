/*
 Author:     Andy, nkuwjg@gmail.com
 Date:       Jan 12, 2015
 Problem:    Binary Tree Zigzag Level Order Traversal
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 Notes:
 Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left 
 to right, then right to left for the next level and alternate between).
 For example:
 Given binary tree {3,9,20,#,#,15,7},
     3
    / \
   9  20
  / \
 15  7
 return its zigzag level order traversal as:
 [
  [3],
  [20,9],
  [15,7]
 ]

 Solution: 1. Queue + reverse.
           2. Two stacks.
 */

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> zigzagLevelOrder_1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        q.offer(null);
        List<Integer> level = new ArrayList<Integer>();
        int depth = 0;
        while(true) {
            TreeNode node = q.poll();
            if (node != null) {
                level.add(node.val);
                if(node.left!=null) q.offer(node.left);
                if(node.right!=null) q.offer(node.right);
            } else {
                if (depth % 2 == 1) Collections.reverse(level);
                res.add(level);
                depth++;
                level = new ArrayList<Integer>();
                if(q.isEmpty()==true) break;
                q.offer(null);
            }
        }
        return res;   
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        Stack<TreeNode> cur = new Stack<TreeNode>();
        Stack<TreeNode> last = new Stack<TreeNode>();
        boolean left2right = true;
        last.push(root);
        List<Integer> level = new ArrayList<Integer>();
        while (last.empty() == false) {
            TreeNode node = last.pop(); 
            if (node != null) {
                level.add(node.val);
                if (left2right) {
                    if(node.left!=null) cur.push(node.left);
                    if(node.right!=null) cur.push(node.right);
                } else {
                    if(node.right!=null) cur.push(node.right);
                    if(node.left!=null) cur.push(node.left);
                }
            }
            if (last.empty() == true) {
                if (level.size() != 0)
                    res.add(level);
                level = new ArrayList<Integer>();
                Stack<TreeNode> temp = last;
                last = cur;
                cur = temp;
                left2right = !left2right;
            }
        }
        return res;
    }


    from lintcode import TreeNode
"""
Definition of TreeNode:
class TreeNode:
    def __init__(self, val):
        this.val = val
        this.left, this.right = None, None
"""


class Solution:
    """
    @param root: The root of binary tree.
    @return: A list of list of integer include 
             the zig zag level order traversal of its nodes' values
    """
    def preorder(self, root, level, res):
        if root:
            if len(res) < level+1: res.append([])
            if level % 2 == 0: 
                res[level].append(root.val)
            else: 
                res[level].insert(0, root.val)
            self.preorder(root.left, level+1, res)
            self.preorder(root.right, level+1, res)
    def zigzagLevelOrder(self, root):
        self.results = []
        self.preorder(root, 0, self.results)
        return self.results
}

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>(); 
        // write your code here
        if (root == null) return result;
        int maxDepth = 0;            
        while(true) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            dfs(root, level, 0, maxDepth);
            if (level.size() == 0) break;
            result.add(level);
            maxDepth++;
        }
                
        return result;
    }
    
    void dfs(TreeNode root, ArrayList<Integer> level, int depth, int maxDepth) {
        if (root == null || depth > maxDepth) return;
        
        if (depth == maxDepth) {
            level.add(root.val);
        }
        
        dfs(root.left, level, depth+1, maxDepth);
        dfs(root.right, level, depth+1, maxDepth);
        
    }
    
    public ArrayList<ArrayList<Integer>> levelOrder2(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>(); 
        // write your code here
        if (root == null) return result;
        
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        
        while(!q.isEmpty()){
             ArrayList<Integer> level = new ArrayList<Integer>();
             
             for(int size = q.size(), i = 0; i < size; i++) {
                TreeNode temp = q.poll();
                 level.add(temp.val);
             
                 if (temp.left != null) q.offer(temp.left); // no c style
                 if (temp.right != null) q.offer(temp.right);
             }
             
             result.add(level);
            
        }
             
        return result;
        
        
    }