/*
 * 122. Best Time to Buy and Sell Stock II — Medium
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * You are given an integer array prices where prices[i] is the price of a given stock on the
 * ith day.
 *
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share
 * of the stock at any time. However, you can sell and buy the stock multiple times on the same
 * day, ensuring you never hold more than one share of the stock.
 *
 * Find and return the maximum profit you can achieve.
 *
 * Example 1:
 *   Input:  prices = [7,1,5,3,6,4]
 *   Output: 7
 *   Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 *                Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 *                Total profit is 4 + 3 = 7.
 *
 * Example 2:
 *   Input:  prices = [1,2,3,4,5]
 *   Output: 4
 *   Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 *
 * Example 3:
 *   Input:  prices = [7,6,4,3,1]
 *   Output: 0
 *   Explanation: There is no way to make a positive profit, so we never buy the stock.
 *
 * Constraints:
 *   1 <= prices.length <= 3 * 10^4
 *   0 <= prices[i] <= 10^4
 *
 * Approach: Greedy — a long hold telescopes into the sum of its daily changes, and churning is
 *           free, so collect every upward daily move — O(n) time, O(1) space.
 */
package greedy;

import java.util.*;

class LT_0122_Best_Time_to_Buy_and_Sell_Stock_II {
    public int maxProfit(int[] prices) {
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            // Buy yesterday and sell today if profitable
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }

        return profit;
    }

    public static void main(String[] args) {
        LT_0122_Best_Time_to_Buy_and_Sell_Stock_II sol = new LT_0122_Best_Time_to_Buy_and_Sell_Stock_II();
        System.out.println(sol.maxProfit(new int[]{7, 1, 5, 3, 6, 4})); // expected: 7
        System.out.println(sol.maxProfit(new int[]{1, 2, 3, 4, 5})); // expected: 4
        System.out.println(sol.maxProfit(new int[]{7, 6, 4, 3, 1})); // expected: 0
    }

}
