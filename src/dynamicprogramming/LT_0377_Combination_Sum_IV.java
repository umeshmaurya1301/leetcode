/*
 * 377. Combination Sum IV — Medium
 * https://leetcode.com/problems/combination-sum-iv/description/
 *
 * Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.
 *
 * The test cases are generated so that the answer can fit in a 32-bit integer.
 *
 * Note: Different sequences are counted as different combinations — order matters.
 *
 * Example 1:
 *   Input:  nums = [1,2,3], target = 4
 *   Output: 7
 *   Explanation:
 *   (1,1,1,1) | (1,1,2) | (1,2,1) | (1,3) | (2,1,1) | (2,2) | (3,1)
 *
 * Example 2:
 *   Input:  nums = [9], target = 3
 *   Output: 0
 *
 * Constraints:
 *   1 <= nums.length <= 200
 *   1 <= nums[i] <= 1000
 *   All the elements of nums are unique.
 *   1 <= target <= 1000
 *
 * Approach: Memoized recursion on the remaining target, summing over every number (order matters) — O(target * n) time, O(target) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0377_Combination_Sum_IV {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        Arrays.fill(dp, -1);
        dfs(nums, dp, target);
        return dp[target];
    }

    private int dfs(int[] nums, int[] dp, int target) {
        if (target==0) return 1;
        if (target < 0) return 0;
        if (dp[target]!=-1) return dp[target];
        
        int ways = 0;
        for (int num : nums) {
            ways += dfs(nums, dp, target-num);
        }
        
        return dp[target] = ways;
    }

    public static void main(String[] args) {
        LT_0377_Combination_Sum_IV sol = new LT_0377_Combination_Sum_IV();
        System.out.println(sol.combinationSum4(new int[]{1, 2, 3}, 4)); // expected: 7
        System.out.println(sol.combinationSum4(new int[]{9}, 3)); // expected: 0
    }
}
