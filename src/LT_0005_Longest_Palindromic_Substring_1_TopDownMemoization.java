// Longest Palindromic Substring — https://leetcode.com/problems/longest-palindromic-substring/
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
}
