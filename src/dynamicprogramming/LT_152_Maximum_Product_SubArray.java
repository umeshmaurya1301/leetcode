/*
 * 152. Maximum Product SubArray — Medium
 * https://leetcode.com/problems/maximum-product-subarray/
 *
 * Given an integer array nums, find a subarray that has the largest product, and return the product.
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 * Example 1:
 *   Input:  nums = [2,3,-2,4]
 *   Output: 6
 *   Explanation: [2,3] has the largest product 6.
 *
 * Example 2:
 *   Input:  nums = [-2,0,-1]
 *   Output: 0
 *   Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 * Constraints:
 *   1 <= nums.length <= 2 * 10^4
 *   -10 <= nums[i] <= 10
 *   The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * Approach: Carry both the max and min product ending at each index, since a negative flips them — O(n) time, O(1) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_152_Maximum_Product_SubArray {
    public int maxProduct(int[] nums) {
        
        int maxEnding = nums[0];
        int minEnding = nums[0];
        int answer = nums[0];

        for (int i=1; i<nums.length; i++) {
            int num = nums[i];

            int tempMax = Math.max( num, Math.max (maxEnding*num, minEnding*num) );
            int tempMin = Math.min( num, Math.min (maxEnding*num, minEnding*num) );

            maxEnding = tempMax;
            minEnding = tempMin;

            answer = Math.max (answer, maxEnding);
        }

        return answer;
    }

    public static void main(String[] args) {
        LT_152_Maximum_Product_SubArray sol = new LT_152_Maximum_Product_SubArray();
        System.out.println(sol.maxProduct(new int[]{2, 3, -2, 4})); // expected: 6
        System.out.println(sol.maxProduct(new int[]{-2, 0, -1})); // expected: 0
    }
}
