/*
 * 740. Delete And Earn — Medium
 * https://leetcode.com/problems/delete-and-earn/
 *
 * You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:
 *
 * - Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
 *
 * Return the maximum number of points you can earn by applying the above operation some number of times.
 *
 * Example 1:
 *   Input:  nums = [3,4,2]
 *   Output: 6
 *   Explanation: Delete 4 to earn 4 points (3 is also deleted). Then delete 2 to earn 2 points. Total = 6.
 *
 * Example 2:
 *   Input:  nums = [2,2,3,3,3,4]
 *   Output: 9
 *   Explanation: Delete a 3 to earn 3 points — all 2s and 4s are deleted. Repeat for the remaining 3s. Total = 9.
 *
 * Constraints:
 *   1 <= nums.length <= 2 * 10^4
 *   1 <= nums[i] <= 10^4
 *
 * Approach: Bucket points by value, then house-robber memoization over the value line — O(n + maxValue) time and space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0740_Delete_And_Earn {
    public int deleteAndEarn(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        // Step 1: Prepare points array
        int[] points = new int[max + 1];
        for (int num : nums) {
            points[num] += num;
        }

        // Step 2: Memoization array
        int[] memo = new int[max + 1];
        Arrays.fill(memo, -1);

        return helper(0, points, memo);
    }

    private int helper(int i, int[] points, int[] memo) {
        int len = memo.length;
        if (i == len-1) return points[len-1];
        if (i >= len) return 0;
        if (memo[i] != -1) return memo[i];

        // Either take i or skip it
        int take = helper(i + 2, points, memo) + points[i];
        int skip = helper(i + 1, points, memo);

        memo[i] = Math.max(take, skip);
        return memo[i];
    }

    public static void main(String[] args) {
        LT_0740_Delete_And_Earn sol = new LT_0740_Delete_And_Earn();
        System.out.println(sol.deleteAndEarn(new int[]{3, 4, 2})); // expected: 6
        System.out.println(sol.deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4})); // expected: 9
    }
}
