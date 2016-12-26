


//stack left, stack right, print

List<Integer> postOrder1(TreeNode root) {

    List<Integer> res = new ArrayList<Integer>();
    if (root == null) {
        return res;
    }
    Stack<TreeNode> s1 = new Stack<TreeNode>();
    TreeNode pre = null;
    TreeNode cur = root;

    while(cur || !s1.isEmpty()) {
        if (cur != null) {  //push left to stack
            s1.push(cur);
            cur = cur.left;
        }
        else {
            TreeNode peek = s1.peek()

            if (peek.right != null && peek.right != pre)// (lastVisit == cur.left && but right not visit), push right to stack, go down
                cur = peak.right;
            }
            else {   // both visit  , print, pop, next should continue peek
                System.out.println(cur.value);
                res.add(peek.value);
               // cur = cur.right;
                pre = peek;
                s1.pop();
            }


    }
}

public ArrayList<Integer> postorderTraversal_KennyBad(TreeNode root) {
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
                 cur = s.peek();

                 if (cur.right != null && cur.right != prev) {
                     s.push(cur.right); // actually no need to push
                      prev = cur;

                     cur = cur.right;

                 }
                 else {//if (cur.right == null || cur.right == prev) {
                     result.add(cur.val);
                     s.pop();
                      prev = cur;

                     cur = null;
                 }

            }
        }
    return result;
    }



}

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



// if (root != null) {
//     s.push(root)
//     root = root.left;
// }
// else {
//     cur = s.pop();  // below two cases can be merged, and do from the opposite
//    if (cur.right == null && cur.left == null){
//             res.add(cur.val);
//    } 
//     else //right to up
//     if (cur.right != null && last == cur.right) {
//         res.add(cur.val);
//     }
//     else if (cur.left == null) {  // impossible 
//           s.push(cur);
//             root = cur.right;
//             last = root;
//     }
