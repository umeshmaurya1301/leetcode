/*
 * 518. Coin Change II — Medium
 * https://leetcode.com/problems/coin-change-ii/description/
 *
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 *
 * Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * The answer is guaranteed to fit into a signed 32-bit integer.
 *
 * Example 1:
 *   Input:  amount = 5, coins = [1,2,5]
 *   Output: 4
 *   Explanation: there are four ways to make up the amount:
 *   5=5 | 5=2+2+1 | 5=2+1+1+1 | 5=1+1+1+1+1
 *
 * Example 2:
 *   Input:  amount = 3, coins = [2]
 *   Output: 0
 *   Explanation: the amount 3 cannot be made up with only coin 2.
 *
 * Example 3:
 *   Input:  amount = 10, coins = [10]
 *   Output: 1
 *
 * Constraints:
 *   1 <= coins.length <= 300
 *   1 <= coins[i] <= 5000
 *   All values of coins are unique.
 *   0 <= amount <= 5000
 *
 * Approach: Memoized unbounded knapsack on (coin index, amount) — take stays on the same coin, skip advances — O(coins * amount) time and space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0518_Coin_Change_II {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount+1];
        for(int [] a : dp) Arrays.fill(a, -1);
        return dfs(coins, dp, amount, 0);
    }

    private int dfs(int[] coins, int[][] dp, int amount, int idx) {
        if (amount==0) return 1;
        if (amount < 0) return 0;
        if (dp[idx][amount]!=-1) return dp[idx][amount];

        int tempCount = dfs(coins, dp, amount-coins[idx], idx);
        if (idx+1<coins.length) tempCount += dfs(coins, dp, amount, idx+1);
        return dp[idx][amount] = tempCount;
    }

    public static void main(String[] args) {
        LT_0518_Coin_Change_II sol = new LT_0518_Coin_Change_II();
        System.out.println(sol.change(5, new int[]{1, 2, 5})); // expected: 4
        System.out.println(sol.change(3, new int[]{2})); // expected: 0
        System.out.println(sol.change(10, new int[]{10})); // expected: 1
    }
}
