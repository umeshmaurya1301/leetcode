/*
 * 30. Substring with Concatenation of All Words — Hard
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 *
 * You are given a string s and an array of strings words. All the strings of words are of the same length.
 *
 * A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.
 *
 * - For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all concatenated strings. "acdbef" is not a concatenated string because it is not the concatenation of any permutation of words.
 *
 * Return an array of the starting indices of all the concatenated substrings in s. You can return the answer in any order.
 *
 * Example 1:
 *   Input:  s = "barfoothefoobarman", words = ["foo","bar"]
 *   Output: [0,9]
 *   Explanation: The substring starting at 0 is "barfoo" — the concatenation of ["bar","foo"], a permutation of words. The substring starting at 9 is "foobar" — the concatenation of ["foo","bar"].
 *
 * Example 2:
 *   Input:  s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 *   Output: []
 *   Explanation: There is no concatenated substring. Note "word" appears twice, so we need two "word"s but they never line up with a single "good" and "best".
 *
 * Example 3:
 *   Input:  s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 *   Output: [6,9,12]
 *   Explanation: The substrings starting at 6 ("foobarthe"), 9 ("barthefoo"), and 12 ("thefoobar") are each a permutation of words.
 *
 * Constraints:
 *   1 <= s.length <= 10^4
 *   1 <= words.length <= 5000
 *   1 <= words[i].length <= 30
 *   s and words[i] consist of lowercase English letters.
 *
 * Approach: One sliding window per word-length offset over a word-count map — O(n * wordLen) time, O(words * wordLen) space.
 */
package hashing;

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

    public static void main(String[] args) {
        LT_0030_Substring_With_Concatenation_Of_All_Words sol = new LT_0030_Substring_With_Concatenation_Of_All_Words();
        System.out.println(sol.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"})); // expected: [0, 9]
        System.out.println(sol.findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"})); // expected: []
        System.out.println(sol.findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"})); // expected: [6, 9, 12]
    }
}
