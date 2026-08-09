/*
 * 5. Longest Palindromic Substring — Medium
 * https://leetcode.com/problems/longest-palindromic-substring/
 *
 * Given a string s, return the longest palindromic substring in s.
 *
 * Example 1:
 *   Input:  s = "babad"
 *   Output: "bab"
 *   Explanation: "aba" is also a valid answer.
 *
 * Example 2:
 *   Input:  s = "cbbd"
 *   Output: "bb"
 *
 * Constraints:
 *   1 <= s.length <= 1000
 *   s consists of only digits and English letters.
 *
 * Approach: Top-down memoization of isPalindrome(i, j) over every substring — O(n^2) time, O(n^2) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0005_Longest_Palindromic_Substring_1_TopDownMemoization {
    public String longestPalindrome(String s) {
        int n = s.length();
        int[][] memo = new int[n][n];

        int bestLen = 1, bestStart = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPal(s, i, j, memo) == 1 && (j - i + 1) > bestLen) {
                    bestLen = j - i + 1;
                    bestStart = i;
                }
            }
        }

        return s.substring(bestStart, bestStart + bestLen);
    }

    private int isPal(String s, int i, int j, int[][] memo) {
        if (i >= j) return 1;

        if (memo[i][j] != 0) return memo[i][j];

        if (s.charAt(i) == s.charAt(j) && isPal(s, i + 1, j - 1, memo) == 1) {
            memo[i][j] = 1;
        } else {
            memo[i][j] = -1;
        }

        return memo[i][j];
    }

    public static void main(String[] args) {
        LT_0005_Longest_Palindromic_Substring_1_TopDownMemoization sol = new LT_0005_Longest_Palindromic_Substring_1_TopDownMemoization();
        System.out.println(sol.longestPalindrome("babad")); // expected: "bab" (or "aba")
        System.out.println(sol.longestPalindrome("cbbd")); // expected: "bb"
    }
}
