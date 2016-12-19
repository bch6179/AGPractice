def findLeaves(self, root):
    def dfs(node):
        if not node:
            return -1
        i = 1 + max(dfs(node.left), dfs(node.right))  #leaf, hight is 0, stored here
        if i == len(out):
            out.append([])
        out[i].append(node.val)
        return i
    out = []
    dfs(root)
    return out

