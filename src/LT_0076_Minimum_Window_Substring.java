// Minimum Window Substring — https://leetcode.com/problems/minimum-window-substring/
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
