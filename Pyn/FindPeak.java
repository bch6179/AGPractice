class Solution {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        // write your code here
        if (A.length < 3) return -1;
        
        int start =0, end=A.length-2;
        while( start+1<end  ) {
            int m = start + (end-start)/2;
            
            // if (A[m] <= A[start]) {
            //     end = m;
            // }
            // else {
                if (A[m] > A[m+1]) {
                    if (A[m] > A[m-1]) {
                        return m;
                    }
                    else end = m;
                }
                else start = m;
                
            //}
            
        }//for
        return end;
    }
}