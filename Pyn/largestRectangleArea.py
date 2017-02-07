# 栈内存储的是高度递增的下标。对于每一个直方图高度，分两种情况。1：当栈空或者当前高度大于栈顶下标所指示的高度时，当前下标入栈。否则，2：当前栈顶出栈，并且用这个下标所指示的高度计算面积。而这个方法为什么只需要一个栈呢？因为当第二种情况时，for循环的循环下标回退，也就让下一次for循环比较当前高度与新的栈顶下标所指示的高度，注意此时的栈顶已经改变由于之前的出栈。
# Java代码：


# stacking the smallest points lower than cur point, when pop cur point, Hcur*(i-stack[-1]-1)
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
