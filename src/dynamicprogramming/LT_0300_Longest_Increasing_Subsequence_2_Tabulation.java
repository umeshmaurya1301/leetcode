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
 * Approach: Tabulation where dp[i] is the LIS ending at i, filled from all j < i — O(n^2) time, O(n) space.
 */
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
