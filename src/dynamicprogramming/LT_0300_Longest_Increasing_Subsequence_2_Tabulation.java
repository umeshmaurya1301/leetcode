// Longest Increasing Subsequence — https://leetcode.com/problems/longest-increasing-subsequence/
package dynamicprogramming;

import java.util.*;

class LT_0300_Longest_Increasing_Subsequence_2_Tabulation {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);          // every element alone is an LIS of length 1

        int best = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            best = Math.max(best, dp[i]);
        }
        return best;
    }

    public static void main(String[] args) {
        LT_0300_Longest_Increasing_Subsequence_2_Tabulation sol = new LT_0300_Longest_Increasing_Subsequence_2_Tabulation();
        System.out.println(sol.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18})); // expected: 4
        System.out.println(sol.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3})); // expected: 4
        System.out.println(sol.lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7})); // expected: 1
    }
}
