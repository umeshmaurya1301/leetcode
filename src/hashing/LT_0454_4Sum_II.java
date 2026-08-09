/*
 * 454. 4Sum II — Medium
 * https://leetcode.com/problems/4sum-ii/description/
 *
 * Given four integer arrays nums1, nums2, nums3, and nums4, each of length n, return the number of tuples (i, j, k, l) such that:
 * - 0 <= i, j, k, l < n
 * - nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 *
 * Example 1:
 *   Input:  nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 *   Output: 2
 *   Explanation:
 *   - (0,0,0,0): 1 + (-2) + (-1) + 0 = 0 ✓
 *   - (1,1,0,0): 2 + (-1) + (-1) + 0 = 0 ✓
 *
 * Example 2:
 *   Input:  nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 *   Output: 1
 *
 * Constraints:
 *   n == nums1.length == nums2.length == nums3.length == nums4.length
 *   1 <= n <= 200
 *   -2^28 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2^28
 *
 * Approach: Hash every pairwise sum of the first two arrays, then look up complements from the other two — O(n^2) time, O(n^2) space.
 */
package hashing;

import java.util.*;

class LT_0454_4Sum_II {

    public int fourSumCount(
            int[] nums1,
            int[] nums2,
            int[] nums3,
            int[] nums4) {

        Map<Integer, Integer> map = new HashMap<>();

        // Store all sums of nums1 + nums2
        for (int a : nums1) {
            for (int b : nums2) {

                int sum = a + b;

                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int count = 0;

        // Find complements from nums3 + nums4
        for (int c : nums3) {
            for (int d : nums4) {

                int target = -(c + d);

                count += map.getOrDefault(target, 0);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        LT_0454_4Sum_II sol = new LT_0454_4Sum_II();
        System.out.println(sol.fourSumCount(new int[]{1, 2}, new int[]{-2, -1}, new int[]{-1, 2}, new int[]{0, 2})); // expected: 2
        System.out.println(sol.fourSumCount(new int[]{0}, new int[]{0}, new int[]{0}, new int[]{0})); // expected: 1
    }
}
