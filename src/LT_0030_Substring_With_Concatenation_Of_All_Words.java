// Substring with Concatenation of All Words — https://leetcode.com/problems/substring-with-concatenation-of-all-words/
import java.util.*;

class LT_0030_Substring_With_Concatenation_Of_All_Words {

    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<>();

        if (s == null || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;

        Map<String, Integer> target = new HashMap<>();

        for (String word : words) {
            target.merge(word, 1, Integer::sum);
        }

        for (int offset = 0; offset < wordLen; offset++) {

            Map<String, Integer> window = new HashMap<>();

            int left = offset;
            int matchedWords = 0;

            for (int right = offset;
                 right + wordLen <= s.length();
                 right += wordLen) {

                String word =
                        s.substring(right, right + wordLen);

                if (!target.containsKey(word)) {
                    window.clear();
                    matchedWords = 0;
                    left = right + wordLen;
                    continue;
                }

                window.merge(word, 1, Integer::sum);
                matchedWords++;

                while (window.get(word) > target.get(word)) {

                    String leftWord =
                            s.substring(left, left + wordLen);

                    window.merge(leftWord, -1, Integer::sum);

                    matchedWords--;
                    left += wordLen;
                }

                if (matchedWords == wordCount) {

                    result.add(left);

                    String leftWord =
                            s.substring(left, left + wordLen);

                    window.merge(leftWord, -1, Integer::sum);

                    matchedWords--;
                    left += wordLen;
                }
            }
        }

        return result;
    }
}
