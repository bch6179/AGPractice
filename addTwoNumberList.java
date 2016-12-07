package notTested;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param l1: the first list
     * @param l2: the second list
     * @return: the sum list of l1 and l2
     */
    public ListNode      {
        // write your code here
        ListNode dh = new ListNode(0);
        ListNode head = dh;
          int carry = 0;
        int d1 = 0, d2 = 0;
        while(l1 != null || l2 != null) {

            if (l1 != null) {
                 d1 = l1.val;
                 l1 = l1.next;
            }
            else d1 = 0;

             if (l2 != null) {
                 d2 = l2.val;
                 l2 = l2.next;
            }
            else d2 = 0;

             int data = d1 + d2 + carry;
            carry = data / 10;
            head.next = new ListNode(data % 10);
            head =head.next;
        }

        if (carry > 0) {
            head.next = new ListNode(carry);
        }

        return dh.next;
    }
}
