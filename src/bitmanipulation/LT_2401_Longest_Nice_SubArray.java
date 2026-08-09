/*
 * 2401. Longest Nice Subarray — Medium
 * https://leetcode.com/problems/longest-nice-subarray/
 *
 * You are given an array nums consisting of positive integers.
 *
 * We call a subarray of nums nice if the bitwise AND of every pair of elements that are in different positions in the subarray is equal to 0.
 *
 * Return the length of the longest nice subarray.
 *
 * A subarray is a contiguous part of an array.
 * Note that subarrays of length 1 are always considered nice.
 *
 * Example 1:
 *   Input:  nums = [1, 3, 8, 48, 10]
 *   Output: 3
 *   Explanation: The longest nice subarray is [3, 8, 48].
 *   - 3 AND 8 = 0 ✅
 *   - 3 AND 48 = 0 ✅
 *   - 8 AND 48 = 0 ✅
 *
 *   No longer nice subarray can be found.
 *
 * Example 2:
 *   Input:  nums = [3, 1, 5, 11, 13]
 *   Output: 1
 *   Explanation: Any subarray of length ≥ 2 contains numbers that share a bit. Answer is 1.
 *
 * Constraints:
 *   1 <= nums.length <= 10^5
 *   1 <= nums[i] <= 10^9
 *
 * Approach: Sliding window carrying an OR bitmask of the window; shrink while a bit collides — O(n) time, O(1) space.
 */
package bitmanipulation;

import java.util.*;

class LT_2401_Longest_Nice_SubArray {
    public int longestNiceSubarray(int[] nums) {

        int left  = 0;
        int mask  = 0;   // OR of all numbers currently in the window
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {

            // Shrink window from left until no bit conflict with nums[right]
            while ((mask & nums[right]) != 0) {
                mask ^= nums[left];   // remove nums[left]'s bits from mask
                left++;
            }

            // Safely add nums[right] — no shared bits
            mask |= nums[right];

            // Update answer
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        LT_2401_Longest_Nice_SubArray sol = new LT_2401_Longest_Nice_SubArray();
        System.out.println(sol.longestNiceSubarray(new int[]{1, 3, 8, 48, 10})); // expected: 3
        System.out.println(sol.longestNiceSubarray(new int[]{3, 1, 5, 11, 13})); // expected: 1
    }
}
