// N-Queens — https://leetcode.com/problems/n-queens/
import java.util.*;

class LT_0051_N_Queens {

    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {

        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n];
        boolean[] diag2 = new boolean[2 * n];

        backtrack(0, board, cols, diag1, diag2, n);

        return result;
    }

    private void backtrack(int row, char[][] board,
                           boolean[] cols,
                           boolean[] diag1,
                           boolean[] diag2,
                           int n) {

        if (row == n) {
            List<String> temp = new ArrayList<>();
            for (char[] r : board) {
                temp.add(new String(r));
            }
            result.add(temp);
            return;
        }

        for (int col = 0; col < n; col++) {

            int d1 = row + col;
            int d2 = row - col + n;

            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue;
            }

            // Place queen
            board[row][col] = 'Q';
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            backtrack(row + 1, board, cols, diag1, diag2, n);

            // Backtrack
            board[row][col] = '.';
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }

    public static void main(String[] args) {
        LT_0051_N_Queens sol = new LT_0051_N_Queens();
        System.out.println(sol.solveNQueens(4));
        // expected: [[.Q.., ...Q, Q..., ..Q.], [..Q., Q..., ...Q, .Q..]]
    }
}
