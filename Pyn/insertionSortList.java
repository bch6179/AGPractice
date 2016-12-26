    public ListNode insertionSortListMyBad(ListNode head) {
        // write your code here
        if (head == null) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode run = head.next;
        while(run != null) {
            ListNode next = run.next;
            ListNode p = head;
              prev = dummy;
            while(p != run) {
                if (run.val < p.val) {
                    prev.next = run;
                    run.next = p;
                    p.next = next;//error
                    break;
                }
                prev = p;
                p = p.next;
            }
            run = next;

        }
        return dummy.next;
    }
 public ListNode insertionSortListMemoryIssue(ListNode head) {
        // write your code here
         if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode run = head.next;
        while(run != null ) {


            ListNode next = run.next;
            prev = dummy;
            while(prev.next.val < run.val) { // 3-2-4-null exception

                prev = prev.next;
            }
                   run.next = prev.next;
                    prev.next = run;


            run = next;

        }
        return dummy.next;
    }

       if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode cur = head.next;
        head.next = null;  //important,  1-> 1->null , 3-2-4-null
        while (cur != null) {
            ListNode tmp = dummy;
            while (tmp.next != null && tmp.next.val <= cur.val) tmp = tmp.next;
            ListNode next = cur.next;
            cur.next = tmp.next;
            tmp.next = cur;
            cur = next;
        }
        return dummy.next;
    }