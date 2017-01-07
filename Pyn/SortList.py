# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None
from base import *
 
class Solution(object):
    def sortList(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        #3->2->1->4
        def mergeSort(head):
            if head == None or head.next == None:
                return head
            
            slow = head
            fast = head.next
            while fast and fast.next:
                slow = slow.next
                fast = fast.next.next
            second = slow.next
            slow.next = None
            
            l = mergeSort(head)
            r = mergeSort(second)
            return merge(l,r)
        def merge(l,r):
            if l == None:
                return r
            if r == None:
                return l
            dummy = ListNode(0)
            cur = dummy
            while l and r:
                if l.val < r.val:
                    cur.next = l
                    l = l.next
                else:
                    cur.next = r
                    r = r.next
                cur = cur.next #Mistake how can you foget next to cur and not test out




# if (head == null || head.next == null) return head;
#         ListNode dummy = new ListNode(Integer.MIN_VALUE);
#         dummy.next = head;
#         ListNode cur = head.next;
#         head.next = null;  //important,  1-> 1->null , 3-2-4-null
#         while (cur != null) {
#             ListNode tmp = dummy;
#             while (tmp.next != null && tmp.next.val <= cur.val) tmp = tmp.next;
#             ListNode next = cur.next;
#             cur.next = tmp.next;
#             tmp.next = cur;
#             cur = next;
#         }
#         return dummy.next;
#     }





































            if l:
                cur.next = l
            if r:
                cur.next =r
            return dummy.next
                    
        def mergeDfs(l, r):
            if l == None:
                return r
            if r == None:
                return l
            if l.val < r.val:
                l.next = merge(l.next, r)
                return l
            else:
                r.next = merge(l, r.next)
                return r
        return mergeSort(head)
s = Solution() 
print s.sortList(ListNode(2, ListNode(1)))