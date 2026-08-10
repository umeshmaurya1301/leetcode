/*
 * 1027. Longest Arithmetic Subsequence — Medium
 * https://leetcode.com/problems/longest-arithmetic-subsequence/
 *
 * Given an array nums of integers, return the length of the longest arithmetic subsequence in nums.
 *
 * A subsequence of an array is a list nums[i1], nums[i2], ..., nums[ik] with
 * 0 <= i1 < i2 < ... < ik <= nums.length - 1. A sequence seq is arithmetic if
 * seq[i + 1] - seq[i] are all the same value (for 0 <= i < seq.length - 1).
 *
 * Example 1:
 *   Input:  nums = [3,6,9,12]
 *   Output: 4
 *   Explanation: The whole array is an arithmetic sequence with a common difference of 3.
 *
 * Example 2:
 *   Input:  nums = [9,4,7,2,10]
 *   Output: 3
 *   Explanation: The longest arithmetic subsequence is [4,7,10].
 *
 * Example 3:
 *   Input:  nums = [20,1,15,3,10,5,8]
 *   Output: 4
 *   Explanation: The longest arithmetic subsequence is [20,15,10,5].
 *
 * Constraints:
 *   2 <= nums.length <= 1000
 *   0 <= nums[i] <= 500
 *
 * Approach: O(n^2) pairwise DP with a hashed second dimension — dp[i] maps a common difference to
 *           the length of the arithmetic chain ending at i with that difference. For each pair
 *           j < i, glue i onto dp[j][diff], defaulting to 1 so that any single element seeds a
 *           chain and any pair gives 2 — O(n^2) time, O(n^2) space.
 */
package hashing;

import java.util.*;

class LT_1027_Longest_Arithmetic_Subsequence {
    public int longestArithSeqLength(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer>[] dp = new HashMap[len];
        for (int i = 0; i < len; i++) {
            dp[i] = new HashMap<>();
        }
        int ans = 2;

        for (int i=1; i<len; i++) {

            for (int j=0; j<i; j++) {
                int diff = nums[i] - nums[j];
                int prevLength = dp[j].getOrDefault(diff, 1);
                dp[i].put(diff,  Math.max(dp[i].getOrDefault(diff, 0), prevLength + 1)  );
                ans = Math.max ( ans, dp[i].get(diff));
            }
        }

        return ans;

    }

    public static void main(String[] args) {
        LT_1027_Longest_Arithmetic_Subsequence sol = new LT_1027_Longest_Arithmetic_Subsequence();
        System.out.println(sol.longestArithSeqLength(new int[]{3, 6, 9, 12})); // expected: 4
        System.out.println(sol.longestArithSeqLength(new int[]{9, 4, 7, 2, 10})); // expected: 3
        System.out.println(sol.longestArithSeqLength(new int[]{20, 1, 15, 3, 10, 5, 8})); // expected: 4
    }

}
