from base import * 
# class TreeNode():
#     def __init__(self, val,left=None,right=None):
#         self.val = val
#         self.left = left
#         self.right = right
#     def __repr__(self):
#         return """TreeNode(val=%r, left=%r, right=%r""" % (self.val, self.left, self.right)
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def inorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        if root == None: return []

        s = []
        res = []
        while root or s:
            if root:
                s.append(root)
                root = root.left
            elif s:
                root = s.pop()
                res.append(root.val)
                root = root.right
        return res

s = Solution() 
#    1 
#  2
#    3
print s.inorderTraversal(TreeNode(1, left=TreeNode(2, right=TreeNode(3))))
              
            

