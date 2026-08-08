// Longest Palindromic Substring — https://leetcode.com/problems/longest-palindromic-substring/
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
