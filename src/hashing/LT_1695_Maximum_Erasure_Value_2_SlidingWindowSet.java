/*
 * 1695. Maximum Erasure Value — Medium
 * https://leetcode.com/problems/maximum-erasure-value/
 *
 * You are given an array of positive integers nums.
 * You must erase exactly one subarray and return the maximum possible sum of elements in the erased subarray.
 *
 * A subarray is valid only if all its elements are unique.
 *
 * Example 1:
 *   Input:  nums = [4,2,4,5,6]
 *   Output: 17
 *   Explanation:
 *   Best subarray: [2,4,5,6] → sum = 17
 *
 * Example 2:
 *   Input:  nums = [5,2,1,2,5,2,1,2,5]
 *   Output: 8
 *   Explanation:
 *   Best unique subarray is [5,2,1] or [1,2,5] → sum = 8
 *
 * Constraints:
 *   1 <= nums.length <= 10^5
 *   1 <= nums[i] <= 10^4
 *
 * Approach: Sliding window over a HashSet, dropping from the left until the duplicate is gone — O(n) time, O(n) space.
 */
package hashing;

import java.util.*;

class LT_1695_Maximum_Erasure_Value_2_SlidingWindowSet {
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        int left = 0, right = 0;
        int sum = 0, max = 0;
        Set<Integer> set = new HashSet<>();

        while (right < n) {
            if (!set.contains(nums[right])) {
                set.add(nums[right]);
                sum += nums[right];
                max = Math.max(max, sum);
                right++;
            } else {
                set.remove(nums[left]);
                sum -= nums[left];
                left++;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        LT_1695_Maximum_Erasure_Value_2_SlidingWindowSet sol = new LT_1695_Maximum_Erasure_Value_2_SlidingWindowSet();
        System.out.println(sol.maximumUniqueSubarray(new int[]{4, 2, 4, 5, 6})); // expected: 17
        System.out.println(sol.maximumUniqueSubarray(new int[]{5, 2, 1, 2, 5, 2, 1, 2, 5})); // expected: 8
    }
}
