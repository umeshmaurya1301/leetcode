/*
 * 139. Word Break — Medium
 * https://leetcode.com/problems/word-break/
 *
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented
 * into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Example 1:
 *   Input:  s = "leetcode", wordDict = ["leet","code"]
 *   Output: true
 *   Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 *   Input:  s = "applepenapple", wordDict = ["apple","pen"]
 *   Output: true
 *   Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *                Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 *   Input:  s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 *   Output: false
 *
 * Constraints:
 *   1 <= s.length <= 300
 *   1 <= wordDict.length <= 1000
 *   1 <= wordDict[i].length <= 20
 *   s and wordDict[i] consist of only lowercase English letters.
 *   All the strings of wordDict are unique.
 *
 * Approach: Top-down memoization on the suffix start index, with the dictionary in a HashSet
 *           for O(1) lookup — O(n^2 * L) time, O(n) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0139_Word_Break {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);  // for O(1) lookup
        Map<Integer, Boolean> memo = new HashMap<>();
        return canBreak(s, 0, wordSet, memo);
    }

    private boolean canBreak(String s, int start, Set<String> wordSet, Map<Integer, Boolean> memo) {
        if (start == s.length()) return true;  // reached end successfully
        if (memo.containsKey(start)) return memo.get(start);

        for (int end = start + 1; end <= s.length(); end++) {
            String sub = s.substring(start, end);
            if (wordSet.contains(sub) && canBreak(s, end, wordSet, memo)) {
                memo.put(start, true);
                return true;
            }
        }

        memo.put(start, false);
        return false;
    }

    public static void main(String[] args) {
        LT_0139_Word_Break sol = new LT_0139_Word_Break();
        System.out.println(sol.wordBreak("leetcode", Arrays.asList("leet", "code"))); // expected: true
        System.out.println(sol.wordBreak("applepenapple", Arrays.asList("apple", "pen"))); // expected: true
        System.out.println(sol.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"))); // expected: false
    }

}
