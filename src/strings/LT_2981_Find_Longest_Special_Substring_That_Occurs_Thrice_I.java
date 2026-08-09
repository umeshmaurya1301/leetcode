/*
 * 2981. Find Longest Special Substring That Occurs Thrice I — Medium
 * https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-i/
 *
 * You are given a string s that consists of lowercase English letters.
 *
 * A string is called special if it is made up of only a single character. For example, "abc" is not special, whereas "ddd", "zz", and "f" are special.
 *
 * Return the length of the longest special substring of s which occurs at least thrice, or -1 if no special substring occurs at least thrice.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 *
 * Example 1:
 *   Input:  s = "aaaa"
 *   Output: 2
 *   Explanation: The longest special substring which occurs at least thrice is "aa", which occurs 3 times at overlapping positions.
 *
 * Example 2:
 *   Input:  s = "abcdef"
 *   Output: -1
 *   Explanation: Every special substring is a single unique character — each appears only once. No special substring occurs thrice.
 *
 * Example 3:
 *   Input:  s = "abcaba"
 *   Output: 1
 *   Explanation: The longest special substring occurring at least thrice is "a", which appears exactly 3 times.
 *
 * Constraints:
 *   3 <= s.length <= 50
 *   s consists of only lowercase English letters.
 *
 * Approach: Split into single-character runs, count how many substrings of each length every character yields, take the longest with count >= 3 — O(n) time, O(n) space.
 */
package strings;

import java.util.*;

class LT_2981_Find_Longest_Special_Substring_That_Occurs_Thrice_I {
    public int maximumLength(String s) {
        Map<Character, List<Integer>> map = new HashMap<>();

        int n = s.length();
        for (int i = 0; i < n;) {
            char ch = s.charAt(i);
            int j = i;
            while (j < n && s.charAt(j) == ch) {
                j++;
            }
            int len = j - i;
            map.computeIfAbsent(ch, k -> new ArrayList<>()).add(len);
            i = j;
        }

        int maxLen = -1;

        for (char ch : map.keySet()) {
            List<Integer> lens = map.get(ch);
            int[] count = new int[51]; // since max len = 70
            for (int len : lens) {
                for (int l = 1; l <= len; l++) {
                    count[l] += (len - l + 1); // FIXED LINE
                }
            }

            for (int l = 50; l >= 1; l--) {
                if (count[l] >= 3) {
                    maxLen = Math.max(maxLen, l);
                    break;
                }
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        LT_2981_Find_Longest_Special_Substring_That_Occurs_Thrice_I sol = new LT_2981_Find_Longest_Special_Substring_That_Occurs_Thrice_I();
        System.out.println(sol.maximumLength("aaaa")); // expected: 2
        System.out.println(sol.maximumLength("abcdef")); // expected: -1
        System.out.println(sol.maximumLength("abcaba")); // expected: 1
    }
}
