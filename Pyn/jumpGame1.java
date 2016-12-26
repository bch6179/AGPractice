public class jumpGame1 {
    public boolean canJump(int[] A) {
        // wirte your code here
        int n = A.length;
        boolean[] f = new boolean[n];
        f[0] = true;

        for (int i = 1; i < n; i++) {//index
            //[0,8,2,0,9]
            //2 3 1 1 4 1
            for (int j = 0; j < i; j++) {
                if (f[j] && A[j] + j >= i) {
                    f[i] = true;
                    break;
                }
            }

        }
        return f[n - 1];
    }

    public boolean canJump(int[] A) {
        // wirte your code here
        int n = A.length;
        int[] f = new int[n];
        f[0] = A[0];

        for (int j = 1; j < n; j++) {//index
            //[0,8,2,0,9]
            //2 3 1 1 4 1
            if (j <= f[j - 1]) {
                f[j] = Math.max(f[j - 1], A[j] + j);

            }

        }
        return f[n - 1] >= n - 1;
    }
}