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

        