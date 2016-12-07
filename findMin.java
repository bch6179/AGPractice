public class Solution {
    /**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] A) {
        // write your code here
        int l = 0;
        int r = A.length - 1;
        while(l + 1 < r) {
            if (A[l] == A[l+1]) {
                l++;
                continue;
            }
            if (A[r] == A[r-1]) {
                r--;
                continue;
            }

            int m = l + (r-l)/2;
            if (A[m] > A[r]) {
                l = m;
            }
            else r = m;
        }

        return (A[l] > A[r]?A[r]:A[l]);
    }
}
