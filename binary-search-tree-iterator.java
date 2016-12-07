public class binary-search-tree-iterator {
    private Stack<TreeNode> stack = new Stack<>();
    private TreeNode curt;
    
    // @param root: The root of binary tree.
    public Solution(TreeNode root) {
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

public class Solution2 {
    //@param root: The root of binary tree.
    private ArrayList<TreeNode> list;
    private int index;
    public Solution(TreeNode root) {
        // write your code here
        this.index = 0;
        this.list = new ArrayList<TreeNode>();
        
        dfs(root, list);
        
     }
     
     public void dfs(TreeNode root, ArrayList<TreeNode> list) {
         if (root == null) return;
        Stack<TreeNode> s = new Stack<TreeNode>(); 
        TreeNode cur = root;
          
         while(true){
             while(cur != null) {
                 s.push(cur);
                 cur = cur.left;
             }
             if (s.isEmpty()) break;
             cur= s.pop();
             list.add(cur);
              cur = cur.right; //error ->
        }
 
        // dfs(root->left, list);
        // list.add(root);
        // dfs(root->right, list);
        
        
       
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        // write your code here
        if (index < list.size()) return true;
        else return false;
    }
    
    //@return: return next node
    public TreeNode next() {
        // write your code here
        return list.get(index++);
    }
}