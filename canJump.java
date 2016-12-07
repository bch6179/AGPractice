   public boolean canJump_myBad(int[] nums) { //furtherest index got lost
           int t = 0;

        for (int i = 0; i < nums.length && t >= i && t < nums.length - 1; i++) {
            t = i + nums[i];
        }

        return t >= nums.length - 1;
    }