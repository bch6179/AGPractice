

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
     * @return: Postorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        // write your code here
         ArrayList<Integer> result = new ArrayList<>();
         
        TreeNode prev = null;
        Stack<TreeNode> s = new Stack<>();
         TreeNode cur = root;
        
            while (cur != null || !s.isEmpty()) {
    
             if (cur != null) {
                s.push(cur);
                cur = cur.left;
     
             }
             else {
                 TreeNode peek = s.peek(); // once here, it means already  go down to the  leftmost, now time for searching right sub tree
    
                 if (peek.right != null && peek.right != prev) {
                    //  s.push(peek.right);
                    //   prev = cur;

                     cur = peek.right;
    
                 }
                 else {//if (cur.right == null || cur.right == prev) {
                     result.add(peek.val);
                     prev = peek; // only need to save prev when print

                     s.pop();
    
                  }
                 
            }
        }
    return result;
    }
}
                  