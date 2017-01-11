package notTested;

import octST.TreeNode;

import java.util.*;

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
  class Myiter {
	  private String[] data;
		private int loc;
	  
	public Myiter(String in) {
		this.data = in.split(",");
		//data = in;
		loc = 0;
	}
   
    public  String next() {
       return data[loc++];
      
    }
	 public boolean hasNext(){
		return loc < data.length;
	 }
		 
	 
 }
class Serialization {
    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String Serialize(TreeNode root) {
    	  // write your code here
        if (root == null) return null;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        StringBuilder result = new StringBuilder();
        TreeNode dm = new TreeNode(0);
        
        while(true) {
            int size = q.size();
            StringBuilder level = new StringBuilder();
                  
            boolean cont = false;
            
            for (int i = 0;i < size; i++) {
                TreeNode temp = q.poll();
                if (temp == dm) {
                   level.append("#,");
                   
                 }
                 else {
                     level.append(temp.val+ ",");
                     cont = true;
                 }
                q.offer(temp.left == null? dm : temp.left);
                q.offer(temp.right == null? dm : temp.right);

             }
             if (cont == false) break;
            result.append(level);

        
        }
        result.setLength(result.length() - 1);
        return result.toString();
        
    }
    
    public TreeNode deserialize(String data) {
    	
    
    }
 
    public TreeNode deserialize2(String data) {
    	if (data == null || data.length() == 0) return null;
    	  TreeNode dm = new TreeNode(0);
   
          LinkedList<TreeNode> current = new LinkedList<TreeNode>();
          LinkedList<TreeNode> next = new LinkedList<TreeNode>();
          Myiter iter = new Myiter(data);
      	 TreeNode head = new TreeNode(Integer.parseInt(iter.next()));

          current.offer(head);
           while(!current.isEmpty() && iter.hasNext()) {
          	 TreeNode node = current.remove();
           	 String temp =iter.next();
          	  if (!temp.equals("#")) {
          		  node.left = new  TreeNode(Integer.parseInt(temp));
                    next.add(node.left);

          	  }
          	 if (! iter.hasNext()) break;
          	 temp  = iter.next();
          	 if (!temp.equals("#")) {
         		  node.right = new  TreeNode(Integer.parseInt(temp));
                   next.add(node.right);

         	  }
               if(current.isEmpty()){
                   current = next;
                   next = new LinkedList<TreeNode>();
                   
               }
          }
       	return head;

    }
     
    public TreeNode deserializeBad(String data) {
        TreeNode dm = new TreeNode(0);
        if (data == null || data.length() == 0) return dm;
        
    //	Queue<TreeNode> q = new LinkedList<>();
    	
//    	for (int i = 0; i < data.length(); i++) {
//    		char x = data.charAt(i);
//    		q.offer(x == '#' ? dm : new TreeNode(x - '0'));
//    	}

    	
    	LinkedList<TreeNode> current = new LinkedList<TreeNode>();
        LinkedList<TreeNode> next = new LinkedList<TreeNode>();
        Myiter iter = new Myiter(data);
    	TreeNode head = new TreeNode(iter.next());

        current.offer(head);
         while(!current.isEmpty() && iter.hasNext()) {
        	 TreeNode node = current.remove();
         	 char temp =iter.next();
        	  if (temp != '#') {
        		  node.left = new  TreeNode(temp);
                  next.add(node.left);

        	  }
        	 if (! iter.hasNext()) break;
        	 temp  = iter.next();
        	 if (temp != '#') {
        		  node.right = new  TreeNode(temp);
              
                 next.add(node.right);
        	  }
             if(current.isEmpty()){
                 current = next;
                 next = new LinkedList<TreeNode>();
                 
             }
        }
     	return head;

    }
//    {
//    	void Serialize(TreeNode * node, vector<char> &output)
//    	{
//    	       if(node == NULL)
//    	       {
//    	             output.push_back('#');
//    	             return;
//    	       }
//
//    	       output.push_back(node->val + '0');
//    	       Serialize(node->left, output);
//    	       Serialize(node->right, output);
//    	}
//
//    	而反序列化的代码也就是：
//
//    	TreeNode *Deserialize(vector<char> output, int &index)
//    	{
//    	       if(index > output.size() || output[index] == '#') return NULL;
//
//    	       TreeNode *node = new TreeNode(output[index] -'0');
//    	       index ++;
//    	       node->left = Deserialize(output, index);
//    	       index++;
//    	       node->right = Deserialize(output, index);
//    	       return node;
//    	}
//    }
//    	while(level != null && level.size() > 0) {
//        	ArrayList<TreeNode> level2  = new ArrayList<>();
//    		for(TreeNode temp : level) {
//    			TreeNode left = q.poll();
//    		 
//    			 temp.left = left == dm ? null : left;
//     			TreeNode right = q.poll();
//
//    			 temp.right = right == dm ? null : right;
//    			 if (temp.left != null)
//    				 level2.add(temp.left);
//    			 if (temp.right != null)
//    				 level2.add(temp.right);
//    		}
//			level = level2;
//    	}
    
      
}