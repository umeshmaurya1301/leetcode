/*
 * 91. Decode Ways — Medium
 * https://leetcode.com/problems/decode-ways/
 *
 * You have intercepted a secret message encoded as a string of numbers. The message
 * is decoded via the following mapping:
 *   "1" -> 'A', "2" -> 'B', ... , "25" -> 'Y', "26" -> 'Z'
 * There are many different ways to decode the message because some codes are
 * contained in other codes ("2" and "5" vs "25"). For example, "11106" can be
 * decoded into "AAJF" with the grouping (1, 1, 10, 6) or "KJF" with the grouping
 * (11, 10, 6); the grouping (1, 11, 06) is invalid because "06" is not a valid code.
 * Given a string s containing only digits, return the number of ways to decode it.
 * If the entire string cannot be decoded in any valid way, return 0.
 * The test cases are generated so that the answer fits in a 32-bit integer.
 *
 * Example 1:
 *   Input:  s = "12"
 *   Output: 2
 *   Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 *
 * Example 2:
 *   Input:  s = "226"
 *   Output: 3
 *   Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 * Example 3:
 *   Input:  s = "06"
 *   Output: 0
 *   Explanation: "06" cannot be mapped to "F" because of the leading zero
 *                ("6" is different from "06"), so the string is not a valid encoding.
 *
 * Constraints:
 *   1 <= s.length <= 100
 *   s contains only digits and may contain leading zero(s).
 *
 * Approach: Top-down DP — memoized DFS on the suffix index. dfs(idx) counts the
 * decodings of s[idx..]: take one digit, plus two digits when they read 10..26.
 * A leading '0' kills the suffix; reaching the end counts as one decoding.
 * O(n) time, O(n) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0091_Decode_Ways {

    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n];

        Arrays.fill(dp, -1);

        return dfs(0, s, dp);
    }

    private int dfs(int idx, String s, int[] dp) {

        if (idx == s.length()) {
            return 1;
        }

        if (s.charAt(idx) == '0') {
            return 0;
        }

        if (dp[idx] != -1) {
            return dp[idx];
        }

        int ways = dfs(idx + 1, s, dp);

        if (idx + 1 < s.length()) {

            int num =
                (s.charAt(idx) - '0') * 10 +
                (s.charAt(idx + 1) - '0');

            if (num >= 10 && num <= 26) {
                ways += dfs(idx + 2, s, dp);
            }
        }

        return dp[idx] = ways;
    }

    public static void main(String[] args) {
        LT_0091_Decode_Ways sol = new LT_0091_Decode_Ways();

        // Example 1
        System.out.println(sol.numDecodings("12"));    // expected: 2

        // Example 2
        System.out.println(sol.numDecodings("226"));   // expected: 3

        // Example 3
        System.out.println(sol.numDecodings("06"));    // expected: 0

        // extra: the grouping example from the statement
        System.out.println(sol.numDecodings("11106")); // expected: 2
    }
}
