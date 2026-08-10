/*
 * 72. Edit Distance — Medium
 * https://leetcode.com/problems/edit-distance/
 *
 * Given two strings word1 and word2, return the minimum number of operations required to convert
 * word1 to word2.
 *
 * You have the following three operations permitted on a word:
 *   Insert a character
 *   Delete a character
 *   Replace a character
 *
 * Example 1:
 *   Input:  word1 = "horse", word2 = "ros"
 *   Output: 3
 *   Explanation: horse -> rorse (replace 'h' with 'r')
 *                rorse -> rose  (remove 'r')
 *                rose  -> ros   (remove 'e')
 *
 * Example 2:
 *   Input:  word1 = "intention", word2 = "execution"
 *   Output: 5
 *   Explanation: intention -> inention (remove 't')
 *                inention  -> enention (replace 'i' with 'e')
 *                enention  -> exention (replace 'n' with 'x')
 *                exention  -> exection (replace 'n' with 'c')
 *                exection  -> execution (insert 'u')
 *
 * Constraints:
 *   0 <= word1.length, word2.length <= 500
 *   word1 and word2 consist of lowercase English letters.
 *
 * Approach: Top-down memoization on the suffix pair (i, j). A matching character costs nothing and
 *           advances both pointers; a mismatch pays 1 and takes the cheapest of insert (j+1),
 *           delete (i+1) and replace (i+1, j+1). Exhausting either string collapses to the number
 *           of characters left in the other — O(m*n) time, O(m*n) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0072_Edit_Distance {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        Integer[][] memo = new Integer[m][n];
        return dp(word1, word2, 0, 0, memo);
    }

    private int dp(String word1, String word2, int i, int j, Integer[][] memo) {
        // If one string is exhausted, we need to insert/delete remaining characters
        if (i == word1.length()) return word2.length() - j;
        if (j == word2.length()) return word1.length() - i;

        // Check memo
        if (memo[i][j] != null) return memo[i][j];

        if (word1.charAt(i) == word2.charAt(j)) {
            // No operation needed
            memo[i][j] = dp(word1, word2, i + 1, j + 1, memo);
        } else {
            // Three possible operations:
            int insertOp = 1 + dp(word1, word2, i, j + 1, memo);      // Insert
            int deleteOp = 1 + dp(word1, word2, i + 1, j, memo);      // Delete
            int replaceOp = 1 + dp(word1, word2, i + 1, j + 1, memo); // Replace

            memo[i][j] = Math.min(insertOp, Math.min(deleteOp, replaceOp));
        }
        return memo[i][j];
    }

    public static void main(String[] args) {
        LT_0072_Edit_Distance sol = new LT_0072_Edit_Distance();
        System.out.println(sol.minDistance("horse", "ros")); // expected: 3
        System.out.println(sol.minDistance("intention", "execution")); // expected: 5
    }

}
