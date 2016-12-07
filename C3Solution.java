public class C3Solution{

	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int left = maxDepth(root.left);
		int right = maxDepth(root.right);
		return Math.max(left, right)+1;

	}

	  public boolean isBalanced(TreeNode root) {
        return maxDepthBLS(root) != -1;
    }

	 private int maxDepthBLS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if (left == -1 || right == -1 || Math.abs(left-right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
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
             
                 if (temp.left != null) q.offer(temp.left); // no c style,require boolean
                 if (temp.right != null) q.offer(temp.right);
             }
             
             result.add(level);
            
        }
             
        return result;
        
        
    }
}
// public TreeNode insertNode(TreeNode root, TreeNode node) {
//         // write your code here
//         if (root == null) return node;
      
//         TreeNode temp = dfs(root,node);
//         if (temp.val < node.val) temp.right = node;
//         else temp.left = node;
        
//         return root;
//     }
    
//     private TreeNode  dfs(TreeNode  root, TreeNode node) {
//         if (root.right == null && root.left == null)  return root;
//         if (root.right != null){
//             if (root.val < node.val) {
//                  dfs(root.right,node);
//             }else return root;
//         }else if (root.left != null) {
//             if (root.val > node.val) {
//                  dfs(root.left,node);
//             }else return root;
//         }
//         return root;

//     }