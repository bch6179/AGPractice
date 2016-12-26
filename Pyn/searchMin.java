 int (ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) return -1;


        int min = 0;
        int start = 0;
        int end = nums.size() - 1;

        // int i = 0;
        // while(i < end) {
        //   if (nums.get(i) > nums.get(i+1)) {
        //       break;
        //   }
        //   i++;
        // }

        while(start + 1 < end) {
            int mid = start + (end-start)/2;
            if (nums.get(mid) > nums.get(end)) {// would fail at 1, 1, 1, -1, 1, 1, 1, 1
                start = mid;
            }
            else    {
                end = mid;
            }

        }

        if (nums.get(start) < nums.get(end)) {
            return start;
        }
