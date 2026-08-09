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
 * Approach 2: Bottom-up tabulation — seed the last row and last column with 1 (only one straight
 *             line out), then fill up-left so both dependencies are already final —
 *             O(m*n) time, O(m*n) space, no recursion stack.
 */
package dynamicprogramming;

import java.util.*;

class LT_0062_Unique_Paths_2_BottomUpTabulation {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // Step 1: Base Case - fill last row and last column with 1
        for (int i = 0; i < m; i++) dp[i][n - 1] = 1;  // last column
        for (int j = 0; j < n; j++) dp[m - 1][j] = 1;  // last row

        // Step 2: Fill from bottom-right to top-left
        for (int row = m - 2; row >= 0; row--) {
            for (int col = n - 2; col >= 0; col--) {
                dp[row][col] = dp[row + 1][col] + dp[row][col + 1];
            }
        }

        return dp[0][0]; // Start from top-left
    }

    public static void main(String[] args) {
        LT_0062_Unique_Paths_2_BottomUpTabulation sol = new LT_0062_Unique_Paths_2_BottomUpTabulation();
        System.out.println(sol.uniquePaths(3, 7)); // expected: 28
        System.out.println(sol.uniquePaths(3, 2)); // expected: 3
    }

}
