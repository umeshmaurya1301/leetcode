/*
 * 523. Continuous Subarray Sum — Medium
 * https://leetcode.com/problems/continuous-subarray-sum/
 *
 * Given an integer array nums and an integer k, return true if the array has a continuous subarray of size at least 2 whose sum is a multiple of k.
 *
 * Example:
 *   Input:  nums = [23,2,4,6,7], k = 6
 *   Output: true
 *   Explanation: [2,4] → sum = 6 (multiple of k)
 *   Input:  nums = [23,2,6,4,7], k = 6
 *   Output: true
 *   Input:  nums = [23,2,6,4,7], k = 13
 *   Output: false
 *
 * Constraints:
 *   1 ≤ nums.length ≤ 10⁵
 *   0 ≤ nums[i] ≤ 10⁹
 *   0 ≤ sum(nums) ≤ 2³¹ - 1
 *   1 ≤ k ≤ 2³¹ - 1
 *
 * Approach: Map each prefix-sum remainder to its first index; a repeat two apart means a valid subarray — O(n) time, O(min(n, k)) space.
 */
package math;

import java.util.*;

class LT_0523_Continuous_Subarry_Sum {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // base case

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            int rem = sum % k;

            if (map.containsKey(rem)) {
                if (i - map.get(rem) > 1) {
                    return true;
                }
            } else {
                map.put(rem, i);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        LT_0523_Continuous_Subarry_Sum sol = new LT_0523_Continuous_Subarry_Sum();
        System.out.println(sol.checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6)); // expected: true
        System.out.println(sol.checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 6)); // expected: true
        System.out.println(sol.checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 13)); // expected: false
    }
}
