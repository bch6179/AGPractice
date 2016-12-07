public class Solution {
    /**
     * @param A: A list of integers
     * @return: The boolean ans
     * wer
     */
    public boolean canJump(int[] A) {
        // wirte your code here
        // if (A.length == 0) {
        //     return false;
        // }
        // boolean[] f = new boolean[A.length];

        // f[0] = true;

        // for (int i = 1; i < A.length; i++)
        //{
        //         if (f[j] == true && (A[j] + j >= i)) {
        //             f[i] = true;
        //         }
        //     }

        // }

        // return f[A.length - 1];

        //METHOD Greedy
        //my bad
        // if (A.size <= 0) {
        //     return false;
        // }

        // int n = A.size;

        // for (int next = 0; next < n - 1; ) {
        //     next = A[next] + next;
        // }

        // if (next >= n - 1) {
        //     return true;
        // }

        // return false;

        //
        int farthest = A[0];
        for (int loc = 1;  loc < A.length - 1; loc++  ) {
            if ((loc <= farthest) && (A[loc] + loc > farthest)) {
                farthest = A[loc] + loc;
            }
        }
        return (farthest >= A.length-1);

    }
//Jump 2
public class Solution2 {
    /**
     * @param A: A list of lists of integers
     * @return: An integer
     */
    public int jump(int[] A) {
        // write your code here

        if (A == null || A.length == 0) {
            return 0;
        }

        int[] jump = new int[A.length];

        jump[0] = 0;  //Previous errors: extra int, init value is 1

        for (int i = 1; i < A.length; i++) {
            jump[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (jump[j] != Integer.MAX_VALUE && (A[j] + j >= i)) {
                    jump[i] = Math.min(jump[j] + 1, jump[i]); //wrong here ,  jump[i] always max
                }
            }
        }
        return jump[A.length - 1];
    }
    public class Solution {
    public int jump(int[] A) {
        int[] steps = new int[A.length];

        steps[0] = 0;
        for (int i = 1; i < A.length; i++) {
            steps[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (steps[j] != Integer.MAX_VALUE && j + A[j] >= i) {
                    steps[i] = steps[j] + 1;
                    break;
                }
            }
        }

        return steps[A.length - 1];
    }
}


    public class Solution {
    public int jump(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int start = 0, end = 0, jumps = 0;
        while (end < A.length - 1) {
            jumps++;
            int farthest = end;
            for (int i = start; i <= end; i++) {
                if (A[i] + i > farthest) {
                    farthest = A[i] + i;
                }
            }
            start = end + 1;
            end = farthest;
        }
        return jumps;
    }
}
}