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
 * Approach: Count prefix-sum remainders in a HashMap (normalising negatives) and pair equal ones — O(n) time, O(k) space.
 */
package math;

import java.util.*;

class LT_0974_Subarray_Sums_Divisible_by_K_1_PrefixSumHashMap {
    public int subarraysDivByK(int[] nums, int k) {        
        Map<Integer, Integer> remainderCount = new HashMap<>();
        remainderCount.put(0, 1); // Important: empty prefix sum

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;

            int remainder = ((prefixSum % k) + k) % k;
            count += remainderCount.getOrDefault(remainder, 0);
            remainderCount.put(remainder, remainderCount.getOrDefault(remainder, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        LT_0974_Subarray_Sums_Divisible_by_K_1_PrefixSumHashMap sol = new LT_0974_Subarray_Sums_Divisible_by_K_1_PrefixSumHashMap();
        System.out.println(sol.subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5)); // expected: 7
        System.out.println(sol.subarraysDivByK(new int[]{5}, 5)); // expected: 1
    }
}
