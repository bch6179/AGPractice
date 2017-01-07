﻿#category main scenarios , don't too much try to merge the upper M and normal one by looking at the conditions
# check edge case [3 1] 1 test cases like A[m]
# = A[begin] since we use this as decision 
 # 2. if nums[m] >= nums[begin], target >= nums[begin]
 #.check infinite begin < end, then verify the last begin at the end is target or not 
#  or <=   in side loop ,nums[m] verify it , and no infinite due to m+1, m-1

# ii DON'T consider nums[begin] == nums[begin]+1,since the key is equal to m or not, if not, still follow the cut, m-1, or m+1; and ---^------, or ------------, only this cases extra; if confused , then looking another perspective

 #   different method. If we see A[l]==A[m] or A[m]==A[r], we can increase the value of l, or decrease value of r until we get a different value of A[l] or A[r]

class Solution(object):
    def searchI(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        begin = 0
        end = len(nums)-1
        
        while begin < end:
            m = begin + (end-begin)/2
            if target == nums[m]:
                return m
            if nums[m] >= nums[begin]:  #Mistake [3 1] 1,  we need >= not to miss 1, let the edge case occur in the next search range; 1 is at the lower right,need begin adjust for target == end
                if target < nums[m] and target >= nums[begin]: #Mistake don't miss it if target == begin, so need >= , target  at upper right, nums[m] == nums[end]
                    end = m -1
                else:
                    begin = m+1
            else:
                if target > nums[m] and target <= nums[end]:
                    begin = m + 1
                else:
                    end = m - 1
            
        
        return -1 if nums[begin] != target else begin

       

    def searchII(self, nums, target): #duplicate
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        begin = 0
        end = len(nums)-1
        
        while begin < end:
            m = begin + (end-begin)/2
            if target == nums[m]:
                return m
    
            flag = False
            while begin < end and nums[begin] == nums[m]:
                begin += 1 
                flag = True
            while begin < end and nums[end] == nums[m]:
                end -= 1
                flag = True
            if flag:
                continue
            if nums[m] >= nums[begin]:  
                if target < nums[m] and target >= nums[begin]:  
                    end = m -1
                else:
                    begin = m+1
            else:
                if target > nums[m] and target <= nums[end]:
                    begin = m + 1
                else:
                    end = m - 1
            
        
        return -1 if nums[begin] != target else begin
s = Solution() 
print s.search([1,1,3,1,1,1,1,1,1],3)
print s.search([1,1,1,1,1,1,3,1,1,1,1],5)