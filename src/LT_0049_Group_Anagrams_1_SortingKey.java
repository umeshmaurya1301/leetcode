// Group Anagrams — https://leetcode.com/problems/group-anagrams/description/
import java.util.*;

class LT_0049_Group_Anagrams_1_SortingKey {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for(String str : strs) {

            char[] arr = str.toCharArray();

            Arrays.sort(arr);

            String key = new String(arr);

            map.putIfAbsent(key, new ArrayList<>());

            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
