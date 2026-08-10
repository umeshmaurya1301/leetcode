/*
 * 62. Unique Paths — Medium
 * https://leetcode.com/problems/unique-paths/
 *
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner
 * (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m-1][n-1]).
 * The robot can only move either down or right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths that the robot can
 * take to reach the bottom-right corner.
 *
 * The test cases are generated so that the answer will be less than or equal to 2 * 10^9.
 *
 * Example 1:
 *   Input:  m = 3, n = 7
 *   Output: 28
 *
 * Example 2:
 *   Input:  m = 3, n = 2
 *   Output: 3
 *   Explanation: From the top-left corner, there are a total of 3 ways to reach the
 *                bottom-right corner:
 *                1. Right -> Down -> Down
 *                2. Down -> Down -> Right
 *                3. Down -> Right -> Down
 *
 * Constraints:
 *   1 <= m, n <= 100
 *
 * Approach 1: Top-down memoization — paths(r,c) = paths(r,c+1) + paths(r+1,c), with the
 *             destination returning 1 and off-grid returning 0 — O(m*n) time, O(m*n) space.
 */
package arrays;

import java.util.*;

class LT_0062_Unique_Paths_1_TopDownMemoization {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int[] a : dp) Arrays.fill(a, -1);
        return function(m,n,0,0,dp);
    }

    private int function(int m, int n, int row, int col, int[][] dp) {
        if(row==m-1 && col==n-1) return 1;
        if(row>=m || col>=n) return 0;
        if(dp[row][col]!=-1) return dp[row][col];
        return dp[row][col] = function(m,n,row,col+1,dp) + function(m,n,row+1,col,dp);
    }

    public static void main(String[] args) {
        LT_0062_Unique_Paths_1_TopDownMemoization sol = new LT_0062_Unique_Paths_1_TopDownMemoization();
        System.out.println(sol.uniquePaths(3, 7)); // expected: 28
        System.out.println(sol.uniquePaths(3, 2)); // expected: 3
    }

}
