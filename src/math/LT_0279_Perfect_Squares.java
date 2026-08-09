/*
 * 279. Perfect Squares — Medium
 * https://leetcode.com/problems/perfect-squares/
 *
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 *
 * A perfect square is an integer that is the square of an integer; in other words, it is
 * the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares
 * while 3 and 11 are not.
 *
 * Example 1:
 *   Input:  n = 12
 *   Output: 3
 *   Explanation: 12 = 4 + 4 + 4.
 *
 * Example 2:
 *   Input:  n = 13
 *   Output: 2
 *   Explanation: 13 = 4 + 9.
 *
 * Constraints:
 *   1 <= n <= 10^4
 *
 * Approach: Coin Change (min count) with the coins generated on the fly as i*i <= n —
 *           top-down memoization on the remaining amount, O(n * sqrt(n)) time, O(n) space.
 */
package math;

import java.util.*;

class LT_0279_Perfect_Squares {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        dfs(n, dp);
        return dp[n];
    }

    private int dfs(int n, int[] dp) {
        if (n==0) return 0;
        if (n<0) return 0;
        if (dp[n] != -1) return dp[n];

        int ans = Integer.MAX_VALUE;
        for (int i=1; i*i<=n; i++) {
            ans = Math.min(ans, 1 + dfs(n-i*i, dp));
        }

        return dp[n] = ans;
    }

    public static void main(String[] args) {
        LT_0279_Perfect_Squares sol = new LT_0279_Perfect_Squares();
        System.out.println(sol.numSquares(12)); // expected: 3
        System.out.println(sol.numSquares(13)); // expected: 2
    }

}
