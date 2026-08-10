/*
 * 63. Unique Paths II — Medium
 * https://leetcode.com/problems/unique-paths-ii/
 *
 * You are given an m x n integer array grid. There is a robot initially located at the top-left
 * corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e.,
 * grid[m-1][n-1]). The robot can only move either down or right at any point in time.
 *
 * An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes
 * cannot include any square that is an obstacle.
 *
 * Return the number of possible unique paths that the robot can take to reach the bottom-right
 * corner.
 *
 * The testcases are generated so that the answer will be less than or equal to 2 * 10^9.
 *
 * Example 1:
 *   Input:  obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 *   Output: 2
 *   Explanation: There is one obstacle in the middle of the 3x3 grid above.
 *                There are two ways to reach the bottom-right corner:
 *                1. Right -> Right -> Down -> Down
 *                2. Down -> Down -> Right -> Right
 *
 * Example 2:
 *   Input:  obstacleGrid = [[0,1],[0,0]]
 *   Output: 1
 *
 * Constraints:
 *   m == obstacleGrid.length
 *   n == obstacleGrid[i].length
 *   1 <= m, n <= 100
 *   obstacleGrid[i][j] is 0 or 1.
 *
 * Approach: LT_62's counting recursion with one extra base case — a blocked cell returns 0, so
 *           the sum naturally erases every path through it. A blocked destination is caught up
 *           front, since the coordinate base case returns 1 without reading the grid —
 *           O(m*n) time, O(m*n) space.
 */
package arrays;

import java.util.*;

class LT_0063_Unique_Paths_II {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if(obstacleGrid[m-1][n-1]==1) return 0;

        int[][] dp = new int[m][n];
        for(int[] a : dp) Arrays.fill(a, -1);
        return function(obstacleGrid,0, 0, dp);
    }

    private int function(int[][] obstacleGrid, int row, int col, int[][] dp) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if (row == m - 1 && col == n - 1) return 1;
        if (row >= m || col >= n) return 0;
        if (dp[row][col] != -1) return dp[row][col];
        if(obstacleGrid[row][col]==1) return 0;

        return dp[row][col] = function(obstacleGrid, row, col + 1, dp) + function(obstacleGrid, row + 1, col, dp);
    }

    public static void main(String[] args) {
        LT_0063_Unique_Paths_II sol = new LT_0063_Unique_Paths_II();
        System.out.println(sol.uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}})); // expected: 2
        System.out.println(sol.uniquePathsWithObstacles(new int[][]{{0, 1}, {0, 0}})); // expected: 1
    }

}
