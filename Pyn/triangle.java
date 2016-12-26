public class Solution {
    /**
     * @param triangle: a list of lists of integers.
     * @return: An integer, minimum path sum.
     */
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        // write your code here
                int n = triangle.size();

        if (triangle == null || n == 0) return 0;

        int [][]  fn = new int[n][n];
        
        
        for(int i = 0; i < n ; i++) {
            fn[n -1][i] = triangle.get(n-1).get(i);
        }
        
        for(int i = n-2; i >= 0; i--) {
            for(int j = 0; j <= i; j++) {
                fn[i][j] = Math.min(fn[i+1][j] , fn[i+1][j+1]) + triangle.get(i).get(j);
            }
        }
        
        return fn[0][0];
            
    }

        public int minimumTotal(int[][] triangle) {
        // write your code here
        return helper(triangle, 0, 0);
    }
    
    int helper(int[][] triangle, int i, int j) {
        if (i >= triangle.length || j > i) return 0;
        return triangle[i][j] +Math.min(helper(triangle, i+1, j), helper(triangle, i+1, j+1) );
    }



   int minSum = Integer.MAX_VALUE;
     int[][] table; 
    public int minimumTotal(int[][] triangle) {
        // write your code 
        table = new int[triangle.length+1][triangle.length+1]; //need extra one
        for (int i = 0; i < triangle.length; i++) {
              for (int j = 0; j < triangle.length; j++) {
                  table[i][j] = Integer.MAX_VALUE;
              }
        }
        return  helper(triangle, 0, 0);
    }
    
    int helper(int[][] triangle, int i, int j) {
        if (i == triangle.length) return 0;
        
       if (table[i][j] == Integer.MAX_VALUE) {

   
          table[i+1][j] =  helper(triangle, i+1, j);
          table[i+1][j+1]= helper(triangle, i+1, j+1);
           table[i][j] = Math.min(table[i+1][j], table[i+1][j+1]) + triangle[i][j];
       }
        return table[i][j];
    }
