// Sudoku Solver — https://leetcode.com/problems/sudoku-solver/
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
}
