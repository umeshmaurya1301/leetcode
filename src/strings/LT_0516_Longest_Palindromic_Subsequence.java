/*
 * 516. Longest Palindromic Subsequence — Medium
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 *
 * Given a string s, find the longest palindromic subsequence's length in s.
 *
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no
 * elements without changing the order of the remaining elements.
 *
 * Example 1:
 *   Input:  s = "bbbab"
 *   Output: 4
 *   Explanation: One possible longest palindromic subsequence is "bbbb".
 *
 * Example 2:
 *   Input:  s = "cbbd"
 *   Output: 2
 *   Explanation: One possible longest palindromic subsequence is "bb".
 *
 * Constraints:
 *   1 <= s.length <= 1000
 *   s consists only of lowercase English letters.
 *
 * Approach: Interval DP memoized on (i, j). Matching ends wrap whatever is inside, so they are
 *           taken unconditionally: 2 + f(i+1, j-1). Otherwise shrink from either side and take the
 *           max. Two base cases carry the parity: i == j gives 1 (odd centre), i > j gives 0
 *           (even centre / empty window) — O(n^2) time, O(n^2) space.
 */
package strings;

import java.util.*;

class LT_0516_Longest_Palindromic_Subsequence {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int[] a : dp) Arrays.fill(a, -1);
        return helper(s, 0, n-1, dp);
    }

    private int helper(String s, int i, int j, int[][] dp) {
        if(i>j) return 0;
        if(i==j) return 1;
        if(dp[i][j]!=-1) return dp[i][j];

        if(s.charAt(i)==s.charAt(j)) {
            return dp[i][j] = 2 + helper(s, i+1, j-1, dp);
        } else {
            return dp[i][j] = Math.max(helper(s, i+1, j, dp), helper(s, i, j-1, dp));
        }
    }

    public static void main(String[] args) {
        LT_0516_Longest_Palindromic_Subsequence sol = new LT_0516_Longest_Palindromic_Subsequence();
        System.out.println(sol.longestPalindromeSubseq("bbbab")); // expected: 4
        System.out.println(sol.longestPalindromeSubseq("cbbd")); // expected: 2
    }

}
