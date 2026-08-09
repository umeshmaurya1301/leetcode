/*
 * 123. Best Time to Buy and Sell Stock III — Hard
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the
 * stock before you buy again).
 *
 * Example 1:
 *   Input:  prices = [3,3,5,0,0,3,1,4]
 *   Output: 6
 *   Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *                Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 *
 * Example 2:
 *   Input:  prices = [1,2,3,4,5]
 *   Output: 4
 *   Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 *                You must sell before buying again.
 *
 * Example 3:
 *   Input:  prices = [7,6,4,3,1]
 *   Output: 0
 *   Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 * Constraints:
 *   1 <= prices.length <= 10^5
 *   0 <= prices[i] <= 10^5
 *
 * Approach: Top-down memoization over (day, canBuy, transactions left). Buy contributes
 *           -price, sell contributes +price and spends one transaction — O(n) time (4n states),
 *           O(n) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0123_Best_Time_to_Buy_and_Sell_Stock_III {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        Integer[][][] dp = new Integer[n][2][2];
        dfs (prices, dp, 0, 1, 1);
        // System.out.println(Arrays.deepToString(dp));
        return dp[0][1][1];
    }

    private int dfs (int[] prices, Integer[][][] dp, int idx, int canBuy, int txnLeft) {
        // System.out.println("idx: "+idx);
        // System.out.println("canBuy: "+canBuy);
        // System.out.println("txnLeft: "+txnLeft);
        if (txnLeft<0 ||  idx==prices.length) return 0;
        if (dp[idx][canBuy][txnLeft]!=null) return dp[idx][canBuy][txnLeft];

        int profit = 0;
        if (canBuy == 1) {
            int buy = -prices[idx] + dfs (prices, dp, idx+1, 0, txnLeft);
            int skip = dfs (prices, dp, idx+1, 1, txnLeft);
            profit = Math.max (buy, skip);
        } else {
            int sell = +prices[idx] + dfs (prices, dp, idx+1, 1, txnLeft-1);
            int skip = dfs(prices, dp, idx+1, 0, txnLeft);
            profit = Math.max(sell, skip);
        }

        return dp[idx][canBuy][txnLeft] = profit;
    }

    public static void main(String[] args) {
        LT_0123_Best_Time_to_Buy_and_Sell_Stock_III sol = new LT_0123_Best_Time_to_Buy_and_Sell_Stock_III();
        System.out.println(sol.maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4})); // expected: 6
        System.out.println(sol.maxProfit(new int[]{1, 2, 3, 4, 5})); // expected: 4
        System.out.println(sol.maxProfit(new int[]{7, 6, 4, 3, 1})); // expected: 0
    }

}
