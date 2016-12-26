 
package TechHouse.LEC;


import java.util.*;
 class TreeNode{
 	int val;
 	TreeNode left;
 	TreeNode right;
 	 public TreeNode(int val) {
        this.val = val;
         this.left = this.right = null;
     }
 }

public class LEC3{

	public static void dfs(TreeNode root, ArrayList<Integer> result) {

		if (root == null) return;
		result.add(root.val);
		dfs(root.left,  result);
		dfs(root.right, result);

	}
	public static ArrayList<Integer> dcs(TreeNode root ) {
ArrayList<Integer> result = new ArrayList<Integer>();
		if (root == null) return;
	ArrayList<Integer>  leftArray =	dcs(root.left,  result);
	ArrayList<Integer>  rightArray 	= dcs(root.right, result);
		result.add(root.val);
		result.addAll(leftArray);
		 result.addAll(rightArray);


	}

	public static int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int left = maxDepth(root.left);
		int right = maxDepth(root.right);
		return Math.max(left, right)+1;

	}

 public void BFSPrint(TreeNode root) {
 	Queue<TreeNode> q = new LinkedList<TreeNode>();
 	q.add(root);
 	while(!q.isEmpty()) {
 		TreeNode cur = q.poll();
 		System.out.println(cur.val);
 		q.add(cur.left);
 		q.add(cur.right);

 	}
 }

 
public static void main() {
 	TreeNode root = new TreeNode(1);
 	root.left = new TreeNode(2);
 	 root.right = new TreeNode(3);

	System.out.println("traverse dfs:");
 	ArrayList<Integer> result = new ArrayList<Integer>();
 	dfs(root, result);

 	for(Integer d:result) System.out.println(d);
 }
}
