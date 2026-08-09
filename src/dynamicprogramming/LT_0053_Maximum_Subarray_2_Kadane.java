/*
 * 53. Maximum Subarray — Medium
 * https://leetcode.com/problems/maximum-subarray/
 *
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 *
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the
 * divide and conquer approach, which is more subtle.
 *
 * Example 1:
 *   Input:  nums = [-2,1,-3,4,-1,2,1,-5,4]
 *   Output: 6
 *   Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 *
 * Example 2:
 *   Input:  nums = [1]
 *   Output: 1
 *   Explanation: The subarray [1] has the largest sum 1.
 *
 * Example 3:
 *   Input:  nums = [5,4,-1,7,8]
 *   Output: 23
 *   Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 *
 * Constraints:
 *   1 <= nums.length <= 10^5
 *   -10^4 <= nums[i] <= 10^4
 *
 * Approach 2: Kadane — keep a running sum, record the answer, then drop the prefix whenever it
 *             turns negative. Recording before the reset is what keeps all-negative arrays
 *             correct — O(n) time, O(1) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0053_Maximum_Subarray_2_Kadane {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int n : nums) {
            sum += n;
            max = Math.max(sum, max);
            if(sum<0) {
                sum=0;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LT_0053_Maximum_Subarray_2_Kadane sol = new LT_0053_Maximum_Subarray_2_Kadane();
        System.out.println(sol.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // expected: 6
        System.out.println(sol.maxSubArray(new int[]{1})); // expected: 1
        System.out.println(sol.maxSubArray(new int[]{5, 4, -1, 7, 8})); // expected: 23
    }

}
