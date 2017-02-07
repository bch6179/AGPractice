import sys
class Solution(object):
    def __init__(self):
        self.maxSum = -sys.maxsize-1
        
    def maxPathSum(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        def dfs(root):
            if root == None: return 0
            left = dfs(root.left)
            right = dfs(root.right)
            ml = max(0, left )
            mr = max(0, right )
            
            self.maxSum = max(self.maxSum, ml+mr+root.val)
            return  max(ml,mr)+root.val 
            
        dfs(root)
        return self.maxSum

        # test [-1], [1,2,3]