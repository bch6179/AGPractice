class ListNode:
    def __init__(self, val, next = None):
        self.val = val
        self.next = next
    def __repr__(self):
        return """ListNode(val=%r, next=%r""" % (self.val, self.next)
class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None
    def __repr__(self):
        return """TreeNode(val=%r, left=%r, right=%r""" % (self.val, self.left, self.right)
