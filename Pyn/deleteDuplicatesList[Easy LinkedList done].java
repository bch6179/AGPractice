package notTested;

public ListNode deleteDuplicates_I(ListNode head) {

       ListNode cur =head,p =null;

        while(cur!= null) {
            p = cur.next;
            while((p!=null)  && (p.val == cur.val)) {
                   p = p.next;
           }
           cur.next = p;
           cur = p;
        }

        return head;
    }

      
   
  public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dm = new ListNode(0);
        dm.next = head;
        head = dm;

        while(head != null && head.next != null && head.next.next != null) {
            ListNode cur = head;
            while(cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            head.next = cur.next;
            head = head.next; // [1 1 1] last 1 head== null, and you goback to check head.next , exception
        }
        return dm.next;
    }