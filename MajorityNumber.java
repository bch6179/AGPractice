say a board has 7 members. A majority would be 4 (more than half of 7).
  
/**
 * Given an array of size n, find the majority element. The majority element is
 * the element that appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always
 * exist in the array.
 *
 * Tags:  Array, Bit Manipulation
 */
class MajorityEle {
    public static void main(String[] args) {

    }

    
  
Majority Element 
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times. 
You may assume that the array is non-empty and the majority element always exist in the array. 
做法1 
思路很简单，先排序，然后在中间的一定是出现最多的。为什么呢？因为它出现more than n/2次，所以它前后元素一定不会超过n/2个。排序采取了快排，好久没写还有点生疏了。但是这个做法却LTE了，尴尬。。 
代码

class Solution {
public:
    int majorityElement(vector<int> &num) {
        int len = num.size();
        quickSort(num,0,len-1);
        return num[len/2];
    }
    void quickSort(vector<int> &num,int low,int high){
        if (low>=high) return;
        int i=low;int j=high;
        int pivot = num[i];
        while (i<j){
            while (i<j&&num[j]>=pivot){j--;}
            num[i]=num[j];
            while (i<j&&num[i]<=pivot){i++;}
            num[j]=num[i];
        }
        num[i]=pivot;
        quickSort(num,low,i-1);
        quickSort(num,i+1,high);

    }
};

做法2 
思路：既然最多元素出现了n/2次，那我就想用抵消的思想，用它和与它不相等的元素一一相消，剩下的一定就是最多的那个元素。根据这个想法，有了如下代码。

class Solution {
public:
    int majorityElement(vector<int> &num) {
        int result=num[0];int len = num.size();
        int count = 0;
        for (int i=0;i<len;i++){
            if (count==0||result==num[i]) {
                result = num[i];count++;}   //count清零时，取当前数作为result
            else count--;
        }
        return result;
    }
};
    /**
     * Go through the array, assign maj ele if count is 0
     * Add 1 to count if same element, otherwise minus 1
     */
    public static int majorityElement(int[] num) {
        int maj = num[0];
        for (int count = 0, i = 0; i < num.length && count <= num.length / 2; i++){
            if (count == 0){
                maj = num[i];
                count++;
            }
            else count = num[i] == maj ? count + 1 : count - 1;
        }
        return maj;
    }
 Solution: 1. Runtime: O(n) — Moore voting algorithm: We maintain a current candidate and a counter initialized to 0.
 As we iterate the array, we look at the current element x:
 If the counter is 0, we set the current candidate to x and the counter to 1.
 If the counter is not 0, we increment or decrement the counter based on whether x is the current candidate.
 After one pass, the current candidate is the majority element. Runtime complexity = O(n).
 2. Runtime: O(n) — Bit manipulation: We would need 32 iterations, each calculating the number of 1's for the ith bit of all n numbers. Since a majority must exist, therefore, either count of 1's > count of 0's or
  vice versa (but can never be equal). The majority number’s ith bit must be the one bit that has the greater count.
 */

public class Solution {
    public int majorityElement_1(int[] num) {
        int n = num.length;
        if (n == 0) return 0;
        if (n == 1) return num[0];
        int res = num[0], cnt = 1;
        for (int i = 1; i < n; ++i) {
            if (cnt == 0) {
                res = num[i];
                ++cnt;
                continue;
            }
            if (res == num[i]) ++cnt;
            else --cnt;
        }
        return res;
    }
    public int majorityElement_2(int[] num) {
        int n = num.length;
        if (n == 0) return 0;
        if (n == 1) return num[0];
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            int one = 0, zero = 0;
            for (int j = 0; j < n; ++j) {
                if (((num[j]>>i) & 1) == 1) ++one;
                else ++zero;
            }
            if (one > zero) res = res | (1<<i);
        }
        return res;
    }
    /**
     * Runtime: O(n) — Bit manipulation: We would need 32 iterations, each
     * calculating the number of 1's for the ith bit of all n numbers. Since a
     * majority must exist, therefore, either count of 1's > count of 0's or
     * vice versa (but can never be equal). The majority number’s ith bit must
     * be the one bit that has the greater count.
     */
    public static int majorityElement2(int[] num) {

    }
}