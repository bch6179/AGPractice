def largestRectangleArea(heights):
        """
        :type heights: List[int]
        :rtype: int
        """
        stack = [-1]
        res = 0
        heights.append(0)
        for i, h in enumerate(heights): 
            while h < heights[stack[-1]]: # stack always non None,0 the minimum as the first top  , so skip the check
                curHeight = heights[stack.pop()]
                w = i - stack[-1] - 1
                res = max(res, w * curHeight)
            stack.append(i) #Mistake should append i in stead of h, otherwise [2] input make it out of range
        heights.pop()    
        return res
def largestRectangleArea2( height):
    height.append(0)
    stack = [-1]
    ans = 0
    for i in range(len(height)):
        while height[i] < height[stack[-1]]:
            h = height[stack.pop()]
            w = i - stack[-1] - 1  #when pop 5, stack[-1] is the leftmost which is next lower to it, that is not popped in last round; by heap, no need to search for the right most higher and also no need to adjust left since the last round pop keep that for you.
            ans = max(ans, h * w)
        stack.append(i)
    height.pop()
    return ans


print largestRectangleArea([2])
print largestRectangleArea([2, 7, 5,6,4])
