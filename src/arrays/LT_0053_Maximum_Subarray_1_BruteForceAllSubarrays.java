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
 * Approach 1: Brute force over every (i, j) pair, re-summing each range — O(n^3) time,
 *             O(n^2) space. TLE/MLE on the real constraints: the dp table is written but
 *             never read, since each (i, j) is visited exactly once. Kept for contrast with
 *             approach 2 (Kadane).
 */
package arrays;

import java.util.*;

class LT_0053_Maximum_Subarray_1_BruteForceAllSubarrays {

    private static final int MAX = Integer.MAX_VALUE;

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int[] a : dp) Arrays.fill(a, MAX);
        int max = Integer.MIN_VALUE;

        for (int i=0; i<n; i++) {
            for (int j=i; j<n; j++) {
                int subArrSum = dfs (i, j, nums, dp);
                if (subArrSum > max) {
                    max = subArrSum;
                }
            }
        }

        return max;
    }

    private int dfs (int i, int j, int[] nums, int[][] dp) {
        if (dp[i][j] != MAX) return dp[i][j];
        int sum = 0;
        for (int idx = i; idx<=j; idx++) sum += nums[idx];
        return dp[i][j] = sum;
    }

    public static void main(String[] args) {
        LT_0053_Maximum_Subarray_1_BruteForceAllSubarrays sol = new LT_0053_Maximum_Subarray_1_BruteForceAllSubarrays();
        System.out.println(sol.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // expected: 6
        System.out.println(sol.maxSubArray(new int[]{1})); // expected: 1
        System.out.println(sol.maxSubArray(new int[]{5, 4, -1, 7, 8})); // expected: 23
    }

}
