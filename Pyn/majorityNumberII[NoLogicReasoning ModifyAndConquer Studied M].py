class Solution(object):
    def majorityElement(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        if not nums: return []
        ca,cb, a, b = 0,0,0,1
        for num in nums:
            if a == num:  #mistake: should be before ca == 0, otherwise,a: 8,b: 8 
                ca += 1 
            elif b == num:
                cb += 1
            elif ca == 0:
                ca = 1;a= num
            elif cb == 0:
                cb = 1;b = num
            else:
                ca -= 1; cb -= 1
        return [v for v in set([a,b]) if nums.count(v) > len(nums)/3]
    
s = Solution() 
print s.majorityElement([8,8,7,7,7])