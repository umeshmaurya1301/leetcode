/*
 * 712. Minimum ASCII Delete Sum for Two Strings — Medium
 * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
 *
 * Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two
 * strings equal.
 *
 * Example 1:
 *   Input:  s1 = "sea", s2 = "eat"
 *   Output: 231
 *   Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum. Deleting "t"
 *                from "eat" adds 116 to the sum. At the end, both strings are equal, and
 *                115 + 116 = 231 is the minimum sum possible to achieve this.
 *
 * Example 2:
 *   Input:  s1 = "delete", s2 = "leet"
 *   Output: 403
 *   Explanation: Deleting "dee" from "delete" to turn the string into "let" adds
 *                100[d] + 101[e] + 101[e] to the sum. Deleting "e" from "leet" adds 101[e].
 *                At the end, both strings are equal to "let", and the answer is
 *                100+101+101+101 = 403. If instead we turned both strings into "lee" or "eet",
 *                we would get answers of 433 or 417, which are higher.
 *
 * Constraints:
 *   1 <= s1.length, s2.length <= 1000
 *   s1 and s2 consist of lowercase English letters.
 *
 * Approach: Weighted LCS — maximise the ASCII weight of the retained common subsequence rather than
 *           its length, since a shorter heavy subsequence can beat a longer light one (see the
 *           "let" vs "lee" vs "eet" tie in Example 2). Then the answer is the combined ASCII total
 *           of both strings minus twice that weight, because each retained character is kept once
 *           in each string — O(m*n) time, O(m*n) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0712_Minimum_ASCII_Delete_Sum_for_Two_Strings {
    public int minimumDeleteSum(String s1, String s2) {

        int len1 = s1.length();
        int len2 = s2.length();

        int[][] dp = new int[len1][len2];
        for(int[] a: dp) Arrays.fill(a, -1);
        int valToSubstract = helper(s1, s2, 0, 0 , dp);

        int ascciSum = 0;
        for(int i=0; i<len1; i++) {
            ascciSum += s1.charAt(i);
        }
        for(int i=0; i<len2; i++) {
            ascciSum += s2.charAt(i);
        }

        return ascciSum - 2*valToSubstract;
    }

    private int helper(String s1, String s2, int i, int j, int[][] dp) {
        int len1 = s1.length();
        int len2 = s2.length();

        if (i == len1 || j == len2)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];

        int val = 0;

        if (s1.charAt(i) == s2.charAt(j)) {
            val = s1.charAt(i) + helper(s1, s2, i + 1, j + 1, dp);
        } else {
            val = helper(s1, s2, i, j + 1, dp);
            val = Math.max(val, helper(s1, s2, i + 1, j, dp));
        }

        return dp[i][j] = val;

    }

    public static void main(String[] args) {
        LT_0712_Minimum_ASCII_Delete_Sum_for_Two_Strings sol =
                new LT_0712_Minimum_ASCII_Delete_Sum_for_Two_Strings();
        System.out.println(sol.minimumDeleteSum("sea", "eat")); // expected: 231
        System.out.println(sol.minimumDeleteSum("delete", "leet")); // expected: 403
    }

}
