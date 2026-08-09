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
 * Approach: Expand around each of the 2n-1 centers — O(n^2) time, O(1) space.
 */
package strings;

import java.util.*;

class LT_0005_Longest_Palindromic_Substring_3_ExpandAroundCenter {
    public String longestPalindrome(String s) {
        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {

            int len1 = expand(s, i, i);       // Odd palindrome
            int len2 = expand(s, i, i + 1);   // Even palindrome

            int len = Math.max(len1, len2);

            if (len > end - start + 1) {

                start = i - (len - 1) / 2;
                end   = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expand(String s, int left, int right) {

        while (left >= 0 &&
               right < s.length() &&
               s.charAt(left) == s.charAt(right)) {

            left--;
            right++;
        }

        return right - left - 1;
    }

    public static void main(String[] args) {
        LT_0005_Longest_Palindromic_Substring_3_ExpandAroundCenter sol = new LT_0005_Longest_Palindromic_Substring_3_ExpandAroundCenter();
        System.out.println(sol.longestPalindrome("babad")); // expected: "bab" (or "aba")
        System.out.println(sol.longestPalindrome("cbbd")); // expected: "bb"
    }
}
