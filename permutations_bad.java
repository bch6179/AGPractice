class Solution {
    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    // private void swap(ArrayList<Integer> nums, int x, int y) {
    //     int temp = nums.get(x);
        
    //     nums.set(x, nums.get(y));
    //     nums.set(y, temp);
    // }
    private void Helper(ArrayList<ArrayList<Integer>> result, int index, ArrayList<Integer> nums) {
        if (index == nums.size() - 1  ) {
            result.add(nums);
            return;
        }
        for (int i = index; i < nums.size(); i++ ) {
           int temp = nums.get(index);
           nums.set(index,nums.get(i));
           nums.set(i, temp);
            Helper(result, index + 1, nums);
           temp = nums.get(index);
           nums.set(index,nums.get(i));
           nums.set(i, temp);
        }
    } 
    //a
    //abc
    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> nums) {
        // write your code here 
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        
        if (nums == null || nums.size() == 0) {
            return result;
        }
        
        
        Helper(result, 0, nums);
        
        return result;
    }
}