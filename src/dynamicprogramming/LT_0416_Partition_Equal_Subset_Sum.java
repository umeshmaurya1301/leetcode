/*
 * 416. Partition Equal Subset Sum — Medium
 * https://leetcode.com/problems/partition-equal-subset-sum/
 *
 * Given an integer array nums, return true if you can partition the array into two subsets
 * such that the sum of the elements in both subsets is equal or false otherwise.
 *
 * Example 1:
 *   Input:  nums = [1,5,11,5]
 *   Output: true
 *   Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 * Example 2:
 *   Input:  nums = [1,2,3,5]
 *   Output: false
 *   Explanation: The array cannot be partitioned into equal sum subsets.
 *
 * Constraints:
 *   1 <= nums.length <= 200
 *   1 <= nums[i] <= 100
 *
 * Approach: Reduce to subset-sum for target = sum/2 (odd sum is an immediate false), then
 *           0/1 knapsack take/skip with a Boolean[][] memo where null means unvisited —
 *           O(n * sum) time, O(n * sum) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0416_Partition_Equal_Subset_Sum {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum%2!=0) return false;
        int target = sum/2;
        Boolean[][] dp = new Boolean[nums.length][target+1];

        boolean ans = dfs(nums, dp, 0, target);
        // System.out.println(Arrays.deepToString(dp));
        return dp[0][target];
        // return (boolean)dp[0][target];
    }

    private boolean dfs(int[] nums, Boolean[][] dp, int idx, int target) {
        if (target==0) return true;
        if (idx >= nums.length || target<0) return false;
        if (dp[idx][target]!=null) return dp[idx][target];

        boolean take = dfs(nums, dp, idx+1, target-nums[idx]);
        boolean notTake = dfs(nums, dp, idx+1, target);
        return dp[idx][target] = take || notTake;
    }

    public static void main(String[] args) {
        LT_0416_Partition_Equal_Subset_Sum sol = new LT_0416_Partition_Equal_Subset_Sum();
        System.out.println(sol.canPartition(new int[]{1, 5, 11, 5})); // expected: true
        System.out.println(sol.canPartition(new int[]{1, 2, 3, 5})); // expected: false
    }

}
