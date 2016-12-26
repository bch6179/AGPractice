/**
 * Created by zhiqi on 10/26/15.
 */
public class nthToLast {
    ListNode nthToLast(ListNode head, int n) {
        // write your code here
        if (head == null) {
            return head;
        }
        ListNode p1 = head;
        int k = 0;
        if (n == 0) n = 1;
        while(p1 != null && k < n) {
            p1 = p1.next;
            k++;
        }
        if (k < n) return null;
        ListNode p2 = head;

        while( p1  != null && p2  != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }
}
