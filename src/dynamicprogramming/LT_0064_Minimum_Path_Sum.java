/*
 * 64. Minimum Path Sum — Medium
 * https://leetcode.com/problems/minimum-path-sum/
 *
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom
 * right, which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Example 1:
 *   Input:  grid = [[1,3,1],[1,5,1],[4,2,1]]
 *   Output: 7
 *   Explanation: Because the path 1 -> 3 -> 1 -> 1 -> 1 minimizes the sum.
 *
 * Example 2:
 *   Input:  grid = [[1,2,3],[4,5,6]]
 *   Output: 12
 *
 * Constraints:
 *   m == grid.length
 *   n == grid[i].length
 *   1 <= m, n <= 200
 *   0 <= grid[i][j] <= 200
 *
 * Approach: The LT_62 grid recursion with the accumulator swapped from count to min —
 *           cost(r,c) = grid[r][c] + min(right, down), with off-grid returning Integer.MAX_VALUE
 *           so Math.min rejects illegal moves — O(m*n) time, O(m*n) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0064_Minimum_Path_Sum {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] dp = new int[n][m];
        for (int[] a : dp) Arrays.fill(a, -1);
        dfs(grid, dp, 0, 0);
        // System.out.println(Arrays.deepToString(dp));
        // for (int[] a : dp) {
        //     System.out.println(Arrays.toString(a));
        // }
        return dp[0][0];
    }

    private int dfs (int[][] grid, int[][] dp, int row, int col) {
        int n = grid.length;
        int m = grid[0].length;

        if (row>=n || col>=m) return Integer.MAX_VALUE;
        if (row==n-1 && col==m-1) return dp[row][col] = grid[row][col];
        if (dp[row][col]!=-1) return dp[row][col];

        int val = grid[row][col];
        int right =  dfs (grid, dp, row, col+1);
        int down =  dfs (grid, dp, row+1, col);
        return dp[row][col] = Math.min (right, down) + val;
    }

    public static void main(String[] args) {
        LT_0064_Minimum_Path_Sum sol = new LT_0064_Minimum_Path_Sum();
        System.out.println(sol.minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}})); // expected: 7
        System.out.println(sol.minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}})); // expected: 12
    }

}
