/*
 * 128. Longest Consecutive Sequence — Medium
 * https://leetcode.com/problems/longest-consecutive-sequence/description/
 *
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 * Example 1:
 *   Input:  nums = [100, 4, 200, 1, 3, 2]
 *   Output: 4
 *   Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 * Example 2:
 *   Input:  nums = [0, 3, 7, 2, 5, 8, 4, 6, 0, 1]
 *   Output: 9
 *   Explanation: The longest consecutive sequence is [0, 1, 2, 3, 4, 5, 6, 7, 8].
 *
 * Constraints:
 *   0 <= nums.length <= 10^5
 *   -10^9 <= nums[i] <= 10^9
 *
 * Approach: HashSet of all values; walk a streak only from numbers with no predecessor — O(n) time, O(n) space.
 */
package hashing;

import java.util.*;

class LT_0128_Longest_Consecutive_Sequence {

    public int longestConsecutive(int[] nums) {

        Set<Integer> set = new HashSet<>();

        for (int n : nums) {
            set.add(n);
        }

        int longest = 0;

        for (int n : set) {

            // Start only if sequence beginning
            if (!set.contains(n - 1)) {

                int curr = n;
                int streak = 1;

                while (set.contains(curr + 1)) {
                    curr++;
                    streak++;
                }

                longest = Math.max(longest, streak);
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        LT_0128_Longest_Consecutive_Sequence sol = new LT_0128_Longest_Consecutive_Sequence();
        System.out.println(sol.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2})); // expected: 4
        System.out.println(sol.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1})); // expected: 9
    }
}
