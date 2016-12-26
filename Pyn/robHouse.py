解题思路：
动态规划（Dynamic Programming）

状态转移方程：

dp[i] = max(dp[i - 1], dp[i - 2] + num[i - 1])

其中，dp[i]表示打劫到第i间房屋时累计取得的金钱最大值。

时间复杂度O(n)，空间复杂度O(n)

Python代码：
class Solution:
    # @param num, a list of integer
    # @return an integer
    def rob(self, num):
        size = len(num)
        dp = [0] * (size + 1)
        if size:
            dp[1] = num[0]
        for i in range(2, size + 1):
            dp[i] = max(dp[i - 1], dp[i - 2] + num[i - 1])
        return dp[size]
1
2
3
4
5
6
7
8
9
10
11
观察可知，上述代码的空间复杂度可以进一步化简为O(1)：

class Solution:
    # @param num, a list of integer
    # @return an integer
    def rob(self, num):
        size = len(num)
        odd, even = 0, 0
        for i in range(size):
            if i % 2:
                odd = max(odd + num[i], even)
            else:
                even = max(even + num[i], odd)
        return max(odd, even)