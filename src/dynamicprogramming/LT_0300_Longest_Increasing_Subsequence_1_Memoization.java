/*
 * 300. Longest Increasing Subsequence — Medium
 * https://leetcode.com/problems/longest-increasing-subsequence/
 *
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence derived from the array by deleting some or no elements without changing the order of the remaining elements.
 * e.g. [3, 6, 2, 7] is a subsequence of [0, 3, 1, 6, 2, 2, 7].
 *
 * Example 1:
 *   Input:  nums = [10, 9, 2, 5, 3, 7, 101, 18]
 *   Output: 4
 *   Explanation: The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
 *
 * Example 2:
 *   Input:  nums = [0, 1, 0, 3, 2, 3]
 *   Output: 4
 *   Explanation: [0, 1, 2, 3].
 *
 * Example 3:
 *   Input:  nums = [7, 7, 7, 7, 7, 7, 7]
 *   Output: 1
 *   Explanation: Increasing must be strict, so equal values can't chain. Any single 7 is the best we get.
 *
 * Constraints:
 *   1 <= nums.length <= 2500
 *   -10^4 <= nums[i] <= 10^4
 *
 *   Follow-up: Can you come up with an algorithm that runs in O(n log n) time complexity?
 *
 * Approach: Memoized take / skip recursion on (current index, previous index) — O(n^2) time, O(n^2) space.
 */
package dynamicprogramming;

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

    public static void main(String[] args) {
        LT_0300_Longest_Increasing_Subsequence_1_Memoization sol = new LT_0300_Longest_Increasing_Subsequence_1_Memoization();
        System.out.println(sol.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18})); // expected: 4
        System.out.println(sol.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3})); // expected: 4
        System.out.println(sol.lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7})); // expected: 1
    }
}
