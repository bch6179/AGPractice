/*
Trapping Rain Water

http://leetcode.com/onlinejudge#question_42

Given n non-negative integers representing an elevation map where the
width of each bar is 1, compute how much water it is able to trap after raining.

For example,
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
In this case, 6 units of rain water (blue section) are being trapped.
*/

// use dynamic programming and only two pass
// 44 milli secs pass large set
// time complexity o(n), space complexity o(n)

en n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example,
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


Analysis

To find the trapped water at position i, we need to find the maximum value of the left elements of i and right elements of i. Assuming they are maxLeft[i] and maxRight[i]. The trapped water is min(maxLeft[i], maxRight[i]) – A[i] (if this value is larger than 0).

To find maxLeft and maxRight, we need to scan the array from left to right and from right to left. You can check the details in the code below.

Code

Java

路1：stack解法

能积水的地方必然是一个中间低两边高的凹陷。所以寻找的目标是一个递减序列之后的递增。由于积水量只有在递增时才能计算，而计算又可能需要用到递减序列中的多个bar。因此必须将递减序列cache起来。这里使用stack。为了便于面积计算中宽度的计算，stack存放的不是递减序列bar的高度，而是每个bar的坐标。


class Solution {
public:
    int trap(int A[], int n) {
        if(n<3) return 0;
        stack<int> s;
        s.push(0);
        int water = 0;

        for(int i=1; i<n; i++) {
            if(A[i]>A[s.top()]) { # Now seeing a upper after stacking lower (taller than the previous)
                int bottom = A[s.top()];
                s.pop();
                while(!s.empty() && A[i]>=A[s.top()]) {
                    water += (A[s.top()]-bottom)*(i-s.top()-1);
                    bottom = A[s.top()];
                    s.pop();
                }
                if(!s.empty()) water += (A[i]-bottom)*(i-s.top()-1); ## add the last gap if the last stacked former grater than A[I]
            }
            s.push(i);
        }

        return water;
    }
};

思路2：two pointers解法

对任意位置i，在i上的积水，由左右两边最高的bar：A[left] = max{A[j], j<i}, A[right] = max{A[j], j>i}决定。定义Hmin = min(A[left], A[right])，则积水量Si为：

Hmin <= A[i]时，Si = 0
Hmin > A[i]时，Si = Hmin - A[i]


public class Solution {
    public int trap(int[] A) {
        if (A.length == 0)
            return 0;
        int[] maxLeft = new int[A.length];
        int[] maxRight = new int[A.length];
        int max = A[0];
        for (int i = 1; i < A.length; i++) {
            maxLeft[i] = max;
            if (A[i] > max)
                max = A[i];
        }
        max = A[A.length - 1];
        for (int i = A.length - 2; i >= 0; i--) {
            maxRight[i] = max;
            if (A[i] > max)
                max = A[i];
        }
        int ret = 0;
        for (int i = 1; i < A.length - 1; i++) {
            int trap = Math.min(maxLeft[i], maxRight[i]) - A[i];
            if (trap > 0)
                ret += trap;
        }
        return ret;
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
26
27
public class Solution {
    public int trap(int[] A) {
        if (A.length == 0)
            return 0;
        int[] maxLeft = new int[A.length];
        int[] maxRight = new int[A.length];
        int max = A[0];
        for (int i = 1; i < A.length; i++) {
            maxLeft[i] = max;
            if (A[i] > max)
                max = A[i];
        }
        max = A[A.length - 1];
        for (int i = A.length - 2; i >= 0; i--) {
            maxRight[i] = max;
            if (A[i] > max)
                max = A[i];
        }
        int ret = 0;
        for (int i = 1; i < A.length - 1; i++) {
            int trap = Math.min(maxLeft[i], maxRight[i]) - A[i];
            if (trap > 0)
                ret += trap;
        }
        return ret;
    }
}
Complexity

We only scan the array 3 times. So the complexity is O(n).

class Solution {
public:
    int trap(int A[], int n) {
        if(n<3) return 0;
        vector<int> left_bound(n);

        // 1) 1->n: search height of max left bound for each bar
        left_bound[0] = A[0];
        for(int i=1; i<n; i++)
            left_bound[i] = max(left_bound[i-1], A[i-1]);

        // 2) n->1: search height of max right bound for each bar
        // 3) water[i] = min(right_bound, left_bound) - A[i]
        //    no water trap if if bound is shorter than A[i]
        int right_bound = A[n-1], water = 0;
        for(int i=n-2; i>0; i--) {
            right_bound = max(A[i], right_bound); // update right bound
            water += min(right_bound, left_bound[i]) - A[i];
        }

        return water;
    }
};