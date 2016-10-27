442. Find All Duplicates in an Array

public class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n ; i++) {
            if (nums[i] != i + 1) {
                while(nums[i] != Integer.MAX_VALUE && nums[i] != i + 1) {
                    if (nums[i] == nums[nums[i] - 1]) {
                        result.add(nums[i]);
                        nums[i] = Integer.MAX_VALUE;
                        break;
                    }
                    int temp = nums[nums[i] - 1];
                    nums[nums[i] - 1] = nums[i];
                    nums[i] = temp;
                }
            }
        }
        return result;
    }
}


public class Solution {
    // when find a number i, flip the number at position i-1 to negative. 
    // if the number at position i-1 is already negative, i is the number that occurs twice.
    
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            int index = Math.abs(nums[i])-1;
            if (nums[index] < 0)
                res.add(Math.abs(index+1));
            nums[index] = -nums[index];
        }
        return res;
    }
}

解法I 正负号标记法（一趟遍历）

参考LeetCode Discuss：https://discuss.leetcode.com/topic/64735/java-simple-solution

遍历nums，记当前数字为n（取绝对值），将数字n视为下标（因为a[i]∈[1, n]）

当n首次出现时，nums[n - 1]乘以-1

当n再次出现时，则nums[n - 1]一定＜0，将n加入答案
Python代码：
class Solution(object):
    def findDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        ans = []
        for n in nums:
            if nums[abs(n) - 1] < 0:
                ans.append(abs(n))
            else:
                nums[abs(n) - 1] *= -1
        return ans
13
解法II 位置交换法

遍历nums，记当前下标为i

当nums[i] > 0 并且 nums[i] != i + 1时，执行循环：

令n = nums[i]

如果n == nums[n - 1]，则将n加入答案，并将nums[i]置为0

否则，交换nums[i], nums[n - 1]
Python代码：
class Solution(object):
    def findDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        ans = []
        for i in range(len(nums)):
            while nums[i] and nums[i] != i + 1:
                n = nums[i]
                if nums[i] == nums[n - 1]:
                    ans.append(n)
                    nums[i] = 0
                else:
                    nums[i], nums[n - 1] = nums[n - 1], nums[i]
        return ans


        3) prev = sum;
        sum = sum ^ a[i];
                 if () xx
                    