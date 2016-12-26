Backpack
http://www.lintcode.com/en/problem/backpack/
http://www.ninechapter.com/solutions/backpack/
￼￼￼￼￼
￼￼
￼Backpack
n个整数a[1..n],装m的背包
state: f[i][j] “前i”个数,取出一些能否组成和为j function: f[i][j] = f[i-1][j - a[i]] or f[i-1][j] intialize: f[X][0] = true; f[0][1..m] = false answer: 能够使得f[n][X]最大的X(0<=X<=m)

public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        boolean f[][] = new boolean[A.length + 1][m + 1];
        for (int i = 0; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = false;
            }
        }
        f[0][0] = true;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j <= m; j++) {
                f[i + 1][j] = f[i][j];
                if (j >= A[i] && f[i][j - A[i]]) {
                    f[i + 1][j] = true;
                }
            } // for j
        } // for i

        for (int i = m; i >= 0; i--) {
            if (f[A.length][i]) {
                return i;
            }
        }
        return 0;
    }
}\

iven n items with size A[i] and value V[i], and a backpack with size m. What's the maximum value can you put into the backpack?
Note
You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.

Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.
这道题还是跟Backpack有大不一样之处

用子问题定义状态：即f[i][v]表示前 i 件物品恰放入一个容量为 j 的背包可以获得的最大价值。则其状态转移方程便是：

f[i][j] = max{f[i-1][j], j>=A[i-1]? f[i-1][j-A[i-1]]+V[i-1] : 0}

复制代码
 1 public class Solution {
 2     /**
 3      * @param m: An integer m denotes the size of a backpack
 4      * @param A & V: Given n items with size A[i] and value V[i]
 5      * @return: The maximum value
 6      */
 7     public int backPackII(int m, int[] A, int V[]) {
 8         int[][] res = new int[A.length+1][m+1];
 9         res[0][0] = 0;
10         for (int i=1; i<=A.length; i++) {
11             for (int j=0; j<=m; j++) {
12                 if (j - A[i-1] < 0)
13                     res[i][j] = res[i-1][j];
14                 if (j - A[i-1] >= 0) {
15                     res[i][j] = Math.max(res[i-1][j], res[i-1][j-A[i-1]]+V[i-1]);
16                 }
17             }
18         }
19
20         return res[A.length][m];
21     }
22 }
复制代码