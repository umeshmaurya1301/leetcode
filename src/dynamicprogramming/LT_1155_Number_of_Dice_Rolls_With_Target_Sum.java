/*
 * 1155. Number of Dice Rolls With Target Sum — Medium
 * https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
 *
 * You have n dice, and each dice has k faces numbered from 1 to k.
 *
 * Given three integers n, k, and target, return the number of possible ways (out of the k^n
 * total ways) to roll the dice, so the sum of the face-up numbers equals target.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 *   Input:  n = 1, k = 6, target = 3
 *   Output: 1
 *   Explanation: You throw one die with 6 faces. There is only one way to get a sum of 3.
 *
 * Example 2:
 *   Input:  n = 2, k = 6, target = 7
 *   Output: 6
 *   Explanation: You throw two dice, each with 6 faces. There are 6 ways to get a sum of 7:
 *                1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
 *
 * Example 3:
 *   Input:  n = 30, k = 30, target = 500
 *   Output: 222616187
 *   Explanation: The answer must be returned modulo 10^9 + 7.
 *
 * Constraints:
 *   1 <= n, k <= 30
 *   1 <= target <= 1000
 *
 * Approach: Top-down memoization over (dice index, remaining target); exactly n dice must be
 *           used, so a leaf counts only when idx == n and remain == 0 — O(n * target * k) time,
 *           O(n * target) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_1155_Number_of_Dice_Rolls_With_Target_Sum {
    private static final int MOD = 1000000007;

    public int numRollsToTarget(int n, int k, int target) {
        int[][] dp = new int[n][target+1];
        for (int[] a : dp) Arrays.fill(a, -1);
        dfs(k, dp, 0, target);
        System.out.println(Arrays.deepToString(dp));
        return dp[0][target];
    }

    private int dfs (int k, int[][] dp, int idx, int remain) {

        if (idx==dp.length && remain != 0) return 0;
        if (idx==dp.length && remain == 0) return 1;
        if (dp[idx][remain] != -1) return dp[idx][remain];

        int ways = 0;

        for (int i=1; i<=k; i++) {
            int newRemain = remain - i;
            // System.out.println("Calling for newRemain: "+newRemain);
            if (newRemain >= 0) {
                int tempWays = (dfs (k, dp, idx + 1, newRemain))%MOD;
                ways += tempWays;
                ways %= MOD;
            }
        }

        return dp[idx][remain] = ways;
    }

    public static void main(String[] args) {
        LT_1155_Number_of_Dice_Rolls_With_Target_Sum sol = new LT_1155_Number_of_Dice_Rolls_With_Target_Sum();
        System.out.println(sol.numRollsToTarget(1, 6, 3)); // expected: 1
        System.out.println(sol.numRollsToTarget(2, 6, 7)); // expected: 6
        System.out.println(sol.numRollsToTarget(30, 30, 500)); // expected: 222616187
    }

}
