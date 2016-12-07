public class Solution {
    /*
     * @param nums: The rotated sorted array
     * @return: void
     */
    public void recoverRotatedSortedArray(ArrayList<Integer> nums) {
                int p = 0;

        // write your code
         p= searchMin(nums);
        if (p == 0) return;
        
        reverse(nums, 0, p-1);
        reverse(nums, p, nums.size()-1);
           reverse(nums, 0, nums.size()-1);

    }

    
    void reverse(ArrayList<Integer> nums, int start, int end) {
       // if (end >= nums.size() || start < 0) return;
        
        while(start < end) {
            int temp = nums.get(end);
            nums.set(end,  nums.get(start));
            nums.set(start, temp);
            
            end--;
            start++;
        }
        
        
    }
    
    int searchMin(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) return -1;
        
        
        int min = 0;
        int start = 0;
        int end = nums.size() - 1;
        
        int i = 0;
        while(i < end) {
           if (nums.get(i) > nums.get(i+1)) {
               break;
           }
           i++;
        }
        
        // while(start + 1 < end) {
        //     int mid = start + (end-start)/2;
        //     if (nums.get(mid) > nums.get(end)) {
        //         start = mid;
        //     }
        //     else (nums.get(mid) < nums.get(end))  {
        //         end = mid;
        //     }
        //     else  {
        //         //not fit for equal
        //     }
            
        // }
        
        // if (nums.get(start) < nums.get(end)) {
        //     return start;
        // }
        // else return end;
        return i+1;
    }

    /** 
     *@param A : an integer rotated sorted array
     *@param target :  an integer to be searched
     *return : an integer
     */
    public int search_rotated_sorted_array_error(int[] A, int target) {
        // write your code here
        int start = 0;
        int end = A.length-1;
        
        if (end <= 0) return -1;
        
        while(start+1 < end) {
            int mid = start + (end-start)/2;
             if (A[mid] == target) {
                return mid;
            }
            if (A[mid] < A[end]) {
                if (target >= A[end] && target <= A[mid])  
                      end = mid;
                else   start = mid;
            }
            else {
                     
                   if (target <= A[end] && target >= A[mid])  
                      start = mid;
                   else end = mid;
            }
                
            
        }
        
        if (target == A[start]) return start;
        else  if (target == A[end]) return end;
        else return -1;
    }
    public class Solution {
    /** 
     *@param A : an integer rotated sorted array
     *@param target :  an integer to be searched
     *return : an integer
     */
    public int     public int search_rotated_sorted_array_correct(int[] A, int target) {
 
        // write your code here
        int start = 0;
        int end = A.length-1;
        
        if (end < 0) return -1;
        
        while(start+1 < end) {
            int mid = start + (end-start)/2;
             if (A[mid] == target) {
                return mid;
            }
            if (A[start] < A[mid]) {
                if (A[start] <  target & target <= A[mid])  
                      end = mid;
                else   start = mid;
            }
            else {
                 if (A[mid] <= target && target <= A[end]) {
                    start = mid;
                 } else {
                    end = mid;
                  }
            }
                
            
        }
        
        if (start >= 0 && target == A[start]) return start;
        else  if (end >= 0 && target == A[end]) return end; //end >=0 
        else return -1;
    }

    public int findPeak_k(int[] A) {
        // write your code here
        if (A.length < 3) return -1;
        
        int start =0, end=A.length-1; // improve instead of length-1
        while(start+1<end  ) {
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
    public int findPeak(int[] A) {
        // write your code here
        int start = 1, end = A.length-2; // 1.答案在之间，2.不会出界 
        while(start + 1 <  end) {
            int mid = (start + end) / 2;
            if(A[mid] < A[mid - 1]) {
                end = mid;
            } else if(A[mid] < A[mid + 1]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if(A[start] < A[end]) {
            return end;
        } else { 
            return start;
        }
    }

     public int findFirstBadVersion(int n) {
        int start = 1, end = n;
        while (start + 1 < end) { //error: while(start + 1 < n) {
            int mid = (end - start) / 2 + start;
            if (VersionControl.isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (VersionControl.isBadVersion(start)) {
            return start;
        }
        return end;
          //     if  (VersionControl.isBadVersion(start)  == false) {
    //       return start; //error
    }

    public ArrayList<Integer> searchRange(ArrayList<Integer> A, int target) {
        // write your code here
        int start = 0, end = A.size()-1;
        int x = -1, y = -1;
        
         ArrayList<Integer> list = new ArrayList<Integer>();  
            list.add(x);
            list.add(y);
            
        if (end == -1) return  list;
        
        while(start+1 < end) {
            int m = start + (end - start)/2;
            
            if (A.get(m) < target) {
                start = m;
            }
            else if (A.get(m) > target) {
                end = m;
            }
            else {
                 end = m;
             }
        }
        
        if (A.get(start) == target) {
            list.set(0,start);
        }
        else if (A.get(end) == target){
            list.set(0,end);
        }
        else return list;
        
        start = 0;
        end =  A.size()-1;
        
        while(start+1 < end) {
            int m = start + (end - start)/2;
            
            if (A.get(m) < target) {
                start = m;
            }
            else if (A.get(m) > target) {
                end = m;
            }
            else {
                 start = m;
             }
        }
        if (A.get(end) == target) {
            list.set(1,end); //error     list.set(1,start); 
        }
        else if (A.get(start) == target){ //error  (A.get(end) == target
            list.set(1,start);
        }
         return list;
       
     }

 
}


// import java.util.*;

// public class C2Solution {
//     /*
//      * @param nums: The rotated sorted array
//      * @return: void
//      */
//     public void recoverRotatedSortedArray(ArrayList<Integer> nums) {
//                 int p = 0;

//         // write your code
//         reverse(nums, 0, nums.size()-1);
//          p= searchMin(nums);
//         if (p == 0) return;
        
//         reverse(nums, 0, p-1);
//         reverse(nums, p, nums.size()-1);
//     }

    
//     void reverse(ArrayList<Integer> nums, int start, int end) {
//         if (end >= nums.size() || start < 0) return;
        
//         while(start < end) {
//             int temp = nums.get(end);
//             nums.get(end) = nums.get(start);
//             nums.get(start) = temp;
            
//             end--;
//             start++;
//         }
        
        
//     }
    
//     int searchMin(ArrayList<Integer> nums) {
//         if (nums == null || nums.size() == 0) return -1;
        
        
//         int min = 0;
//         int start = 0;
//         int end = nums.size() - 1;
        
//         while(start + 1 < end) {
//             int mid = start + (end-start)/2;
//             if (nums.get(mid) > nums.get(end)) {
//                 start = mid;
//             }
//             else {
//                 end = mid;
//             }
            
//         }
        
//         if (nums.get(start) < nums.get(end)) {
//             return start;
//         }
//         else return end;
//     }
// }
 // public int findFirstBadVersion(int n) {
    //     // write your code here
    //     int start = 1; 
    //     int end = n;
        
    //     if  (VersionControl.isBadVersion(start)  == true)
    //         return start;

    //     while(start + 1 < n) {
    //         int m = start + (end - start)/2;
    //         if (VersionControl.isBadVersion(m)  == false) {
    //             start = m;
    //         }
    //         else {
    //             end = m;
    //         }
    //     }
        
    //     if  (VersionControl.isBadVersion(start)  == false) {
    //       return start; //error
    //     }
    //     else {
    //         return end;
    //     }
        
    // }
