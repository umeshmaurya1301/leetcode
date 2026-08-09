/*
 * 474. Ones and Zeroes — Medium
 * https://leetcode.com/problems/ones-and-zeroes/
 *
 * You are given an array of binary strings strs and two integers m and n.
 *
 * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
 *
 * A set x is a subset of a set y if all elements of x are also elements of y.
 *
 * Example 1:
 *   Input:  strs = ["10","0001","111001","1","0"], m = 5, n = 3
 *   Output: 4
 *   Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4. Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}. {"111001"} is invalid because it contains 4 1's, more than the maximum of 3.
 *
 * Example 2:
 *   Input:  strs = ["10","0","1"], m = 1, n = 1
 *   Output: 2
 *   Explanation: The largest subset is {"0", "1"}, so the answer is 2.
 *
 * Constraints:
 *   1 <= strs.length <= 600
 *   1 <= strs[i].length <= 100
 *   strs[i] consists only of digits '0' and '1'
 *   1 <= m, n <= 100
 *
 * Approach: Memoized 0/1 knapsack on (index, zeros left, ones left) — O(L * m * n) time and space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0474_Ones_and_Zeroes {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] memo = new int[len][m + 1][n + 1];
        for (int[][] row : memo)
            for (int[] col : row)
                Arrays.fill(col, -1);
        return dfs(strs, 0, m, n, memo);
    }

    private int dfs(String[] strs, int idx, int zerosRemaining, int onesRemaining, int[][][] memo) {
        if (idx == strs.length) return 0;
        if (memo[idx][zerosRemaining][onesRemaining] != -1)
            return memo[idx][zerosRemaining][onesRemaining];

        int[] count = countZerosOnes(strs[idx]);
        int zeros = count[0], ones = count[1];

        int notTake = dfs(strs, idx + 1, zerosRemaining, onesRemaining, memo);

        int take = 0;
        if (zerosRemaining >= zeros && onesRemaining >= ones) {
            take = 1 + dfs(strs, idx + 1, zerosRemaining - zeros, onesRemaining - ones, memo);
        }

        return memo[idx][zerosRemaining][onesRemaining] = Math.max(take, notTake);
    }

    private int[] countZerosOnes(String s) {
        int zeros = 0, ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') zeros++;
            else ones++;
        }
        return new int[]{zeros, ones};
    }

    public static void main(String[] args) {
        LT_0474_Ones_and_Zeroes sol = new LT_0474_Ones_and_Zeroes();
        System.out.println(sol.findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3)); // expected: 4
        System.out.println(sol.findMaxForm(new String[]{"10", "0", "1"}, 1, 1)); // expected: 2
    }
}
