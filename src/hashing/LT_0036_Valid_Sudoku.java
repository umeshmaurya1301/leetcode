// Valid Sudoku — https://leetcode.com/problems/valid-sudoku/
package hashing;

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

    public static void main(String[] args) {
        LT_0036_Valid_Sudoku sol = new LT_0036_Valid_Sudoku();

        char[][] board1 = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(Arrays.deepToString(board1));
        System.out.println(sol.isValidSudoku(board1)); // expected: true

        char[][] board2 = {
            {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(Arrays.deepToString(board2));
        System.out.println(sol.isValidSudoku(board2)); // expected: false
    }
}
