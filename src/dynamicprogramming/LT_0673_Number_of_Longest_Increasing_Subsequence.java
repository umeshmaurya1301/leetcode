/*
 * 673. Number of Longest Increasing Subsequence — Medium
 * https://leetcode.com/problems/number-of-longest-increasing-subsequence/
 *
 * Given an integer array nums, return the number of longest increasing subsequences.
 *
 * Notice that the sequence has to be strictly increasing.
 *
 * Example 1:
 *   Input:  nums = [1,3,5,4,7]
 *   Output: 2
 *   Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
 *
 * Example 2:
 *   Input:  nums = [2,2,2,2,2]
 *   Output: 5
 *   Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing
 *                subsequences of length 1, so the output is 5.
 *
 * Constraints:
 *   1 <= nums.length <= 2000
 *   -10^6 <= nums[i] <= 10^6
 *   The answer is guaranteed to fit inside a 32-bit integer.
 *
 * Approach: The O(n^2) LIS tabulation carrying a second ledger — length[i] is the LIS length ending
 *           at i, count[i] is how many such LIS end at i. A strictly longer predecessor RESETS
 *           count[i] = count[j]; an equally long one ACCUMULATES count[i] += count[j]. The answer
 *           sums count[i] over every i tied at maxLen — O(n^2) time, O(n) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0673_Number_of_Longest_Increasing_Subsequence {
    public int findNumberOfLIS(int[] nums) {

        int len = nums.length;
        int[] length = new int[len];
        int[] count = new int[len];

        Arrays.fill(length, 1);
        Arrays.fill(count, 1);

        int maxLen = 1;

        for (int i=0; i<len; i++) {

            for (int j=0; j<i; j++) {

                if (nums[i] > nums[j]) {

                    if (length[j] + 1 > length[i]) {

                        length[i] = length[j] + 1;
                        count[i] = count[j];

                    } else if (length[j] + 1 == length[i]) {
                        count[i] += count[j];
                    }

                }

            }
            maxLen = Math.max(maxLen, length[i]);
        }

        int ans = 0;
        for (int i=0; i<len; i++) {
            if (length[i]==maxLen) ans += count[i];
        }

        return ans;

    }

    public static void main(String[] args) {
        LT_0673_Number_of_Longest_Increasing_Subsequence sol =
                new LT_0673_Number_of_Longest_Increasing_Subsequence();
        System.out.println(sol.findNumberOfLIS(new int[]{1, 3, 5, 4, 7})); // expected: 2
        System.out.println(sol.findNumberOfLIS(new int[]{2, 2, 2, 2, 2})); // expected: 5
    }

}
