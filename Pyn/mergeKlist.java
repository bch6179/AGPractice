  ListNode mergeKList(List<ListNode> list) {
        //if empty
        int end = list.size() - 1;
        //move this later int begin = 0;

        while(end > 0) {  // not begin < end
            int begin = 0;
            while(begin < end) {
                list.set(begin, mergeList(list.get(begin), list.get(end)));
                begin++;
                end--;
            }
        }

        return list.get(0);  // not get(begin)

  }

  private ListNode mergeList(ListNode l1, ListNode l2) {
          ListNode dh = new ListNode(0);
          ListNode tail = dh;
          while(l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    tail.next = l1;
                    l1 = l1.next;
                }
                else {  //forget this half
                    tail.next = l2;
                    l2 = l2.next;
                }
                tail = tail.next; //forget this
          }
        if (l1 != null) tail.next = l1;
        if (l2 != null) tail.next = l2;
           // while(l1 != null) {
          //   tail.next = l1;//falt
          // l1 = l1.next;
          // }
          // while(l2 != null) {
          //   tail.next = l2;
          //   l2 = l2.next;
          // }

          return dh.next;
  }

    private static final Comparator<ListNode> listComparator =
        new Comparator<ListNode>() {
            @Override
            public int compare(ListNode x, ListNode y) {
                return x.val - y.val;
            }
        }
    ListNode mergeKList(List<ListNode> lists) {


        Queue<ListNode> q = new PriorityQueue<>(lists.size(), listComparator);

        for (ListNode list : lists ) {

            if (list != null) q.add(list); //forgot null check
        }

        ListNode dh = new ListNode(0);
        ListNode p = dh;

        while(!q.isEmpty()) {
            ListNode node = q.poll();
            p.next = node;
            p = p.next;

            if (node.next != null) {
                q.add(node.next);
            }
        }
        return dh.next;
    }



/**
     * Use a heap, O(n * log(k))
     */
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) return null;
        // Build priority queue
        Queue<ListNode> queue = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>() {
            @Override
            public int compare(ListNode n1, ListNode n2) {
                return n1.val - n2.val;
            }
        });
        for (ListNode n : lists) if (n != null) queue.add(n);

        ListNode dummy = new ListNode(0); // set dummy head
        ListNode tail = dummy;
        while (!queue.isEmpty()) { // build next
            tail.next = queue.poll();
            tail = tail.next;
            if (tail.next != null) queue.add(tail.next);
        }
        return dummy.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * Divide and conquer
     * merge two halves, divide to merge two lists
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // next node should be the result of comparison
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2); // notice l1.next
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next); // notice l2.next
            return l2;
        }
    }

    public ListNode mergeKLists(List<ListNode> lists) {
        /*base cases*/
        if (lists.size() == 0) return null;
        if (lists.size() == 1) return lists.get(0);
        if (lists.size() == 2) return mergeTwoLists(lists.get(0), lists.get(1));
        /*merge two halves*/
        return mergeTwoLists(mergeKLists(lists.subList(0, lists.size()/2)),
            mergeKLists(lists.subList(lists.size()/2, lists.size())));
    }