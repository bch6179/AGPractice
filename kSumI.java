 k Sum
state: f[i][j][t]前i个数取j个数出来能否和为t function: f[i][j][t] = f[i - 1][j - 1][t - a[i]] or
f[i - 1][j][t]
1. 问是否可行 (DP) - f[x][0][0] = true 2. 问方案总数 (DP) - f[x][0][0] = 1
3. 问所有方案 (递归/搜索)

题目

Given n distinct positive integers, integer k (k <= n) and a number target.

Find k numbers where sum is target. Calculate how many solutions there are?

Example
Given [1,2,3,4], k=2, target=5. There are 2 solutions:

[1,4] and [2,3], return 2.

Tags Expand
LintCode Copyright Dynamic Programming

解题思路

思路一： 迭代，开始时依次选择一个点，然后根据新的target值和新的数组，选择k-1个数，思路直接简单。但是存在重复计算。该算法复杂度为 Nk ,指数递增，所以在lintcode中就计算超时了。
思路二：参考网上解法，还记得我们求解一堆数，任意个数的合为N的组合个数的题吗？当时用一个一维数组来存储可以构成的数，现在我们同样用一个长度为target的数据来存储数据，存的是前j个数中选择i个数，和的构成分布，所以数据就是dp[i][j][target]，加上下届即可。
dp[i][j][v]=dp[i][j-1][v]+dp[i-1][j-1][v-A[i]]
代码

代码一

public class Solution {
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */
    public int kSum(int A[], int k, int target) {
        if (A.length < k || k == 0)
            return 0;

        if(k == 1){
            for(int i=0;i<A.length;i++)
                if(A[i] == target)
                    return 1;
            return 0;
        }
        else  {
            int[] B = new int[A.length - 1];
            if (A.length > 1)
                System.arraycopy(A, 1, B, 0, A.length - 1);
            return kSum(B, k - 1, target - A[0]) + kSum(B, k, target);
        }
    }
}
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
12
13
14
15
16
17
18
19
20
21
22
23
24
25
代码二

public class Solution {
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */
    public int kSum(int A[], int k, int target) {
        int[][][] dp = new int[k + 1][A.length + 1][target + 1];

        for (int i = 1; i <= A.length; i++) {
            for (int j = i; j > 0; j--) {
                if (A[j - 1] <= target)
                    dp[1][i][A[j - 1]] = 1;
            }
        }

        for (int m = 2; m <= k; m++) {
            for (int n = m; n <= A.length; n++) {
                for (int l = 0; l <= target; l++) {
                    dp[m][n][l] = dp[m][n][l] + dp[m][n - 1][l];
                    if (l + A[n - 1] <= target)
                        dp[m][n][l + A[n - 1]] = dp[m][n][l + A[n - 1]] + dp[m - 1][n - 1][l];
                }
            }
        }

        return dp[k][A.length][target];
    }
}
然这道题可以用Recursion的方法，找出所有满足条件的组合，然后求结果arraylist的size，不过对于只需要求多少个可行解的这道题，找出所有满足条件的组合显得过于奢侈了

如果只需要求一个可行解个数，一个3维DP就好了



没有管优化：要注意base case的选取。

我第一次做的时候只定义了 res[0][0][0] = 1, 其实res[i][0][0] = 1, 漏了这些case，所以当时老是过不了

 1 public class Solution {
 2     /**
 3      * @param A: an integer array.
 4      * @param k: a positive integer (k <= length(A))
 5      * @param target: a integer
 6      * @return an integer
 7      */
 8     public int kSum(int A[], int k, int target) {
 9         // write your code here
10         int[][][] res = new int[A.length+1][k+1][target+1];
11         for (int i=0; i<=A.length; i++) {
12             res[i][0][0] = 1;
13         }
14         for (int i=1; i<=A.length; i++) {
15             for (int j=1; j<=k; j++) {
16                 for (int t=1; t<=target; t++) {
17                     if (j > i) res[i][j][t] = 0;
18                     else res[i][j][t] = res[i-1][j][t];
19                     if (t >= A[i-1])
20                         res[i][j][t] += res[i-1][j-1][t-A[i-1]];
21
22                 }
23             }
24         }
25         return res[A.length][k][target];
26     }
27 }
 21题递归方法
//copyright@ July && yansha
//July、yansha，updated。
#include<list>
#include<iostream>
using namespace std;

list<int>list1;
void find_factor(int sum, int n)
{
    // 递归出口
    if(n <= 0 || sum <= 0)
        return;

    // 输出找到的结果
    if(sum == n)
    {
        // 反转list
        list1.reverse();
        for(list<int>::iterator iter = list1.begin(); iter != list1.end(); iter++)
            cout << *iter << " + ";
        cout << n << endl;
        list1.reverse();
    }

    list1.push_front(n);      //典型的01背包问题
    find_factor(sum-n, n-1);   //放n，n-1个数填满sum-n
    list1.pop_front();
    find_factor(sum, n-1);     //不放n，n-1个数填满sum
}

int main()
{
    int sum, n;
    cout << "请输入你要等于多少的数值sum:" << endl;
    cin >> sum;
    cout << "请输入你要从1.....n数列中取值的n：" << endl;
    cin >> n;
    cout << "所有可能的序列，如下：" << endl;
    find_factor(sum,n);
    return 0;
}
解法二
@zhouzhenren：
这个问题属于子集和问题（也是背包问题）。本程序采用 回溯法+剪枝
X数组是解向量，t=∑(1,..,k-1)Wi*Xi, r=∑(k,..,n)Wi
若t+Wk+W(k+1)<=M,则Xk=true，递归左儿子(X1,X2,..,X(k-1),1)；否则剪枝；
若t+r-Wk>=M && t+W(k+1)<=M,则置Xk=0，递归右儿子(X1,X2,..,X(k-1),0)；否则剪枝；
本题中W数组就是(1,2,..,n),所以直接用k代替WK值。

代码编写如下：

//copyright@ 2011 zhouzhenren

//输入两个整数 n 和 m，从数列1，2，3.......n 中 随意取几个数,
//使其和等于 m ,要求将其中所有的可能组合列出来。

#include <stdio.h>
#include <stdlib.h>
#include <memory.h>

/**
 * 输入t， r， 尝试Wk
 */
void sumofsub(int t, int k ,int r, int& M, bool& flag, bool* X)
{
    X[k] = true;   // 选第k个数
    if (t + k == M) // 若找到一个和为M，则设置解向量的标志位，输出解
    {
        flag = true;
        for (int i = 1; i <= k; ++i)
        {
            if (X[i] == 1)
            {
                printf("%d ", i);
            }
        }
        printf("/n");
    }
    else
    {   // 若第k+1个数满足条件，则递归左子树
        if (t + k + (k+1) <= M)
        {
            sumofsub(t + k, k + 1, r - k, M, flag, X);
        }
        // 若不选第k个数，选第k+1个数满足条件，则递归右子树
        if ((t + r - k >= M) && (t + (k+1) <= M))
        {
            X[k] = false;
            sumofsub(t, k + 1, r - k, M, flag, X);
        }
    }
}

void search(int& N, int& M)
{
    // 初始化解空间
    bool* X = (bool*)malloc(sizeof(bool) * (N+1));
    memset(X, false, sizeof(bool) * (N+1));
    int sum = (N + 1) * N * 0.5f;
    if (1 > M || sum < M) // 预先排除无解情况
    {
        printf("not found/n");
        return;
    }
    bool f = false;
    sumofsub(0, 1, sum, M, f, X);
    if (!f)
    {
        printf("not found/n");
    }
    free(X);
}

int main()
{
    int N, M;
    printf("请输入整数N和M/n");
    scanf("%d%d", &N, &M);
    search(N, M);
    return 0;
}
扩展：

1、从一列数中筛除尽可能少的数使得从左往右看，这些数是从小到大再从大到小的（网易）。

2、有两个序列a,b，大小都为n,序列元素的值任意整数，无序；
要求：通过交换a,b中的元素，使[序列a元素的和]与[序列b元素的和]之间的差最小。
例如:
var a=[100,99,98,1,2, 3];
var b=[1, 2, 3, 4,5,40];（微软100题第32题）。

    @well：[fairywell]:
给出扩展问题 1 的一个解法：
1、从一列数中筛除尽可能少的数使得从左往右看，这些数是从小到大再从大到小的（网易）。
双端 LIS 问题，用 DP 的思想可解，目标规划函数 max{ b[i] + c[i] - 1 }, 其中 b[i] 为从左到右， 0 ~ i 个数之间满足递增的数字个数； c[i] 为从右到左， n-1 ~ i 个数之间满足递增的数字个数。最后结果为 n - max + 1。其中 DP 的时候，可以维护一个 inc[] 数组表示递增数字序列，inc[i] 为从小到大第 i 大的数字，然后在计算 b[i] c[i] 的时候使用二分查找在 inc[] 中找出区间 inc[0] ~ inc[i-1] 中小于 a[i] 的元素个数（low）。
源代码如下：
/**
* The problem:
* 从一列数中筛除尽可能少的数使得从左往右看，这些数是从小到大再从大到小的（网易）。
* use binary search, perhaps you should compile it with -std=c99
* fairywell 2011
*/
#include <stdio.h>

#define MAX_NUM    (1U<<31)

int
main()
{
    int i, n, low, high, mid, max;

    printf("Input how many numbers there are: ");
    scanf("%d/n", &n);
    /* a[] holds the numbers, b[i] holds the number of increasing numbers
    * from a[0] to a[i], c[i] holds the number of increasing numbers
    * from a[n-1] to a[i]
    * inc[] holds the increasing numbers
    * VLA needs c99 features, compile with -stc=c99
    */
    double a[n], b[n], c[n], inc[n];

    printf("Please input the numbers:/n");
    for (i = 0; i < n; ++i) scanf("%lf", &a[i]);

    // update array b from left to right
    for (i = 0; i < n; ++i) inc[i] = (unsigned) MAX_NUM;
    //b[0] = 0;
    for (i = 0; i < n; ++i) {
        low = 0; high = i;
        while (low < high) {
            mid = low + (high-low)*0.5;
            if (inc[mid] < a[i]) low = mid + 1;
            else high = mid;
        }
        b[i] = low + 1;
        inc[low] = a[i];
    }

    // update array c from right to left
    for (i = 0; i < n; ++i) inc[i] = (unsigned) MAX_NUM;
    //c[0] = 0;
    for (i = n-1; i >= 0; --i) {
        low = 0; high = i;
        while (low < high) {
            mid = low + (high-low)*0.5;
            if (inc[mid] < a[i]) low = mid + 1;
            else high = mid;
        }
        c[i] = low + 1;
        inc[low] = a[i];
    }

    max = 0;
    for (i = 0; i < n; ++i )
        if (b[i]+c[i] > max) max = b[i] + c[i];
        printf("%d number(s) should be erased at least./n", n+1-max);
        return 0;
}

@yansha：fairywell的程序很赞，时间复杂度O(nlogn)，这也是我能想到的时间复杂度最优值了。不知能不能达到O(n)。

扩展题第2题

当前数组a和数组b的和之差为
    A = sum(a) - sum(b)

a的第i个元素和b的第j个元素交换后，a和b的和之差为
    A' = sum(a) - a[i] + b[j] - （sum(b) - b[j] + a[i])
           = sum(a) - sum(b) - 2 (a[i] - b[j])
           = A - 2 (a[i] - b[j])

设x = a[i] - b[j]，得
    |A| - |A'| = |A| - |A-2x|

    假设A > 0,

    当x 在 (0,A)之间时，做这样的交换才能使得交换后的a和b的和之差变小，x越接近A/2效果越好,
    如果找不到在(0,A)之间的x，则当前的a和b就是答案。

所以算法大概如下：
    在a和b中寻找使得x在(0,A)之间并且最接近A/2的i和j，交换相应的i和j元素，重新计算A后，重复前面的步骤直至找不到(0,A)之间的x为止。

接上，@yuan：
a[i]-b[j]要接近A/2，则可以这样想，
我们可以对于a数组的任意一个a[k],在数组b中找出与a[k]-C最接近的数（C就是常数，也就是0.5*A）
这个数要么就是a[k]-C，要么就是比他稍大，要么比他稍小，所以可以要二分查找。

查找最后一个小于等于a[k]-C的数和第一个大于等于a[k]-C的数，
然后看哪一个与a[k]-C更加接近，所以T(n) = nlogn。

除此之外，受本文读者xiafei1987128启示，有朋友在stacoverflow上也问过一个类似的题，:-)，见此：http://stackoverflow.com/questions/9047908/swap-the-elements-of-two-sequences-such-that-the-difference-of-the-element-sums。感兴趣的可以看看。

本章完。

