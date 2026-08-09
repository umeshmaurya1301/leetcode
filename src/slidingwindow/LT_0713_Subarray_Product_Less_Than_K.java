/*
 * 713. Subarray Product Less Than K — Medium
 * https://leetcode.com/problems/subarray-product-less-than-k/
 *
 * Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.
 *
 * Example 1:
 *   Input:  nums = [10,5,2,6], k = 100
 *   Output: 8
 *   Explanation: The 8 subarrays with product < 100 are:
 *   [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]
 *   Note: [10,5,2] is excluded — product 100 is not strictly less than 100.
 *
 * Example 2:
 *   Input:  nums = [1,2,3], k = 0
 *   Output: 0
 *
 * Constraints:
 *   1 <= nums.length <= 3 * 10^4
 *   1 <= nums[i] <= 1000
 *   0 <= k <= 10^6
 *
 * Approach: Sliding window on a running product, adding (right - left + 1) subarrays per step — O(n) time, O(1) space.
 */
package slidingwindow;

import java.util.*;

class LT_0713_Subarray_Product_Less_Than_K {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int left = 0;
        int len = nums.length;
        int count = 0;
        int prod = 1;
        if(k<=1) return 0;

        for(int right = 0; right<len; right++) {
            prod = prod * nums[right];

            while(prod >= k) {
                prod = prod/nums[left];
                left++;
            }
            count += right-left+1;
        }
        return count;
    }

    public static void main(String[] args) {
        LT_0713_Subarray_Product_Less_Than_K sol = new LT_0713_Subarray_Product_Less_Than_K();
        System.out.println(sol.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100)); // expected: 8
        System.out.println(sol.numSubarrayProductLessThanK(new int[]{1, 2, 3}, 0)); // expected: 0
    }
}
