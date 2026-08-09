/*
 * 974. Subarray Sums Divisible by K — Medium
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/
 *
 * Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
 *
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 *   Input:  nums = [4, 5, 0, -2, -3, 1], k = 5
 *   Output: 7
 *   Explanation: There are 7 subarrays with a sum divisible by 5: [4,5,0,-2,-3,1], [5], [5,0], [5,0,-2,-3], [0], [0,-2,-3], [-2,-3].
 *
 * Example 2:
 *   Input:  nums = [5], k = 5
 *   Output: 1
 *
 * Constraints:
 *   1 <= nums.length <= 3 * 10^4
 *   -10^4 <= nums[i] <= 10^4
 *   2 <= k <= 10^4
 *
 * Approach: Same remainder counting, but in a k-slot array instead of a HashMap — O(n) time, O(k) space.
 */
package prefixsum;

import java.util.*;

class LT_0974_Subarray_Sums_Divisible_by_K_2_PrefixModArray {
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        int prefixMod = 0, result = 0;

        // There are k mod groups 0...k-1.
        int[] modGroups = new int[k];
        modGroups[0] = 1;

        for (int num: nums) {
            // Take modulo twice to avoid negative remainders.
            prefixMod = (prefixMod + num % k + k) % k;
            // Add the count of subarrays that have the same remainder as the current
            // one to cancel out the remainders.
            result += modGroups[prefixMod];
            modGroups[prefixMod]++;
        }

        return result;
    }

    public static void main(String[] args) {
        LT_0974_Subarray_Sums_Divisible_by_K_2_PrefixModArray sol = new LT_0974_Subarray_Sums_Divisible_by_K_2_PrefixModArray();
        System.out.println(sol.subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5)); // expected: 7
        System.out.println(sol.subarraysDivByK(new int[]{5}, 5)); // expected: 1
    }
}
