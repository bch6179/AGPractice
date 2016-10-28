import java.util.*;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
 * 
 * Tags: Divide and Conquer, Linkedlist, Heap
 */
class MergeKSortedList {
    public static void main(String[] args) {
        
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
   
}

public class Solution {
    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists.size() == 0) {
            return null;
        }
        return mergeHelper(lists, 0, lists.size() - 1);
    }
    
    private ListNode mergeHelper(List<ListNode> lists, int start, int end) {
        if (start == end) {
            return lists.get(start);
        }
        
        int mid = start + (end - start) / 2;
        ListNode left = mergeHelper(lists, start, mid);
        ListNode right = mergeHelper(lists, mid + 1, end);
        return mergeTwoLists(left, right);
    }
    
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                tail = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                tail = list2;
                list2 = list2.next;
            }
        }
        if (list1 != null) {
            tail.next = list1;
        } else {
            tail.next = list2;
        }
        
        return dummy.next;
    }
}
// Version 3: merge two by two
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
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    public ListNode mergeKLists(List<ListNode> lists) {  
        if (lists == null || lists.size() == 0) {
            return null;
        }
        
        while (lists.size() > 1) {
            List<ListNode> new_lists = new ArrayList<ListNode>();
            for (int i = 0; i + 1 < lists.size(); i += 2) {
                ListNode merged_list = merge(lists.get(i), lists.get(i+1));
                new_lists.add(merged_list);
            }
            if (lists.size() % 2 == 1) {
                new_lists.add(lists.get(lists.size() - 1));
            }
            lists = new_lists;
        }
        
        return lists.get(0);
    }
    
    private ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (a != null && b != null) {
            if (a.val < b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        
        if (a != null) {
            tail.next = a;
        } else {
            tail.next = b;
        }
        
        return dummy.next;
    }
}

