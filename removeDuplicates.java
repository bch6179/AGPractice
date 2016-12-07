package notTested;

public class removeDuplicates {


 	    /**
	     * @param A: a array of integers
	     * @return : return an integer
	     */
	    public int removeDuplicates(int[] nums) {
	        // write your code here
	        int count = 1;
	        int writeIdx = 0;

	        for (int i = 1; i < nums.length; i++) {
	        	if (nums[writeIdx] == nums[i] && count <= 2) {
	                nums[++writeIdx] = nums[i];
	                count++;
	            }
	            else if (nums[writeIdx] != nums[i]) {
	                 nums[++writeIdx] = nums[i];
	                 count = 1;
	            }
	        }

	        return writeIdx + 1;
	    } // [1 1 1 ] error

	            if(nums == null)
            return 0;
        int cur = 0;
        int i ,j;
        for(i = 0; i < nums.length;){
            int now = nums[i];
            for( j = i; j < nums.length; j++){
                if(nums[j] != now)
                    break;
                if(j-i < 2)
                    nums[cur++] = now;
            }
            i = j;
        }
        return cur;

}
