// Valid Sudoku — https://leetcode.com/problems/valid-sudoku/
import java.util.*;

class LT_0036_Valid_Sudoku {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rowCheck = new boolean[9][9];
        boolean[][] colCheck = new boolean[9][9];
        boolean[][] boxCheck = new boolean[9][9];

        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                char ch = board[i][j];
                if(ch=='.') continue;

                int val = ch - '1';
                int boxIndex = 3 * (i/3) + j/3;

                if(rowCheck[i][val] || colCheck[val][j] || boxCheck[boxIndex][val]) {
                    return false;                                        
                }
                rowCheck[i][val]  = true;
                colCheck[val][j] = true;
                boxCheck[boxIndex][val] = true;
            }
        }

        return true;
    }
}
