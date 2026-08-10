/*
 * 1463. Cherry Pickup II — Hard
 * https://leetcode.com/problems/cherry-pickup-ii/
 *
 * You are given a rows x cols matrix grid representing a field of cherries where grid[i][j]
 * represents the number of cherries that you can collect from the (i, j) cell.
 *
 * You have two robots that can collect cherries for you:
 *   Robot #1 is located at the top-left corner (0, 0), and
 *   Robot #2 is located at the top-right corner (0, cols - 1).
 *
 * Return the maximum number of cherries collected using both robots by following the rules below:
 *   From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
 *   When any robot passes through a cell, it picks up all cherries, and the cell becomes empty.
 *   When both robots stay in the same cell, only one takes the cherries.
 *   Both robots cannot move outside of the grid at any moment.
 *   Both robots should reach the bottom row in grid.
 *
 * Example 1:
 *   Input:  grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
 *   Output: 24
 *   Explanation: Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
 *                Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12. Total: 24.
 *
 * Example 2:
 *   Input:  grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
 *   Output: 28
 *   Explanation: Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
 *                Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11. Total: 28.
 *
 * Constraints:
 *   rows == grid.length
 *   cols == grid[i].length
 *   2 <= rows, cols <= 70
 *   0 <= grid[i][j] <= 100
 *
 * Approach: 3D memoized simultaneous DFS. Both robots descend exactly one row per step, so the
 *           state collapses from (r1,c1,r2,c2) to (row, c1, c2); each state branches over the
 *           3 x 3 = 9 joint moves and collects grid[row][c1] (+ grid[row][c2] when the columns
 *           differ) — O(m*n^2) time, O(m*n^2) space.
 */
package arrays;

import java.util.*;

class LT_1463_Cherry_Pickup_II {

    private static final int[] DIR = {-1, 0, 1};

    public int cherryPickup(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        Integer[][][] memo = new Integer[m][n][n];

        return dfs(grid, 0, 0, n - 1, memo);
    }

    private int dfs(int[][] grid, int row, int c1, int c2,
                    Integer[][][] memo) {

        int m = grid.length;
        int n = grid[0].length;

        if (c1 < 0 || c1 >= n || c2 < 0 || c2 >= n)
            return Integer.MIN_VALUE;

        if (row == m - 1) {
            if (c1 == c2) return grid[row][c1];
            return grid[row][c1] + grid[row][c2];
        }

        if (memo[row][c1][c2] != null) return memo[row][c1][c2];

        int current;

        if (c1 == c2)
            current = grid[row][c1];
        else
            current = grid[row][c1] + grid[row][c2];

        int best = Integer.MIN_VALUE;

        // 3 × 3 = 9 possible moves
        for (int d1 : DIR) {
            for (int d2 : DIR) {
                best = Math.max(best,dfs(grid, row + 1, c1 + d1, c2 + d2, memo) );
            }
        }

        return memo[row][c1][c2] = current + best;
    }

    public static void main(String[] args) {
        LT_1463_Cherry_Pickup_II sol = new LT_1463_Cherry_Pickup_II();
        System.out.println(sol.cherryPickup(new int[][]{
                {3, 1, 1}, {2, 5, 1}, {1, 5, 5}, {2, 1, 1}})); // expected: 24
        System.out.println(sol.cherryPickup(new int[][]{
                {1, 0, 0, 0, 0, 0, 1},
                {2, 0, 0, 0, 0, 3, 0},
                {2, 0, 9, 0, 0, 0, 0},
                {0, 3, 0, 5, 4, 0, 0},
                {1, 0, 2, 3, 0, 0, 6}})); // expected: 28
    }

}
