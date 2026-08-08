// Best Time to Buy and Sell Stock — https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
package arrays;

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
