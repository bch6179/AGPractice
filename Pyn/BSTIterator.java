import java.util.*;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be
 * initialized with the root node of a BST.
 * 
 * Calling next() will return the next smallest number in the BST.
 * 
 * Note: next() and hasNext() should run in average O(1) time and uses O(h)
 * memory, where h is the height of the tree.
 * 
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 * 
 * Tags: Tree, Stack
 */
class BSTIterator {
    public static void main(String[] args) {
        
    }
    
    Stack<TreeNode> s;
    
    /**
     * Push all left child, and left child's left child and on and on to stack
     */
    public BSTIterator(TreeNode root) {
        s = new Stack<TreeNode>();
        pushAll(root); // push the whole left subtree
    }
    
    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !s.isEmpty();
    }
    
    /**
     * @return the next smallest number s
     */
    public int next() {
        TreeNode n = s.pop();
        pushAll(n.right); // put left subtree of right child
        return n.val;
    }
    
    void pushAll(TreeNode root) {
        while (root != null) {
            s.push(root);
            root = root.left;
        }
    }
    
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}


public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();
    private TreeNode curt;
    
    // @param root: The root of binary tree.
    public BSTIterator(TreeNode root) {
        curt = root;
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        return (curt != null || !stack.isEmpty());
    }
    
    //@return: return next node
    public TreeNode next() {
        while (curt != null) {
            stack.push(curt);
            curt = curt.left;
        }
        
        curt = stack.pop();
        TreeNode node = curt;
        curt = curt.right;
        
        return node;
    }
}

//Mistake throw exception for {-1} if exactly copying inorder traversal;