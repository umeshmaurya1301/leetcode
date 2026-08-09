/*
 * 376. Wiggle Subsequence — Medium
 * https://leetcode.com/problems/wiggle-subsequence/
 *
 * A wiggle sequence is a sequence where the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with one element and a sequence with two non-equal elements are trivially wiggle sequences.
 *
 * - For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6, -3, 5, -7, 3) alternate between positive and negative.
 * - In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences. The first is not because its first two differences are positive, and the second is not because its last difference is zero.
 *
 * A subsequence is obtained by deleting some elements (possibly zero) from the original sequence, leaving the remaining elements in their original order.
 *
 * Given an integer array nums, return the length of the longest wiggle subsequence of nums.
 *
 * Example 1:
 *   Input:  nums = [1,7,4,9,2,5]
 *   Output: 6
 *   Explanation: The entire sequence is a wiggle sequence with differences (6, -3, 5, -7, 3).
 *
 * Example 2:
 *   Input:  nums = [1,17,5,10,13,15,10,5,16,8]
 *   Output: 7
 *   Explanation: There are several subsequences that achieve this length. One is [1, 17, 10, 13, 10, 16, 8] with differences (16, -7, 3, -3, 6, -8).
 *
 * Example 3:
 *   Input:  nums = [1,2,3,4,5,6,7,8,9]
 *   Output: 2
 *
 * Constraints:
 *   1 <= nums.length <= 1000
 *   0 <= nums[i] <= 1000
 *   Follow up: could you solve this in O(n) time?
 *
 * Approach: Memoized state machine on (index, expecting up or down) — O(n) time, O(n) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0376_Wiggle_Subsequence_1_MemoizedStateMachine {
    public int wiggleMaxLength(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][2];
        for (int[] a : dp)
            Arrays.fill(a, -1);

        int val1 = dfs(nums, dp, 0, 0);
        int val2 = dfs(nums, dp, 0, 1);
        return Math.max(val1, val2);
    }

    private int dfs(int[] nums, int[][] dp, int idx, int isUp) {
        int len = nums.length;
        if (idx == len)
            return 0;

        if (dp[idx][isUp] != -1)
            return dp[idx][isUp];

        int val = 0;
        if (isUp == 0) {
            if (idx == 0 || nums[idx] > nums[idx - 1]) {
                val = 1 + dfs(nums, dp, idx + 1, 1);
            } else {
                val = dfs(nums, dp, idx + 1, 0);
            }
        }

        if (isUp == 1) {
            if (idx == 0 || nums[idx] < nums[idx - 1]) {
                val = 1 + dfs(nums, dp, idx + 1, 0);
            } else {
                val = dfs(nums, dp, idx + 1, 1);
            }
        }

        return dp[idx][isUp] = val;
    }

    public static void main(String[] args) {
        LT_0376_Wiggle_Subsequence_1_MemoizedStateMachine sol = new LT_0376_Wiggle_Subsequence_1_MemoizedStateMachine();
        System.out.println(sol.wiggleMaxLength(new int[]{1, 7, 4, 9, 2, 5})); // expected: 6
        System.out.println(sol.wiggleMaxLength(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8})); // expected: 7
        System.out.println(sol.wiggleMaxLength(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9})); // expected: 2
    }
}
