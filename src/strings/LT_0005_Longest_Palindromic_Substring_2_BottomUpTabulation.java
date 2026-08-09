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
 * Approach: Bottom-up tabulation by increasing substring length — O(n^2) time, O(n^2) space.
 */
package strings;

import java.util.*;

class LT_0005_Longest_Palindromic_Substring_2_BottomUpTabulation {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n <= 1) return s;

        boolean[][] dp = new boolean[n][n];
        int bestLen = 1, start = 0;

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                bestLen = 2;
            }
        }

        for (int len = 3; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;

                    if (len > bestLen) {
                        bestLen = len;
                        start = i;
                    }
                }
            }
        }

        return s.substring(start, start + bestLen);
    }

    public static void main(String[] args) {
        LT_0005_Longest_Palindromic_Substring_2_BottomUpTabulation sol = new LT_0005_Longest_Palindromic_Substring_2_BottomUpTabulation();
        System.out.println(sol.longestPalindrome("babad")); // expected: "bab" (or "aba")
        System.out.println(sol.longestPalindrome("cbbd")); // expected: "bb"
    }
}
