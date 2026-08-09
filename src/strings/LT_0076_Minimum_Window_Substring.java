/*
 * 76. Minimum Window Substring — Hard
 * https://leetcode.com/problems/minimum-window-substring/
 *
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window.
 *
 * If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 *
 * Example 1:
 *   Input:  s = "ADOBECODEBANC", t = "ABC"
 *   Output: "BANC"
 *   Explanation: The minimum window "BANC" includes 'A', 'B', and 'C' from t.
 *
 * Example 2:
 *   Input:  s = "a", t = "a"
 *   Output: "a"
 *   Explanation: The entire string s is the minimum window.
 *
 * Example 3:
 *   Input:  s = "a", t = "aa"
 *   Output: ""
 *   Explanation: Both 'a's from t must be included. The largest window of s has only one 'a'.
 *
 * Constraints:
 *   m == s.length(), n == t.length()
 *   1 <= m, n <= 10^5
 *   s and t consist of uppercase and lowercase English letters.
 *
 *   Follow-up: Can you find an algorithm that runs in O(m + n) time?
 *
 * Approach: Sliding window over need / window counts with a formed counter, shrinking while valid — O(n + m) time, O(charset) space.
 */
package strings;

import java.util.*;

class LT_0076_Minimum_Window_Substring {
    public String minWindow(String s, String t) {

        if (s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> target = new HashMap<>();

        for (char ch : t.toCharArray()) {
            target.put(ch, target.getOrDefault(ch, 0) + 1);
        }

        int required = target.size();
        int formed = 0;

        Map<Character, Integer> window = new HashMap<>();

        int left = 0;

        int minLen = Integer.MAX_VALUE;
        int start = 0;

        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);

            window.put(ch, window.getOrDefault(ch, 0) + 1);

            if (target.containsKey(ch) &&
                window.get(ch).intValue() == target.get(ch).intValue()) {
                formed++;
            }

        while (formed == required) {

                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                char leftChar = s.charAt(left);

                window.put(leftChar, window.get(leftChar) - 1);

                if (target.containsKey(leftChar) &&
                    window.get(leftChar) < target.get(leftChar)) {
                    formed--;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE
                ? ""
                : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        LT_0076_Minimum_Window_Substring sol = new LT_0076_Minimum_Window_Substring();
        System.out.println(sol.minWindow("ADOBECODEBANC", "ABC")); // expected: "BANC"
        System.out.println(sol.minWindow("a", "a")); // expected: "a"
        System.out.println(sol.minWindow("a", "aa")); // expected: ""
    }
}
