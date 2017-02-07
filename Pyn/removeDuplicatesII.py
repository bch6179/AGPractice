class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        flag = False
        
        if len(nums) <= 2: return len(nums) #Mistake return len not nums
        prev = nums[0]
        toIndex = 1
        i = 1
        while i < len(nums):
            if nums[i] != prev or not flag:
                nums[toIndex] = nums[i]  
                flag = False  #Mistake [1,1,2,3] generated for [1,1,2,2,3]
                toIndex+=1
                if nums[i] == prev:
                    flag = True
                prev = nums[i] #Mistake use prev instead of nums[i-1], which is changed 
            i+=1
        return toIndex
def removeDuplicates(self, nums):
    i = 0
    for n in nums:
        if i < 2 or n > nums[i-2]:
            nums[i] = n
            i += 1
    return i

#I No duplicate allowed
#     public int removeDuplicates(int[] nums) {
#     int i = 0;
#     for(int n : nums)
#         if(i < 1 || n > nums[i - 1]) 
#             nums[i++] = n;
#     return i;
# }