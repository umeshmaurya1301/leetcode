// Group Anagrams — https://leetcode.com/problems/group-anagrams/description/
package hashing;

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
