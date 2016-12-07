import collections

class Solution(object):
    def containsNearbyDuplicate(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: bool
        """
        hash = {}
        
        for i in range(len(nums)):
            if nums[i] in hash:
                if i - hash[nums[i]] <= k:
                    return True
            hash[nums[i]] = i
        return False

