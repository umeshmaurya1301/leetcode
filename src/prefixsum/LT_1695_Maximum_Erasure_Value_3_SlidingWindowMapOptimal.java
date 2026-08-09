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
 * Approach: Sliding window over a last-index map, jumping left past the duplicate only when it lies inside the window — O(n) time, O(n) space.
 */
package prefixsum;

import java.util.*;

class LT_1695_Maximum_Erasure_Value_3_SlidingWindowMapOptimal {
    public int maximumUniqueSubarray(int[] nums) {
        int len = nums.length;
        if (len == 1) return nums[0];

        int i = 0;
        int j = 0;

        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int globalMax = 0;

        while (j < len) {
            int val = nums[j];

            if (map.containsKey(val) && map.get(val) >= i) {
                int prevIdx = map.get(val);

                // shrink window from left until i > prevIdx
                while (i <= prevIdx) {
                    sum -= nums[i];
                    i++;
                }
            }

            map.put(val, j);
            sum += val;
            globalMax = Math.max(globalMax, sum);
            j++;
        }

        return globalMax;
    }

    public static void main(String[] args) {
        LT_1695_Maximum_Erasure_Value_3_SlidingWindowMapOptimal sol = new LT_1695_Maximum_Erasure_Value_3_SlidingWindowMapOptimal();
        System.out.println(sol.maximumUniqueSubarray(new int[]{4, 2, 4, 5, 6})); // expected: 17
        System.out.println(sol.maximumUniqueSubarray(new int[]{5, 2, 1, 2, 5, 2, 1, 2, 5})); // expected: 8
    }
}
