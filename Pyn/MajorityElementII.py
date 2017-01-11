[LeetCode]Majority Element II
作者是 在线疯狂 发布于 2015年6月29日 在 LeetCode.
题目描述：
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.

Hint:

How many majority elements could it possibly have?

题目大意：
给定一个大小为n的整数数组，从中找出所有出现次数超过 ⌊ n/3 ⌋ 的元素。算法应该满足线性时间复杂度和O(1)空间复杂度。

提示：

一共可能有多少个“众数”？

解题思路：
可以从Moore投票算法中得到一些启发

参考LeetCode Discuss（https://leetcode.com/discuss/43248/boyer-moore-majority-vote-algorithm-and-my-elaboration）

观察可知，数组中至多可能会有2个出现次数超过 ⌊ n/3 ⌋ 的众数

记变量n1, n2为候选众数； c1, c2为它们对应的出现次数

遍历数组，记当前数字为num

若num与n1或n2相同，则将其对应的出现次数加1

否则，若c1或c2为0，则将其置为1，对应的候选众数置为num

否则，将c1与c2分别减1

最后，再统计一次候选众数在数组中出现的次数，若满足要求，则返回之。

Python代码：
class Solution:
    # @param {integer[]} nums
    # @return {integer[]}
    def majorityElement(self, nums):
        n1 = n2 = None
        c1 = c2 = 0
        for num in nums:
            if n1 == num:
                c1 += 1
            elif n2 == num:
                c2 += 1
            elif c1 == 0:
                n1, c1 = num, 1
            elif c2 == 0:
                n2, c2 = num, 1
            else:
                c1, c2 = c1 - 1, c2 - 1
        size = len(nums)
        return [n for n in (n1, n2)
                   if n is not None and nums.count(n) > size / 3]
 