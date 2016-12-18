# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def sortedListToBST(self, head):
        """
        :type head: ListNode
        :rtype: TreeNode
        """
        def dfs(head):
            if head == None: return None
            if head.next == None: return TreeNode(head.val)
            dummy = TreeNode(0)
            dummy.next = head #Mistake missing head
            slow = dummy
            fast = head
            while fast != None and fast.next != None:
                fast =fast.next.next
                slow = slow.next
            temp = slow.next
            slow.next = None
            node = TreeNode(temp.val)
            node.left = dfs(head)
            node.right = dfs(temp.next) #Mistake here duplicate temp
            return node
        return dfs(head)
            