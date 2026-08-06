// Best Time to Buy and Sell Stock — https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
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
}
