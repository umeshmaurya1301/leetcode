/*
 * 49. Group Anagrams — Medium
 * https://leetcode.com/problems/group-anagrams/description/
 *
 * Given an array of strings strs, group the anagrams together.
 *
 * Two strings are anagrams if they contain the same characters with the same frequencies, but the order of characters may differ.
 *
 * Return the grouped anagrams in any order.
 *
 * Example 1:
 *   Input:  strs = ["eat","tea","tan","ate","nat","bat"]
 *   Output: [["eat","tea","ate"],["tan","nat"],["bat"]]
 *   Explanation: "eat", "tea", and "ate" are anagrams — sorted they all become "aet". Similarly "tan" and "nat" become "ant".
 *
 * Example 2:
 *   Input:  strs = [""]
 *   Output: [[""]]
 *   Explanation: Empty string forms a single anagram group.
 *
 * Example 3:
 *   Input:  strs = ["a"]
 *   Output: [["a"]]
 *   Explanation: Single character string forms one group.
 *
 * Constraints:
 *   1 <= strs.length <= 10000
 *   0 <= strs[i].length <= 100
 *   strs[i] consists of lowercase English letters.
 *
 * Approach: Group in a HashMap keyed by a 26-slot frequency signature — O(n * k) time, O(n * k) space.
 */
package strings;

import java.util.*;

class LT_0049_Group_Anagrams_2_FrequencyCountKey {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strs) {
            int[] freq = new int[26];
            for(char c : s.toCharArray()) {
                freq[c-'a']++;
            }
            StringBuilder builder = new StringBuilder();
            for(int n : freq) {
                builder.append(String.valueOf(n)+"#");
            }
            String key = new String(builder);
            List<String> values = map.getOrDefault(key, new ArrayList<>());
            values.add(s);
            map.put(key, values);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        LT_0049_Group_Anagrams_2_FrequencyCountKey sol = new LT_0049_Group_Anagrams_2_FrequencyCountKey();
        System.out.println(sol.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"})); // expected: [[eat, tea, ate], [tan, nat], [bat]]
        System.out.println(sol.groupAnagrams(new String[]{""})); // expected: [[]]
    }
}
