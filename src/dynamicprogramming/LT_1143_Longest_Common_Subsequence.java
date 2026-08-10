/*
 * 1143. Longest Common Subsequence — Medium
 * https://leetcode.com/problems/longest-common-subsequence/
 *
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters
 * (can be none) deleted without changing the relative order of the remaining characters. For
 * example, "ace" is a subsequence of "abcde". A common subsequence of two strings is a subsequence
 * that is common to both strings.
 *
 * Example 1:
 *   Input:  text1 = "abcde", text2 = "ace"
 *   Output: 3
 *   Explanation: The longest common subsequence is "ace" and its length is 3.
 *
 * Example 2:
 *   Input:  text1 = "abc", text2 = "abc"
 *   Output: 3
 *   Explanation: The longest common subsequence is "abc" and its length is 3.
 *
 * Example 3:
 *   Input:  text1 = "abc", text2 = "def"
 *   Output: 0
 *   Explanation: There is no such common subsequence, so the result is 0.
 *
 * Constraints:
 *   1 <= text1.length, text2.length <= 1000
 *   text1 and text2 consist of only lowercase English characters.
 *
 * Approach: Top-down memoization on the suffix pair (i, j). Matching characters are always worth
 *           taking (exchange argument), so that branch is unconditional: 1 + f(i+1, j+1). On a
 *           mismatch, drop one character from either side and take the max — O(m*n) time,
 *           O(m*n) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_1143_Longest_Common_Subsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();

        int[][] dp = new int[len1][len2];
        for(int[] a: dp) Arrays.fill(a, -1);
        return helper(text1, text2, 0, 0 , dp);
    }

    private int helper(String s1, String s2, int i, int j, int[][] dp) {
        int len1 = s1.length();
        int len2 = s2.length();

        if(i==len1 || j==len2) return 0;
        if(dp[i][j]!=-1) return dp[i][j];

        int val = 0;

        if(s1.charAt(i)==s2.charAt(j)) {
            val = 1 + helper(s1, s2, i+1, j+1, dp);
        } else {
            val = helper(s1, s2, i, j+1, dp);
            val = Math.max(val, helper(s1, s2, i+1, j, dp));
        }

        return dp[i][j] = val;

    }

    public static void main(String[] args) {
        LT_1143_Longest_Common_Subsequence sol = new LT_1143_Longest_Common_Subsequence();
        System.out.println(sol.longestCommonSubsequence("abcde", "ace")); // expected: 3
        System.out.println(sol.longestCommonSubsequence("abc", "abc")); // expected: 3
        System.out.println(sol.longestCommonSubsequence("abc", "def")); // expected: 0
    }

}
