/*
 * 714. Best Time to Buy and Sell Stock with Transaction Fee — Medium
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day,
 * and an integer fee representing a transaction fee.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like,
 * but you need to pay the transaction fee for each transaction.
 *
 * Note:
 * You may not engage in multiple transactions simultaneously (i.e., you must sell the stock
 * before you buy again).
 * The transaction fee is only charged once for each stock purchase and sale.
 *
 * Example 1:
 *   Input:  prices = [1,3,2,8,4,9], fee = 2
 *   Output: 8
 *   Explanation: Buying at prices[0] = 1, selling at prices[3] = 8, buying at prices[4] = 4,
 *                selling at prices[5] = 9.
 *                The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 *
 * Example 2:
 *   Input:  prices = [1,3,7,5,10,3], fee = 3
 *   Output: 6
 *
 * Constraints:
 *   1 <= prices.length <= 5 * 10^4
 *   1 <= prices[i] < 5 * 10^4
 *   0 <= fee < 5 * 10^4
 *
 * Approach: Two-state memoization over (day, canBuy) with unlimited transactions; the fee is
 *           charged once per completed round trip, on the sell branch — O(n) time, O(n) space.
 */
package arrays;

import java.util.*;

class LT_0714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        Integer[][] dp = new Integer[n][2];
        dfs(prices, fee, dp, 0, 1);
        return dp[0][1];
    }

    private int dfs (int[] prices, int fee, Integer[][] dp, int idx, int canBuy) {
        if (idx==prices.length) return 0;
        if (dp[idx][canBuy]!=null) return dp[idx][canBuy];

        int profit = 0;
        if (canBuy==1) {
            int buy = -prices[idx] + dfs (prices, fee, dp, idx+1, 0);
            int skip = dfs (prices, fee, dp, idx+1, 1);
            profit = Math.max(buy, skip);
        } else {
            int sell = +prices[idx] + dfs (prices, fee, dp, idx + 1, 1) - fee;
            int skip = dfs (prices, fee, dp, idx + 1, 0);
            profit = Math.max (sell, skip);
        }

        return dp[idx][canBuy] = profit;
    }

    public static void main(String[] args) {
        LT_0714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee sol = new LT_0714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee();
        System.out.println(sol.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2)); // expected: 8
        System.out.println(sol.maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3)); // expected: 6
    }

}
