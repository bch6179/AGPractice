 
#  eetCode — Integer to English WordsLeetCode — Strobogrammatic Number  
# LeetCode — Verify Preorder Sequence in Binary Search Tree

# Problem:

# Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

# You may assume each number in the sequence is unique.

# Follow up:
# Could you do it using only constant space complexity?

# Thought:

# 这题主要有两种想法：

# DFS, BST我们可以找到root， 左边tree， 右边tree。然后往下DFS判断。右边的不能比root小。这就是最大的准则。
# Print Preorder Sequence with Inorder Sequence. 这个算法特别简单，太厉害了！
# 下面所有的解法都要看！
  

public class Solution {
    // Solution 1
    //O(n) space O(n) time
    public boolean verifyPreorder(int[] preorder){
        if(preorder == null || preorder.length == 0) return true;
         
        LinkedList<Integer> stack = new LinkedList<Integer>();
        ArrayList<Integer> inorder = new ArrayList<Integer>();
         
        for(int i: preorder){
            if(!inorder.isEmpty() && i < inorder.get(inorder.size()-1)){
                return false;
            }
             
            while(!stack.isEmpty() && i > stack.peek()){
                inorder.add(stack.pop());
            }
            stack.push(i);
        }
        return true;
    }
     
    //Solution II
    //O(n) space O(n) time
    public boolean verifyPreorder(int[] preorder){
        if(preorder == null || preorder.length == 0) return true;
         
        LinkedList<Integer> stack = new LinkedList<Integer>();
        //reduce inorder with an integer
        int inorder = -1;
         
        for(int i: preorder){
            if(inorder != -1 && i < inorder){
                return false;
            }
             
            while(!stack.isEmpty() && i > stack.peek()){
                inorder =stack.pop();
            }
            stack.push(i);
        }
        return true;
    }
     
    //Solution III: O(1) space O(n) time -- 怎么做的啊！！
    public boolean verifyPreorder(int[] preorder){
        if(preorder == null || preorder.length == 0) return true;
        //reduce stack to an integer
        int stack = -1;
        //reduce inorder with an integer
        int inorder = -1;
         
        for(int i: preorder){
            if(i < inorder){
                return false;
            }
             
            while(stack != -1 && i > preorder[stack]){
                inorder = preorder[stack];
                stack = stack-1;
            }
            stack++;
            preorder[stack] = i;
        }
        return true;
    }
     
    //Solution V 
    public boolean verifyPreorder(int[] preorder) {
        if(preorder == null || preorder.length == 0 ) return true;
        return verifyHelper(preorder, 0, preorder.length-1);
    }
     