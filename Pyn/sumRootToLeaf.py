# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    
    def __init__(self):
        self.res = 0
        
    def sumNumbers(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
    
        def dfs(root, pv):
            if not root: return 
            if not root.left and not root.right:
                self.res +=  pv*10+root.val 
                return
            dfs(root.left, pv*10+root.val)
            dfs(root.right, pv*10+root.val)
            
        dfs(root, 0)
        return self.res
        
        