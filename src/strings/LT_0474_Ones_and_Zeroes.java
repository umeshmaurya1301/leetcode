// Ones and Zeroes — https://leetcode.com/problems/ones-and-zeroes/
package strings;

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
