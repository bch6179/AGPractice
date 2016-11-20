Given an array of integers and a number k, find knon-overlapping subarrays which have the largest sum.

The number in each subarray should be contiguous.

Return the largest sum.

Note
The subarray should contain at least one number

Example
Given [-1,4,-2,3,-2,3],k=2, return 8

d[i][j]代表0->i-1元素中j个subarray的maxsum  (注意不包含元素i)
d[i][j] = max(d[i][j], d[m][j-1] + max) (m = j-1 .... i-1; max需要单独求，是从元素i-1到m的max subarray, 用求max subarray的方法，需要从后往前算）
[java] view plain copy
public int maxSubArray(ArrayList<Integer> nums, int k) {  
    // write your code  
    int n = nums.size();  
    int[][] d = new int[n+1][k+1];  
    for (int j = 1; j <= k; j++) {  
        for (int i = j; i <= n; i++) {  
            d[i][j] = Integer.MIN_VALUE;  
            int max = Integer.MIN_VALUE;  
            int localMax = 0;  
            for (int m = i-1; m >= j-1; m--) {  
                localMax = Math.max(nums.get(m), nums.get(m)+localMax);  
                max = Math.max(localMax, max);  
                d[i][j] = Math.max(d[i][j], d[m][j-1] + max);  
            }  
        }  
    }  
    return d[n][k];  
}  


Note
The subarray should contain at least one number

Analysis:

DP. d[i][j] means the maximum sum we can get by selecting j subarrays from the first i elements.

d[i][j] = max{d[p][j-1]+maxSubArray(p+1,i)}

we iterate p from i-1 to j-1, so we can record the max subarray we get at current p, this value can be used to calculate the max subarray from p-1 to i when p becomes p-1.

Solution:

复制代码
 1 public class Solution {
 2     /**
 3      * @param nums: A list of integers
 4      * @param k: An integer denote to find k non-overlapping subarrays
 5      * @return: An integer denote the sum of max k non-overlapping subarrays
 6      */
 7     public int maxSubArray(ArrayList<Integer> nums, int k) {
 8         if (nums.size()<k) return 0;
 9         int len = nums.size();
10         //d[i][j]: select j subarrays from the first i elements, the max sum we can get.
11         int[][] d = new int[len+1][k+1];
12         for (int i=0;i<=len;i++) d[i][0] = 0;        
13         
14         for (int j=1;j<=k;j++)
15             for (int i=j;i<=len;i++){
16                 d[i][j] = Integer.MIN_VALUE;
17                 //Initial value of endMax and max should be taken care very very carefully.
18                 int endMax = 0;
19                 int max = Integer.MIN_VALUE;                
20                 for (int p=i-1;p>=j-1;p--){
21                     endMax = Math.max(nums.get(p), endMax+nums.get(p));
22                     max = Math.max(endMax,max);
23                     if (d[i][j]<d[p][j-1]+max)
24                         d[i][j] = d[p][j-1]+max;                    
25                 }
26             }
27 
28         return d[len][k];
29                     
30 
31     }
32 }
复制代码
Solution 2:

Use one dimension array.

复制代码
 1 public class Solution {
 2     /**
 3      * @param nums: A list of integers
 4      * @param k: An integer denote to find k non-overlapping subarrays
 5      * @return: An integer denote the sum of max k non-overlapping subarrays
 6      */
 7     public int maxSubArray(ArrayList<Integer> nums, int k) {
 8         if (nums.size()<k) return 0;
 9         int len = nums.size();
10         //d[i][j]: select j subarrays from the first i elements, the max sum we can get.
11         int[] d = new int[len+1];
12         for (int i=0;i<=len;i++) d[i] = 0;        
13         
14         for (int j=1;j<=k;j++)
15             for (int i=len;i>=j;i--){
16                 d[i] = Integer.MIN_VALUE;
17                 int endMax = 0;
18                 int max = Integer.MIN_VALUE;                
19                 for (int p=i-1;p>=j-1;p--){
20                     endMax = Math.max(nums.get(p), endMax+nums.get(p));
21                     max = Math.max(endMax,max);
22                     if (d[i]<d[p]+max)
23                         d[i] = d[p]+max;                    
24                 }
25             }
26 
27         return d[len];
28                     
29 
30     }
31 }
复制代码