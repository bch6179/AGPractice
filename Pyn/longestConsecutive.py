# /**
#  * Given an unsorted array of integers, find the length of the longest
#  * consecutive elements sequence.
#  * 
#  * For example,
#  * Given [100, 4, 200, 1, 3, 2],
#  * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its
#  * length: 4.
#  * 
#  * Your algorithm should run in O(n) complexity.
#  * 
#  * Tags: Array, HashTable
#  */

import collections
class Solution(object):
    def longestConsecutive(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        map = {} #collections.defaultdict(lambda: int)
        res = 0
        for num in nums:
            if num in map: continue #Mistake if not skip, it will overwrite in map[num]=num
            low = map.get(num-1,  num)
            high = map.get(num+1,  num)
            res = max(res, high-low+1)
            map[num]=num
            map[low] = high
            map[high] = low
        return res   
s = Solution() 
print s.longestConsecutive([-2,-3,-3,7,-3,0,5,0,-8,-4,-1,2])