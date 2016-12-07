// 题目

// Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

// Solve it without division and in O(n).

// For example, given [1,2,3,4], return [24,12,8,6].

// Follow up:
// Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
// 要求

// 题目比较好理解，但是有几个关键点这里需要明确一下：

//     不能用除法。意思就是：你不能上来先把所有数乘积算出来，然后再逐个除以每个元素，这种思路是无聊、没技术含量而且不被允许的。
//     时间复杂度必须控制到O(n)。意思是：如果用O(n^2)的方法，那外层一个for循环，内层左右遍历就解决了，也是很无聊的解法。
//     空间复杂度最好是常数，但是重新分配的返回数组不算在内。

// 思路1

// 我们以一个4个元素的数组为例，nums=[a1, a2, a3, a4]。
// 想在O(n)时间复杂度完成最终的数组输出，res=[a2*a3*a4, a1*a3*a4, a1*a2*a4, a2*a3*a4]。

// 比较好的解决方法是构造两个数组相乘：

//     [1, a1, a1*a2, a1*a2*a3]
//     [a2*a3*a4, a3*a4, a4, 1]

// 这样思路是不是清楚了很多，而且这两个数组我们是比较好构造的。

AC代码如下：
 //my own
    public int[] productExceptSelf(int[] nums) {
        int[] p = new int[nums.length];
        p[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >=0; i--) {
            p[i] = p[i + 1] * nums[i + 1];
        }
        int k = 1;
         for (int i = 1; i < nums.length;i ++) {
            k = k * nums[i - 1];
            p[i] *=  k;
        }
        return p;
    }

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] pSeq = new int[nums.length];
        int[] nSeq = new int[nums.length];

        pSeq[0] = 1;
        for (int i = 1; i < len; i ++) {
            pSeq[i] = pSeq[i - 1] * nums[i - 1];
        }

        nSeq[len - 1] = 1;
        for (int i = len - 2; i >= 0; i --) {
            nSeq[i] = nSeq[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < len; i ++) {
            pSeq[i] *= nSeq[i];
        }

        return pSeq;
    }


但是，上面的空间复杂度为O(N)，不满足常数时间复杂度。我们可以对上面的代码进行空间优化，用一个常数p来保存每次计算的结果值。

优化AC代码：

        int len = nums.length, p;
        int[] arr = new int[nums.length];

        arr[0] = p = 1;
        for (int i = 1; i < len; i ++) {
            p = p * nums[i - 1];
            arr[i] = p;
        }

        p = 1;
        for (int i = len - 2; i >= 0; i --) {
            p = p * nums[i + 1];
            arr[i] *= p;
        }

        return arr;


思路2

本以为这样就已经很不错了，但是在discuss讨论区发现了一个特别牛逼的递归解法，非常精妙，这里分享给大家。

    public int[] productExceptSelfRev(int[] nums) {
        multiply(nums, 1, 0, nums.length);

        return c;
    }

    private int multiply(int[] a, int fwdProduct, int indx, int N) {
        int revProduct = 1;
        if (indx < N) {
            revProduct = multiply(a, fwdProduct * a[indx], indx + 1, N);
            int cur = a[indx];
            a[indx] = fwdProduct * revProduct;
            revProduct *= cur;
        }
        return revProduct;
    }