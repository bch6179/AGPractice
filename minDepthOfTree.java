// import this to use @Documented
import java.lang.annotation.*;

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    private int min = Integer.MAX_VALUE;

    public int minDepth(TreeNode root) {
        // write your code here
        // if (root == null) return 0;
        // helper(root, 1);
        return helperDC(root);
    }

    //Travesal Accepted

    //Total Runtime: 2746 ms
    // public void helperTraverse(TreeNode root, int count) { //Error return a value from method whose result type is void
    //     if (root.left == null && root.right == null) {
    //         min = Math.min(min,count);
    //     }

    //     if (root.left != null) {
    //         helper(root.left, count + 1);
    //     }
    //     if (root.right != null) {
    //         helper(root.right, count + 1);
    //     }

    // }
    public int helperDC(TreeNode root) {
        if (root == null) return 0;
        if (root.left ==null) {
            return helperDC(root.right) + 1;
        }
        else if (root.right == null) {
            return  helperDC(root.left) + 1;
        }
        else {
            return Math.min(helperDC(root.left), helperDC(root.right)) + 1;
        }

    }


     /**
     * <h1> minDepth</h1>
     * <p>
     * Recursive
     * Get minDepth of left and right subtree
     * If one side is 0, return the other side plus 1
     * Return the smaller one + 1 </p>
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left == 0) return right + 1;
        if (right == 0) return left + 1;
        return Math.min(left, right) + 1; // plus root
    }

}
