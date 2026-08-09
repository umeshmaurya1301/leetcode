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
 * Approach: Patience sorting: binary search a tails array for the first value >= x and overwrite it — O(n log n) time, O(n) space.
 */
package binarysearch;

import java.util.*;

class LT_0300_Longest_Increasing_Subsequence_3_BinarySearchPatience {
    public int lengthOfLIS(int[] nums) {
        List<Integer> tails = new ArrayList<>();

        for (int x : nums) {
            // first index with tails[idx] >= x
            int lo = 0, hi = tails.size();
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (tails.get(mid) < x) lo = mid + 1;
                else hi = mid;
            }

            if (lo == tails.size()) tails.add(x);   // extend
            else tails.set(lo, x);                  // tighten
        }

        return tails.size();
    }

    public static void main(String[] args) {
        LT_0300_Longest_Increasing_Subsequence_3_BinarySearchPatience sol = new LT_0300_Longest_Increasing_Subsequence_3_BinarySearchPatience();
        System.out.println(sol.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18})); // expected: 4
        System.out.println(sol.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3})); // expected: 4
        System.out.println(sol.lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7})); // expected: 1
    }
}
