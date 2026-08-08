// Sudoku Solver — https://leetcode.com/problems/sudoku-solver/
package arrays;

import java.util.*;

class LT_0037_Sudoku_Solver {
    public void solveSudoku(char[][] board) {
        solver(board);
    }

    private boolean solver(char[][] board) {
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                char ch = board[i][j];
                if(ch=='.') {
                    
                    for(char k='1'; k<='9'; k++) {
                        if(isValid(board, i, j, k)) {
                            board[i][j] = k;
                            
                            if(solver(board)) {
                                return true;
                            }
                            board[i][j] = '.';
                        }    
                    }
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char val) {
        for(int k=0; k<9; k++) {
            if(board[k][col]==val) return false;
            if(board[row][k]==val) return false;

            int boxIdxRow = 3 * (row/3) + k/3;
            int boxColIdx = 3 * (col/3) + k%3;
            if(board[boxIdxRow][boxColIdx]==val) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        LT_0037_Sudoku_Solver sol = new LT_0037_Sudoku_Solver();

        char[][] board = {
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
        System.out.println("before: " + Arrays.deepToString(board));
        sol.solveSudoku(board);
        System.out.println("after:  " + Arrays.deepToString(board));
        // expected: [[5,3,4,6,7,8,9,1,2], [6,7,2,1,9,5,3,4,8], [1,9,8,3,4,2,5,6,7],
        //            [8,5,9,7,6,1,4,2,3], [4,2,6,8,5,3,7,9,1], [7,1,3,9,2,4,8,5,6],
        //            [9,6,1,5,3,7,2,8,4], [2,8,7,4,1,9,6,3,5], [3,4,5,2,8,6,1,7,9]]
    }
}
