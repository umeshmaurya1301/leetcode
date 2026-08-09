/*
 * 121. Best Time to Buy and Sell Stock — Easy
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 * Example 1:
 *   Input:  prices = [7, 1, 5, 3, 6, 4]
 *   Output: 5
 *   Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6 − 1 = 5. Buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 *
 * Example 2:
 *   Input:  prices = [7, 6, 4, 3, 1]
 *   Output: 0
 *   Explanation: In this case, no transactions are done and the max profit = 0.
 *
 * Constraints:
 *   1 <= prices.length <= 10^5
 *   0 <= prices[i] <= 10^4
 *
 * Approach: Single pass tracking the running minimum price and the best profit against it — O(n) time, O(1) space.
 */
package greedy;

import java.util.*;

class LT_0121_Best_Time_to_Buy_and_Sell_Stock {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int num:prices) {
            if(num<min) {
                min = num;
            }
            max = Math.max(max, num-min);
        }

        return max;
    }

    public static void main(String[] args) {
        LT_0121_Best_Time_to_Buy_and_Sell_Stock sol = new LT_0121_Best_Time_to_Buy_and_Sell_Stock();
        System.out.println(sol.maxProfit(new int[]{7, 1, 5, 3, 6, 4})); // expected: 5
        System.out.println(sol.maxProfit(new int[]{7, 6, 4, 3, 1})); // expected: 0
    }
}
