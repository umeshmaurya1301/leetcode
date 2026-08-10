/*
 * 115. Distinct Subsequences — Hard
 * https://leetcode.com/problems/distinct-subsequences/
 *
 * Given two strings s and t, return the number of distinct subsequences of s which equals t.
 *
 * The test cases are generated so that the answer fits on a 32-bit signed integer.
 *
 * Example 1:
 *   Input:  s = "rabbbit", t = "rabbit"
 *   Output: 3
 *   Explanation: There are 3 ways to generate "rabbit" from "rabbbit" — each one drops a
 *                different 'b' from the run of three.
 *
 * Example 2:
 *   Input:  s = "babgbag", t = "bag"
 *   Output: 5
 *   Explanation: There are 5 ways to pick the characters b, a, g in order out of "babgbag".
 *
 * Constraints:
 *   1 <= s.length, t.length <= 1000
 *   s and t consist of English letters.
 *
 * Approach: The LCS lattice with the combiner swapped from max to sum. f(i,j) counts the ways to
 *           build t[j..] out of s[i..]: on a match both "use s[i]" and "skip s[i]" produce distinct
 *           subsequences, so they are added; on a mismatch only the skip survives. Exhausting t
 *           returns 1 (one complete match) and must be checked before exhausting s, which returns 0
 *           — O(m*n) time, O(m*n) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0115_Distinct_Subsequences {
    public int numDistinct(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();

        int[][] dp = new int[len1][len2];
        for (int[] a : dp) Arrays.fill(a, -1);
        dfs(s,t,dp,0,0);
        return dp[0][0];
    }

    private int dfs(String s, String t, int[][] dp, int i, int j) {
        int len1 = s.length();
        int len2 = t.length();

        if (j==len2) return 1;
        if (i==len1) return 0;

        if (dp[i][j]!=-1) return dp[i][j];

        int ways = 0;
        if (s.charAt(i) == t.charAt(j)) {
            ways += dfs (s, t, dp, i+1, j+1);
            ways += dfs (s, t, dp, i+1, j);
        } else {
            ways = dfs (s, t, dp, i+1, j);
        }

        return dp[i][j] = ways;
    }

    public static void main(String[] args) {
        LT_0115_Distinct_Subsequences sol = new LT_0115_Distinct_Subsequences();
        System.out.println(sol.numDistinct("rabbbit", "rabbit")); // expected: 3
        System.out.println(sol.numDistinct("babgbag", "bag")); // expected: 5
    }

}
