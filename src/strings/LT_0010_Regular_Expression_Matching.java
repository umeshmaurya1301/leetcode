/*
 * 10. Regular Expression Matching — Hard
 * https://leetcode.com/problems/regular-expression-matching/
 *
 * Given an input string s and a pattern p, implement regular expression matching with support for
 * '.' and '*' where:
 *   '.' Matches any single character.
 *   '*' Matches zero or more of the preceding element.
 * Return a boolean indicating whether the matching covers the entire input string (not partial).
 *
 * Example 1:
 *   Input:  s = "aa", p = "a"
 *   Output: false
 *   Explanation: "a" does not match the entire string "aa".
 *
 * Example 2:
 *   Input:  s = "aa", p = "a*"
 *   Output: true
 *   Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a'
 *                once, it becomes "aa".
 *
 * Example 3:
 *   Input:  s = "ab", p = ".*"
 *   Output: true
 *   Explanation: ".*" means "zero or more (*) of any character (.)".
 *
 * Constraints:
 *   1 <= s.length <= 20
 *   1 <= p.length <= 20
 *   s contains only lowercase English letters.
 *   p contains only lowercase English letters, '.', and '*'.
 *   It is guaranteed for each appearance of the character '*', there will be a previous valid
 *   character to match.
 *
 * Approach: Memoized recursion on (i, j) that reads the pattern as tokens rather than characters —
 *           a one-slot lookahead at p[j+1] decides between a bare token (consume one character on
 *           each side) and a starred group, which forks into "use it zero times" (j+2, i unchanged)
 *           OR "use it once more" (i+1, j unchanged, so the group stays available). Only j has a
 *           base case; s running out is handled by firstMatch going false — O(m*n) time,
 *           O(m*n) space.
 */
package strings;

import java.util.*;

class LT_0010_Regular_Expression_Matching {
    private Boolean[][] memo;

    public boolean isMatch(String s, String p) {
        memo = new Boolean[s.length() + 1][p.length() + 1];
        return dp(0, 0, s, p);
    }

    private boolean dp(int i, int j, String s, String p) {
        // return cached result
        if (memo[i][j] != null) return memo[i][j];

        boolean ans;

        if (j == p.length()) {
            ans = (i == s.length());
        } else {
            boolean firstMatch = (i < s.length() &&
                    (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));

            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                // case 1: skip "char*"
                // case 2: consume one character if match
                ans = dp(i, j + 2, s, p) || (firstMatch && dp(i + 1, j, s, p));
            } else {
                ans = firstMatch && dp(i + 1, j + 1, s, p);
            }
        }

        memo[i][j] = ans;
        return ans;
    }

    public static void main(String[] args) {
        LT_0010_Regular_Expression_Matching sol = new LT_0010_Regular_Expression_Matching();
        System.out.println(sol.isMatch("aa", "a")); // expected: false
        System.out.println(sol.isMatch("aa", "a*")); // expected: true
        System.out.println(sol.isMatch("ab", ".*")); // expected: true
    }

}
