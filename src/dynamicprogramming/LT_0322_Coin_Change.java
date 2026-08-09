/*
 * 322. Coin Change — Medium
 * https://leetcode.com/problems/coin-change/
 *
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Example 1:
 *   Input:  coins = [1,2,5], amount = 11
 *   Output: 3
 *   Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 *   Input:  coins = [2], amount = 3
 *   Output: -1
 *
 * Example 3:
 *   Input:  coins = [1], amount = 0
 *   Output: 0
 *
 * Constraints:
 *   1 <= coins.length <= 12
 *   1 <= coins[i] <= 2^31 - 1
 *   0 <= amount <= 10^4
 *
 * Approach: Top-down memoization on the remaining amount, trying every coin — O(amount * coins) time, O(amount) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0322_Coin_Change {
    public int coinChange(int[] coins, int amount) {
        if (amount==0) return 0;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        int ans = dfs(coins, amount, dp);
        System.out.println(Arrays.toString(dp));
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
        // return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int dfs(int[] coins, int amount, int[] dp) {

        if (amount == 0) return 0;
        if (amount < 0) return Integer.MAX_VALUE;
        if (dp[amount] != -1) return dp[amount];

        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = dfs(coins, amount - coin, dp);
            if(res!=Integer.MAX_VALUE) {minCoins = Math.min(minCoins, res+1);}
        }

        return dp[amount] = minCoins;
    }

    public static void main(String[] args) {
        LT_0322_Coin_Change sol = new LT_0322_Coin_Change();
        System.out.println(sol.coinChange(new int[]{1, 2, 5}, 11)); // expected: 3
        System.out.println(sol.coinChange(new int[]{2}, 3)); // expected: -1
        System.out.println(sol.coinChange(new int[]{1}, 0)); // expected: 0
    }

}
