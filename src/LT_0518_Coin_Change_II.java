// Coin Change II — https://leetcode.com/problems/coin-change-ii/description/
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
}
