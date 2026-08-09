/*
 * 309. Best Time to Buy and Sell Stock with Cooldown — Medium
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like
 * (i.e., buy one and sell one share of the stock multiple times) with the following
 * restrictions:
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the
 * stock before you buy again).
 *
 * Example 1:
 *   Input:  prices = [1,2,3,0,2]
 *   Output: 3
 *   Explanation: transactions = [buy, sell, cooldown, buy, sell]
 *
 * Example 2:
 *   Input:  prices = [1]
 *   Output: 0
 *
 * Constraints:
 *   1 <= prices.length <= 5000
 *   0 <= prices[i] <= 1000
 *
 * Approach: Two-state memoization over (day, canBuy) with unlimited transactions; the cooldown
 *           is encoded as an index jump — selling on day idx resumes at idx+2, which is why the
 *           base case must use >= rather than == — O(n) time, O(n) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        Integer[][] dp = new Integer[n][2];
        dfs(prices, dp, 0, 1);
        return dp[0][1];
    }

    private int dfs (int[] prices, Integer[][] dp, int idx, int canBuy) {
        if (idx>=prices.length) return 0;
        if (dp[idx][canBuy]!=null) return dp[idx][canBuy];

        int profit = 0;
        if (canBuy==1) {
            int buy = -prices[idx] + dfs (prices, dp, idx+1, 0);
            int skip = dfs (prices, dp, idx+1, 1);
            profit = Math.max (buy, skip);
        } else {
            int sell = +prices[idx] + dfs (prices, dp, idx+2, 1);
            int skip = dfs(prices, dp, idx+1, 0);
            profit = Math.max (sell, skip);
        }

        return dp[idx][canBuy] = profit;
    }

    public static void main(String[] args) {
        LT_0309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown sol = new LT_0309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown();
        System.out.println(sol.maxProfit(new int[]{1, 2, 3, 0, 2})); // expected: 3
        System.out.println(sol.maxProfit(new int[]{1})); // expected: 0
    }

}
