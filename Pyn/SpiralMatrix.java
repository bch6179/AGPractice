/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 25, 2014
 Problem:    Spiral Matrix
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/spiral-matrix/
 Notes:
 Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 For example,
 Given the following matrix:
 [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
 ]
 You should return [1,2,3,6,9,8,7,4,5].

 Solution: ...
 */
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (matrix.length == 0 || matrix[0].length == 0) return res;
        int n = matrix.length, m = matrix[0].length, row = 0, col = -1;
        while (true) {
            for (int i = 0; i < m; ++i) res.add(matrix[row][++col]);
            if (--n == 0) break;
            for (int i = 0; i < n; ++i) res.add(matrix[++row][col]);
            if (--m == 0) break;
            for (int i = 0; i < m; ++i) res.add(matrix[row][--col]);
            if (--n == 0) break;
            for (int i = 0; i < n; ++i) res.add(matrix[--row][col]);
            if (--m == 0) break;
        }
        return res;
    }
}
import java.util.*;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of
 * the matrix in spiral order.
 * 
 * For example,
 * Given the following matrix:
 * 
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 * 
 * Tags: Array
 */
class SpiralMatrix {
    public static void main(String[] args) {
        
    }
    
    /**
     * Remember which level it is right now
     * Do level by level till reach center
     */
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0) return res;
        
        int m = matrix.length;
        int n = matrix[0].length;
        int lv = 0;
        
        while (2 * lv < m && 2 * lv < n) { // note 2 * level
            for (int i = lv; i < n - lv; i++) res.add(matrix[lv][i]); // right
            for (int i = lv + 1; i < m - lv; i++) res.add(matrix[i][n-lv-1]); // down
            if (2 * lv == m - 1 || 2 * lv == n - 1) break; // reach last row/col
            for (int i = n - lv - 2; i >= lv; i--) res.add(matrix[m-lv-1][i]);
            for (int i = m - lv - 2; i >= lv+1; i--) res.add(matrix[i][lv]);
            lv++;
        }
        return res;
    }
    
    /**
     * Use rMin, rMax, cMin, cMax, to store the boundries
     * Use i, j to track the position
     * Move i, j around to add elements
     * Break whenever out of bounds to avoid duplicate traversal
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int iMin = 0;
        int iMax = matrix.length - 1;
        int jMin = 0;
        int jMax = matrix[0].length - 1;
        int i = 0;
        int j = 0;
        // update boundry as soon as we traverse through it
        while (iMin <= iMax && jMin <= jMax) {
            for (j = jMin; j <= jMax; j++) res.add(matrix[iMin][j]);
            iMin++; if(iMin > iMax) break; // break as soon as possible
            for (i = iMin; i <= iMax; i++) res.add(matrix[i][jMax]);
            jMax--; if(jMax < jMin) break;
            for (j = jMax; j >= jMin; j--) res.add(matrix[iMax][j]);
            iMax--; if(iMax < iMin) break;
            for (i = iMax; i >= iMin; i--) res.add(matrix[i][jMin]);
            jMin++;
        }
        return res;
    }
}
  public static void printMatrixInSpiralOrder(int[][] A) {
    int[][] shift = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int dir = 0, x = 0, y = 0;

    for (int i = 0; i < A.length * A.length; ++i) {
      System.out.print(A[x][y] + " ");
      A[x][y] = 0;
      int nextX = x + shift[dir][0], nextY = y + shift[dir][1];
      if (nextX < 0 || nextX >= A.length || nextY < 0 || nextY >= A.length
          || A[nextX][nextY] == 0) {
        dir = (dir + 1) & 3;
        nextX = x + shift[dir][0];
        nextY = y + shift[dir][1];
      }
      x = nextX;
      y = nextY;
    }
  }

  虑2个初始条件，如果矩阵只有一行或者一列，那么无需转圈，依次输出即可。

其他情况均需转圈：从左到右，从上到下，从右到左，从下到上。 从大圈依次循环到小圈即可。

 

代码如下：

 

复制代码
 1    public ArrayList<Integer> spiralOrder(int[][] matrix) {
 2         ArrayList<Integer> result = new ArrayList<Integer>();
 3         if(matrix == null || matrix.length == 0)
 4             return result;
 5  
 6         int m = matrix.length;
 7         int n = matrix[0].length;
 8  
 9         int x=0; 
10         int y=0;
11  
12         while(m>0 && n>0){
13  
14             //if one row/column left, no circle can be formed
15             if(m==1){
16                 for(int i=0; i<n; i++){
17                     result.add(matrix[x][y++]);
18                 }
19                 break;
20             }else if(n==1){
21                 for(int i=0; i<m; i++){
22                     result.add(matrix[x++][y]);
23                 }
24                 break;
25             }
26  
27             //below, process a circle
28  
29             //top - move right
30             for(int i=0;i<n-1;i++)
31                 result.add(matrix[x][y++]);
32  
33             //right - move down
34             for(int i=0;i<m-1;i++)
35                 result.add(matrix[x++][y]);
36  
37             //bottom - move left
38             for(int i=0;i<n-1;i++)
39                 result.add(matrix[x][y--]);
40  
41             //left - move up
42             for(int i=0;i<m-1;i++)
43                 result.add(matrix[x--][y]);
44  
45             x++;
46             y++;
47             m=m-2;
48             n=n-2;
49         }
50  
51         return result;
52     }