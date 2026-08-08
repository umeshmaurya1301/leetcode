// Find Longest Special Substring That Occurs Thrice I — https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-i/
package hashing;

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
