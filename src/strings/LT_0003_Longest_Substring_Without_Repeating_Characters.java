/*
 * 3. Longest Substring Without Repeating Characters — Medium
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Given a string s, find the length of the longest substring without duplicate characters.
 *
 * Example:
 *   Input:  "abcabcbb"
 *   Output: 3
 *   Explanation: "abc" is the longest substring without repeating characters.
 *
 * Constraints:
 *   0 ≤ s.length ≤ 10⁵
 *   s consists of English letters, digits, symbols, and spaces
 *
 * Approach: Sliding window with a last-seen index map — O(n) time, O(min(n, charset)) space.
 */
package strings;

import java.util.*;

class LT_0003_Longest_Substring_Without_Repeating_Characters {
    public int lengthOfLongestSubstring(String s) {
        int bestLen = 0;
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);

            if (map.containsKey(ch)) {
                left = Math.max(map.get(ch) + 1, left);
            }

            map.put(ch, right);
            bestLen = Math.max(bestLen, right - left + 1);
        }

        return bestLen;
    }

    public static void main(String[] args) {
        LT_0003_Longest_Substring_Without_Repeating_Characters sol = new LT_0003_Longest_Substring_Without_Repeating_Characters();
        System.out.println(sol.lengthOfLongestSubstring("abcabcbb")); // expected: 3
    }
}
