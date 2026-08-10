/*
 * 120. Triangle — Medium
 * https://leetcode.com/problems/triangle/
 *
 * Given a triangle array, return the minimum path sum from top to bottom.
 *
 * For each step, you may move to an adjacent number of the row below. More formally, if you are
 * on index i on the current row, you may move to either index i or index i + 1 on the next row.
 *
 * Example 1:
 *   Input:  triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 *   Output: 11
 *   Explanation: The triangle looks like:
 *                   2
 *                  3 4
 *                 6 5 7
 *                4 1 8 3
 *                The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11.
 *
 * Example 2:
 *   Input:  triangle = [[-10]]
 *   Output: -10
 *
 * Constraints:
 *   1 <= triangle.length <= 200
 *   triangle[0].length == 1
 *   triangle[i].length == triangle[i - 1].length + 1
 *   -10^4 <= triangle[i][j] <= 10^4
 *
 * Approach: Top-down memoization on (row, col) — cost(i,j) = triangle[i][j] + min(down, down-right),
 *           with the last row as the base case. Integer.MAX_VALUE is the unvisited sentinel because
 *           cells may be negative, so -1 would be ambiguous — O(n^2) time, O(n^2) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0120_Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] memo = new int[triangle.size()][triangle.size()];
        for (int[] row : memo) Arrays.fill(row, Integer.MAX_VALUE);
        return dfs(triangle, 0, 0, memo);
    }

    private int dfs(List<List<Integer>> triangle, int i, int j, int[][] memo) {
        if (i == triangle.size() - 1) return triangle.get(i).get(j);
        if (memo[i][j] != Integer.MAX_VALUE) return memo[i][j];

        int down = dfs(triangle, i + 1, j, memo);
        int diag = dfs(triangle, i + 1, j + 1, memo);

        memo[i][j] = triangle.get(i).get(j) + Math.min(down, diag);
        return memo[i][j];
    }

    public static void main(String[] args) {
        LT_0120_Triangle sol = new LT_0120_Triangle();
        System.out.println(sol.minimumTotal(
                List.of(List.of(2), List.of(3, 4), List.of(6, 5, 7), List.of(4, 1, 8, 3)))); // expected: 11
        System.out.println(sol.minimumTotal(List.of(List.of(-10)))); // expected: -10
    }

}
