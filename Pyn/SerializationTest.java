package octST;

import static org.junit.Assert.*;

import java.util.*;

import octST.TreeNode;

import org.junit.BeforeClass;
import org.junit.Test;

public class SerializationTest {
	static TreeNode  head = new TreeNode(0);
	
	@BeforeClass
	public static void init() {
 		TreeNode d1 = new TreeNode(1);
		TreeNode d2 = new TreeNode(2);
		TreeNode d3 = new TreeNode(3);
		head.left = d1;
		head.right = d2;
		d2.right = d3;
	}
	
	public void bfsPrint(TreeNode head) {
		 Queue<TreeNode> q = new LinkedList<>();
	        q.offer(head);
	        while(!q.isEmpty()) {
	        	
	            int size = q.size();
	            
	            for(int i = 0; i  < size; i++) {
	            	TreeNode cur = q.poll();

	                System.out.print(cur.val);
	                if (cur.left != null) q.offer(cur.left );
	                else System.out.print("*");
	                if (cur.right != null) q.offer(cur.right );
	                else System.out.print("*");
	            
	            }
	          
	            System.out.println();

	            
	        }
	}
	@Test
	public void test() {
		Serialization se = new Serialization();
        String result = se.Serialize(head);
        System.out.println(result);
        TreeNode  head = se.deserialize(result);
        bfsPrint(head);
 	}
	
	@Test
	public void mergeSortedArrayTest() {
	       int[] A = {1 , 2, 3,0 ,0};
	        int[] B = {4, 5};
	       MergeSortedArray solution= new MergeSortedArray();
	        solution.mergeSortedArray(A, 3, B, 2);
	        for(int i = 0; i < 5; i++ )
	        System.out.println(A[i]);
 	}
}
