// Longest Increasing Subsequence — https://leetcode.com/problems/longest-increasing-subsequence/
import java.util.*;

class LT_0300_Longest_Increasing_Subsequence_1_Memoization {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len+1];
        for (int[] a : dp) Arrays.fill(a, -1);
        dfs(nums, 0, -1,  dp);
        // System.out.println(Arrays.deepToString(dp));
        return dp[0][0];
    }

    private int dfs (int[] nums, int curr, int prev, int[][] dp) {
        
        if (curr==nums.length) return 0;
        if (dp[curr][prev+1] != -1) return dp[curr][prev+1];

        int skip = dfs(nums, curr+1, prev, dp);
        int take = 0;
        if (prev == -1 || nums[curr] > nums[prev]) {
            take = 1 + dfs (nums, curr+1, curr, dp);
        }

        return dp[curr][prev+1] = Math.max(take, skip);
    }
}
