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

    public static void main(String[] args) {
        LT_0049_Group_Anagrams_1_SortingKey sol = new LT_0049_Group_Anagrams_1_SortingKey();
        System.out.println(sol.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"})); // expected: [[eat, tea, ate], [tan, nat], [bat]]
        System.out.println(sol.groupAnagrams(new String[]{""})); // expected: [[]]
    }
}
