/*
 * 221. Maximal Square — Medium
 * https://leetcode.com/problems/maximal-square/
 *
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only
 * 1's and return its area.
 *
 * Example 1:
 *   Input:  matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 *   Output: 4
 *   Explanation: The largest all-1 square is the 2 x 2 block at rows 1-2, columns 2-3.
 *
 * Example 2:
 *   Input:  matrix = [["0","1"],["1","0"]]
 *   Output: 1
 *
 * Example 3:
 *   Input:  matrix = [["0"]]
 *   Output: 0
 *
 * Constraints:
 *   m == matrix.length
 *   n == matrix[i].length
 *   1 <= m, n <= 300
 *   matrix[i][j] is '0' or '1'.
 *
 * Approach: Memoized grid DP where f(r,c) is the side of the largest all-1 square whose top-left
 *           corner is (r,c): f(r,c) = 1 + min(right, down-right, down) for a '1' cell, 0 for a '0'
 *           cell, and 0 off-grid. Answer is max(side)^2 — O(m*n) time, O(m*n) space.
 */
package arrays;

import java.util.*;

class LT_0221_Maximal_Square {
    private static final int[][] DIR = new int[][]{{0,1},{1,1},{1,0} };

    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] memo = new int[m][n];
        for (int[] a : memo) Arrays.fill(a, -1);

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                function(matrix, memo, i, j);
            }
        }

        int max = 0;
        System.out.println(Arrays.deepToString(memo));
        for (int[] a : memo) {
            for (int d : a) {
                max = Math.max (max, d*d);
            }
        }

        return max;
    }

    private int function (char[][] matrix, int[][] memo, int row, int col) {
        if (row>=matrix.length || col>=matrix[0].length) return 0;
        if (matrix[row][col]=='0') return memo[row][col] = 0;
        if (memo[row][col] != -1) return memo[row][col];

        int min = Integer.MAX_VALUE;
        for (int[] dir : DIR) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            min = Math.min (min, function(matrix, memo, newRow, newCol));
        }
        return memo[row][col] = min==Integer.MAX_VALUE ? 0 : min + 1;
    }

    public static void main(String[] args) {
        LT_0221_Maximal_Square sol = new LT_0221_Maximal_Square();
        System.out.println(sol.maximalSquare(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}})); // expected: 4
        System.out.println(sol.maximalSquare(new char[][]{{'0', '1'}, {'1', '0'}})); // expected: 1
        System.out.println(sol.maximalSquare(new char[][]{{'0'}})); // expected: 0
    }

}
