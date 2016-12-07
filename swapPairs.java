/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    /**
     * @param head a ListNode
     * @return a ListNode
     */
    public ListNode swapPairs(ListNode head) {
        // Write your code here
        if (head == null || head.next == null) return head;
        ListNode dm = new ListNode(0);
        ListNode prev = dm;
        prev.next = head;
        ListNode p1 = head;

        while(p1 != null && p1.next != null) {
            ListNode p2 = p1.next;
            ListNode next = p2.next;
            p2.next = p1;
            p1.next = null;
            prev.next = p2;

            prev = p1;
            p1 = next;

        }
        if (p1 != null) {
            prev.next = p1; // Handle the last signle node, since it's not doing in the loop
        }
        return dm.next;
    }
}