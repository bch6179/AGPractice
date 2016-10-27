import java.util.*;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 * 
 * The left subtree of a node contains only nodes with keys less than the
 * node's key.
 * The right subtree of a node contains only nodes with keys greater than the
 * node's key.
 * Both the left and right subtrees must also be binary search trees.
 * 
 * Tags: Tree, DFS
 */
class ValidateBST {

    public static void main(String[] args) {
        TreeNode r = new TreeNode(Integer.MAX_VALUE);
        ValidateBST v = new ValidateBST();
        System.out.println(v.isValidBST(r));
    }
    
    Integer pred = null;
    
    /**
     * Recursive
     * Check current node
     * Check left subtree
     * Compare with current node and set predecessor
     * Check right subtree
     */
    public boolean isValidBST(TreeNode root) { 
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        // visit
        if (pred != null && root.val <= pred) return false;
        pred = root.val; // set
        if (!isValidBST(root.right)) return false;
        return true;
    }
    
    /**
     * Failed if input include Integer MAX and Integer MIN
     */
    public boolean isValidBSTB(TreeNode root) {
        return isValidBSTB(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    // add range of current value and do recursive check
    public boolean isValidBSTB(TreeNode root, int min, int max) {
        return root == null || root.val > min && root.val < max && isValidBSTB(root.left, min, root.val) && isValidBSTB(root.right, root.val, max);
    }
    
    /**
     * Inorder traversal, generate a list, should be increasing order
     */
    public boolean isValidBSTC(TreeNode root) {
        if (root == null) return true;
        List<Integer> result = new ArrayList<Integer>();
        inOrderList(root, result);
        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i) >= result.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
    
    public void inOrderList(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inOrderList(root.left, res);
        res.add(root.val);
        inOrderList(root.right, res);
    }
    
    /**
     * Preorder
     * Check if root.val is bigger than value of rightmost node in left subtree
     * and smaller than value of leftmost node in right subtree.
     */
    public boolean isValidBSTD(TreeNode root) {
        if (root == null) return true;
        TreeNode temp = null;
        if (root.left != null) {
            temp = root.left;
            while (temp.right != null) { // move to right most 
                temp = temp.right;
            }
            if (temp.val >= root.val) return false;
        }
        if (root.right != null) {
            temp = root.right;
            while (temp.left != null) { // move to left most
                temp = temp.left;
            }
            if (temp.val <= root.val) return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }
    
    // inorder, 
    
    
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    // version 1 Traverse
public class Solution {
    private int lastVal = Integer.MIN_VALUE;
    private boolean firstNode = true;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (!firstNode && lastVal >= root.val) {
            return false;
        }
        firstNode = false;
        lastVal = root.val;
        if (!isValidBST(root.right)) {
            return false;
        }
        return true;
    }
}


// version 2  Divide and Conquer
class ResultType {
    boolean is_bst;
    int maxValue, minValue;
    
    ResultType(boolean is_bst, int maxValue, int minValue) {
        this.is_bst = is_bst;
        this.maxValue = maxValue;
        this.minValue = minValue;
    }
}

public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        ResultType r = validateHelper(root);
        return r.is_bst;
    }
    
    private ResultType validateHelper(TreeNode root) {
        if (root == null) {
            return new ResultType(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        
        ResultType left = validateHelper(root.left);
        ResultType right = validateHelper(root.right);
        
        if (!left.is_bst || !right.is_bst) {
            // if is_bst is false then minValue and maxValue are useless
            return new ResultType(false, 0, 0);
        }
        
        if (root.left != null && left.maxValue >= root.val || 
              root.right != null && right.minValue <= root.val) {
            return new ResultType(false, 0, 0);
        }
        
        return new ResultType(true,
                              Math.max(root.val, right.maxValue),
                              Math.min(root.val, left.minValue));
    }
}
}
