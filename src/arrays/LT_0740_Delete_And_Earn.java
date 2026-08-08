// Delete And Earn — https://leetcode.com/problems/delete-and-earn/
package arrays;

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
