class Solution(object):
    def helper(self, nums, start, tlist, res):
        res.append(list(tlist))
        # if start >= len(nums):#Mistake append before return
        #    return
        for i in range(start, len(nums)):
            tlist.append(nums[i]) #Mistake why set start 
            self.helper(nums, i+1, tlist, res)
            tlist.pop(-1)

    def subsets(self, nums):
        res = []
        self.helper(sorted(nums), 0, [], res)
        return res
s = Solution() 
res = s.subsets([1,2,3])
print res