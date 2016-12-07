    public int countOnes(int num) {
        // write your code here

        int count = 0;
        if (num == 0) return count;

        int mask = 1;

        // while(num >= mask) {
        //     if ((num & mask) > 0) { //  > has higher priority than &
        //         count++;
        //     }
        //     mask <<=  1;
        //     if (mask > Integer.MAX_VALUE) break;
        // }
        for (int i = 0; i < 32; i++) {
            if ((num & (1<<i)) != 0) count++;
        }
        return count;
    }
};