/*
 * 188. Best Time to Buy and Sell Stock IV — Hard
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 *
 * You are given an integer array prices where prices[i] is the price of a given stock on the
 * ith day, and an integer k.
 *
 * Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you
 * may buy at most k times and sell at most k times.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the
 * stock before you buy again).
 *
 * Example 1:
 *   Input:  k = 2, prices = [2,4,1]
 *   Output: 2
 *   Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 *
 * Example 2:
 *   Input:  k = 2, prices = [3,2,6,5,0,3]
 *   Output: 7
 *   Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 *                Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *
 * Constraints:
 *   1 <= k <= 100
 *   1 <= prices.length <= 1000
 *   0 <= prices[i] <= 1000
 *
 * Approach: The LT_123 recursion with the transaction cap generalised — memoization over
 *           (day, canBuy, transactions left), with txnLeft 0-indexed from k-1 —
 *           O(n * k) time, O(n * k) space.
 */
package arrays;

import java.util.*;

class LT_0188_Best_Time_to_Buy_and_Sell_Stock_IV {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        Integer[][][] dp = new Integer[n][2][k];
        dfs (prices, dp, 0, 1, k-1);
        return dp[0][1][k-1];
    }

    private int dfs (int[] prices, Integer[][][] dp, int idx, int canBuy, int txnLeft) {
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
        LT_0188_Best_Time_to_Buy_and_Sell_Stock_IV sol = new LT_0188_Best_Time_to_Buy_and_Sell_Stock_IV();
        System.out.println(sol.maxProfit(2, new int[]{2, 4, 1})); // expected: 2
        System.out.println(sol.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3})); // expected: 7
    }

}
