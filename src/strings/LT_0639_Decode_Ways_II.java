/*
 * 639. Decode Ways II — Hard
 * https://leetcode.com/problems/decode-ways-ii/
 *
 * A message containing letters from A-Z can be encoded into numbers using the mapping
 * 'A' -> "1", 'B' -> "2", ... 'Z' -> "26".
 *
 * To decode an encoded message, all the digits must be grouped then mapped back into
 * letters using the reverse of the mapping above (there may be multiple ways). For example,
 * "11106" can be mapped into "AAJF" with the grouping (1 1 10 6) or "KJF" with the grouping
 * (11 10 6). Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into
 * 'F' — "6" is different from "06".
 *
 * In addition, an encoded message may contain the '*' character, which can represent any
 * digit from '1' to '9' ('0' is excluded). For example, "1*" may represent any of "11",
 * "12", ..., "19". Decoding "1*" is equivalent to decoding any of the messages it represents.
 *
 * Given a string s consisting of digits and '*' characters, return the number of ways to
 * decode it, modulo 10^9 + 7.
 *
 * Example 1:
 *   Input:  s = "*"
 *   Output: 9
 *   Explanation: * can be any of "1"-"9", decoding to "A"-"I". Total = 9.
 *
 * Example 2:
 *   Input:  s = "1*"
 *   Output: 18
 *   Explanation: "1*" represents "11"-"19", and each of those has 2 decodings
 *                (e.g. "11" -> "AA" or "K"). Total = 9 * 2 = 18.
 *
 * Example 3:
 *   Input:  s = "2*"
 *   Output: 15
 *   Explanation: "2*" represents "21"-"29". "21"-"26" have 2 decodings each,
 *                "27"-"29" have only 1. Total = (6 * 2) + (3 * 1) = 15.
 *
 * Constraints:
 *   1 <= s.length <= 10^5
 *   s[i] is a digit or '*'
 *
 * Approach: Bottom-up DP with counted transitions. Decode Ways I uses boolean gates
 * (dp[i] = dp[i-1] + dp[i-2] when the last one/two chars are legal); '*' turns those gates
 * into counts, so dp[i] = dp[i-1]*single(c) + dp[i-2]*pair(c1,c2). Two rolling longs replace
 * the array, which also avoids the O(n)-deep recursion a top-down version would need at the
 * 10^5 length bound. O(n) time, O(1) space.
 */
package strings;

import java.util.*;

class LT_0639_Decode_Ways_II {
    private static final int MOD = 1_000_000_007;

    public int numDecodings(String s) {
        int n = s.length();
        long prev2 = 1;                    // dp[0] — empty prefix decodes one way
        long prev1 = single(s.charAt(0));  // dp[1]

        for (int i = 2; i <= n; i++) {
            long cur = (prev1 * single(s.charAt(i - 1))
                      + prev2 * pair(s.charAt(i - 2), s.charAt(i - 1))) % MOD;
            prev2 = prev1;
            prev1 = cur;
        }

        return (int) prev1;
    }

    // ways this character decodes on its own
    private int single(char c) {
        if (c == '*') return 9;      // 1..9
        return c == '0' ? 0 : 1;
    }

    // ways (c1, c2) forms a valid two-digit code 10..26
    private int pair(char c1, char c2) {
        if (c1 == '*' && c2 == '*') return 15;      // 11-19, 21-26
        if (c1 == '*') return c2 <= '6' ? 2 : 1;    // 1x always; 2x only when x <= 6
        if (c2 == '*') {
            if (c1 == '1') return 9;                // 11-19
            return c1 == '2' ? 6 : 0;               // 21-26
        }
        if (c1 == '1') return 1;                    // 10-19
        return (c1 == '2' && c2 <= '6') ? 1 : 0;    // 20-26
    }

    public static void main(String[] args) {
        LT_0639_Decode_Ways_II sol = new LT_0639_Decode_Ways_II();
        System.out.println(sol.numDecodings("*"));  // expected: 9
        System.out.println(sol.numDecodings("1*")); // expected: 18
        System.out.println(sol.numDecodings("2*")); // expected: 15
    }
}
