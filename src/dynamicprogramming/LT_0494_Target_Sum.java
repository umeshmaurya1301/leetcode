/*
 * 494. Target Sum — Medium
 * https://leetcode.com/problems/target-sum/
 *
 * You are given an integer array nums and an integer target.
 *
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before
 * each integer in nums and then concatenate all the integers.
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate
 * them to build the expression "+2-1".
 * Return the number of different expressions that you can build, which evaluates to target.
 *
 * Example 1:
 *   Input:  nums = [1,1,1,1,1], target = 3
 *   Output: 5
 *   Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
 *                -1 + 1 + 1 + 1 + 1 = 3
 *                +1 - 1 + 1 + 1 + 1 = 3
 *                +1 + 1 - 1 + 1 + 1 = 3
 *                +1 + 1 + 1 - 1 + 1 = 3
 *                +1 + 1 + 1 + 1 - 1 = 3
 *
 * Example 2:
 *   Input:  nums = [1], target = 1
 *   Output: 1
 *
 * Constraints:
 *   1 <= nums.length <= 20
 *   0 <= nums[i] <= 1000
 *   0 <= sum(nums[i]) <= 1000
 *   -1000 <= target <= 1000
 *
 * Approach: Top-down memoization over (index, remaining target), summing the '+' and '-'
 *           branches. Remaining target goes negative, so the memo is a HashMap keyed on
 *           "idx,curr" rather than a 2D array — O(n * sum) time, O(n * sum) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0494_Target_Sum {
    public int findTargetSumWays(int[] nums, int target) {
        Map<String, Integer> dp = new HashMap<>();
        return dfs(nums, dp, 0, target);

    }

    private int dfs(int[] nums, Map<String, Integer> dp, int idx, int curr) {
        int len = nums.length;
        if (curr==0 && idx==len) return 1;
        if (idx>=nums.length) return 0;

        String key = idx + "," + curr;
        if(dp.containsKey(key)) return dp.get(key);

        int plusWays = dfs(nums, dp, idx+1, curr - nums[idx]);
        int minusWays = dfs(nums, dp, idx+1, curr + nums[idx]);
        int ways = plusWays + minusWays;
        dp.put(key, ways);
        return ways;
    }

    public static void main(String[] args) {
        LT_0494_Target_Sum sol = new LT_0494_Target_Sum();
        System.out.println(sol.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3)); // expected: 5
        System.out.println(sol.findTargetSumWays(new int[]{1}, 1)); // expected: 1
    }

}
