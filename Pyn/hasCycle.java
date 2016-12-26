    public boolean hasCycle(ListNode head) {
        // write your code here
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null && fast.next != null && fast != slow) {
            fast = fast.next.next;
            slow = slow.next; //fast.next;
        }

        return !(fast == null || fast.next == null);
    }

        public Boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode fast, slow;
        fast = head.next;
        slow = head;
        while (fast != slow) {
            if(fast==null || fast.next==null)
                return false;
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }