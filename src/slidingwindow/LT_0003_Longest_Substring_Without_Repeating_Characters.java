// Longest Substring Without Repeating Characters — https://leetcode.com/problems/longest-substring-without-repeating-characters/
package slidingwindow;

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
