/*
 * 36. Valid Sudoku — Medium
 * https://leetcode.com/problems/valid-sudoku/
 *
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * 1. Each row must contain the digits 1-9 without repetition.
 * 2. Each column must contain the digits 1-9 without repetition.
 * 3. Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * Note:
 * - A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * - Only the filled cells need to be validated according to the mentioned rules.
 *
 * Example 1:
 *   Input:
 *   board =
 *   [["5","3",".",".","7",".",".",".","."]
 *   ,["6",".",".","1","9","5",".",".","."]
 *   ,[".","9","8",".",".",".",".","6","."]
 *   ,["8",".",".",".","6",".",".",".","3"]
 *   ,["4",".",".","8",".","3",".",".","1"]
 *   ,["7",".",".",".","2",".",".",".","6"]
 *   ,[".","6",".",".",".",".","2","8","."]
 *   ,[".",".",".","4","1","9",".",".","5"]
 *   ,[".",".",".",".","8",".",".","7","9"]]
 *   Output: true
 *
 * Example 2:
 *   Input:
 *   board =
 *   [["8","3",".",".","7",".",".",".","."]
 *   ,["6",".",".","1","9","5",".",".","."]
 *   ,[".","9","8",".",".",".",".","6","."]
 *   ,["8",".",".",".","6",".",".",".","3"]
 *   ,["4",".",".","8",".","3",".",".","1"]
 *   ,["7",".",".",".","2",".",".",".","6"]
 *   ,[".","6",".",".",".",".","2","8","."]
 *   ,[".",".",".","4","1","9",".",".","5"]
 *   ,[".",".",".",".","8",".",".","7","9"]]
 *   Output: false
 *   Explanation: Same as Example 1, except with the 5 in the top-left corner modified to 8. Since there are two 8's in the top-left 3x3 sub-box, it is invalid.
 *
 * Constraints:
 *   board.length == 9
 *   board[i].length == 9
 *   board[i][j] is a digit 1-9 or '.'
 *   Only the filled cells need to be validated
 *
 * Approach: Single pass marking row / column / box seen-grids — O(81) = O(1) time, O(1) space.
 */
package arrays;

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
