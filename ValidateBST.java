    public boolean isValidBST(TreeNode root) {
        // write your code here
        return helper(root, null, null);
    //     if (root == null) return true;
    //     Stack<TreeNode> s = new Stack<>();
 
    //   int prev = Integer.MIN_VALUE;  // not able to handle MIN_VALUE
        
    //     while(root != null || !s.isEmpty()) {
    //         if (root != null ) {
            
    //             s.push(root);
    //             root = root.left;
    //         }
    //         else {
    //             root = s.pop();
              
    //             if (root.val <= prev) return false;
            
    //             prev = root.val;
                
    //             root = root.right;
                
    //         }
    //     }
    //     return true;
        
    }
    public boolean helper(TreeNode root, TreeNode min, TreeNode max) {
        
        if (root == null) return true;
        
        if (min != null && root.val <= min.val ||
      max != null && root.val >= max.val) { // >=
            return false;
        }
        
        return helper(root.left, min, root) && helper(root.right, root, max);
    }
    
  // to handle MIN_VALUE 
        public boolean isValidBST(TreeNode root) {
        // write your code here
       // return helper(root, null, null);
        if (root == null) return true;
        Stack<TreeNode> s = new Stack<>();
 
      TreeNode prev = null;
        
        while(root != null || !s.isEmpty()) {
            if (root != null ) {
            
                s.push(root);
                root = root.left;
            }
            else {
                root = s.pop();
              
                if (prev != null && root.val <= prev.val) return false;
            
                prev = root;
                
                root = root.right;
                
            }
        }
        return true;
        
    }