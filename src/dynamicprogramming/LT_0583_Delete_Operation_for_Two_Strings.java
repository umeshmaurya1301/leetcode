/*
 * 583. Delete Operation for Two Strings — Medium
 * https://leetcode.com/problems/delete-operation-for-two-strings/
 *
 * Given two strings word1 and word2, return the minimum number of steps required to make word1 and
 * word2 the same.
 *
 * In one step, you can delete exactly one character in either string.
 *
 * Example 1:
 *   Input:  word1 = "sea", word2 = "eat"
 *   Output: 2
 *   Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 *
 * Example 2:
 *   Input:  word1 = "leetcode", word2 = "etco"
 *   Output: 4
 *
 * Constraints:
 *   1 <= word1.length, word2.length <= 500
 *   word1 and word2 consist of only lowercase English letters.
 *
 * Approach: Deletion is the only operation, so whatever survives must be a common subsequence and
 *           minimising deletions is the same as maximising it. Compute LCS by memoization, then
 *           answer = len1 + len2 - 2*lcs, since every kept character is kept once in each string —
 *           O(m*n) time, O(m*n) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0583_Delete_Operation_for_Two_Strings {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1][len2];
        for (int[] a : dp)
            Arrays.fill(a, -1);
        int lcs = helper(word1, word2, 0, 0, dp);
        return len1 + len2 - 2 * lcs;
    }

    private int helper(String w1, String w2, int i, int j, int[][] dp) {
        int len1 = w1.length();
        int len2 = w2.length();
        if (i == len1 || j == len2)
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        int val = 0;
        if (w1.charAt(i) == w2.charAt(j)) {
            val = 1 + helper(w1, w2, i + 1, j + 1, dp);
        } else {
            val = helper(w1, w2, i, j + 1, dp);
            val = Math.max(val, helper(w1, w2, i + 1, j, dp));
        }

        return dp[i][j] = val;
    }

    public static void main(String[] args) {
        LT_0583_Delete_Operation_for_Two_Strings sol = new LT_0583_Delete_Operation_for_Two_Strings();
        System.out.println(sol.minDistance("sea", "eat")); // expected: 2
        System.out.println(sol.minDistance("leetcode", "etco")); // expected: 4
    }

}
