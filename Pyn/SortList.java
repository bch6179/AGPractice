edium Sort List

27% Accepted
Sort a linked list in O(n log n) time using constant space complexity.

Have you met this question in a real interview? Yes
Example
Given 1-3->2->null, sort it to 1->2->3->null.

Tags Expand

  /**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param head: The head of linked list.
     * @return: You should return the head of the sorted linked list,
                    using constant space complexity.
     */
    public ListNode sortList(ListNode head) {
        // write your code here
        ListNode dummy = new ListNode(0);


        while(head != null) {    // head == target
            ListNode cur = dummy;
            while(  cur.next != null && cur.next.val < head.val) { // not needed cur != target
                     cur = cur.next;

            }
            ListNode next = head.next;
              head.next = cur.next.next;  //cur.next == head, no problem
            cur.next = head;
             head = next;

        }

        return dummy.next;
    }

      public ListNode sortList(ListNode head) {
        // write your code here
        ListNode dummy = new ListNode(0);
 

        while(head != null) {    // head == target
            ListNode cur = dummy;
            while(  cur.next != null && cur.next.val < head.val) { // not needed cur != target
                     cur = cur.next;

            }
            ListNode next = head.next;
              head.next = cur.next.next;  //cur.next == head, no problem
            cur.next = head;
             head = next;

        }

        return dummy.next;
    }
}