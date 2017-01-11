package com.epi;

public class SudokuCheck {
  // @include
  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(int[][] A) {
    // Check row constraints.
    for (int i = 0; i < A.length; ++i) {
      if (hasDuplicate(A, i, i + 1, 0, A.length, A.length)) {
        return false;
      }
    }

    // Check column constraints.
    for (int j = 0; j < A.length; ++j) {
      if (hasDuplicate(A, 0, A.length, j, j + 1, A.length)) {
        return false;
      }
    }

    // Check region constraints.
    int regionSize = (int) Math.sqrt(A.length);
    for (int I = 0; I < regionSize; ++I) {
      for (int J = 0; J < regionSize; ++J) {
        if (hasDuplicate(A, regionSize * I, regionSize * (I + 1),
                         regionSize * J, regionSize * (J + 1), A.length)) {
          return false;
        }
      }
    }
    return true;
  }

  // Return true if subarray A[startRow : endRow - 1][startCol : endCol - 1]
  // contains any duplicates in [1 : numElements]; otherwise return false.
  private static boolean hasDuplicate(int[][] A, int startRow, int endRow,
                                      int startCol, int endCol,
                                      int numElements) {
    boolean[] isPresent = new boolean[numElements + 1];
    for (int i = startRow; i < endRow; ++i) {
      for (int j = startCol; j < endCol; ++j) {
        if (A[i][j] != 0 && isPresent[A[i][j]]) {
          return true;
        }
        isPresent[A[i][j]] = true;
      }
    }
    return false;
  }
  // @exclude
}
Valid Sudoku
// use bit mapping, be careful about operator precedence
// 56 milli secs pass Judge Large

class Solution {
  public:
  bool isValidSudoku(vector<vector<char> > &board) {
    // use bit map to mark the apperance of numbers
    vector<int> row(9, 0);
    vector<int> col(9, 0);
    vector<vector<int> > box(3, vector<int>(3, 0));

    for(int i=0; i<9; ++i)
      for(int j=0; j<9; ++j)
        if(board[i][j] != '.') {
          int digit = board[i][j] - '1'; // convert to 0 ~ 8
          // check if there is duplicate choice
          if((row[i] & (1<<digit)) != 0) return false;
          if((col[j] & (1<<digit)) != 0) return false;
          if((box[i/3][j/3] & (1<<digit)) != 0) return false;
          // mark the bit for the appeared number
          row[i] |= (1<<digit);
          col[j] |= (1<<digit);
          box[i/3][j/3] |= (1<<digit);
        }
    return true; // return true if no conflict found
  }
};