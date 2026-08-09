/*
 * 647. Palindromic Substrings — Medium
 * https://leetcode.com/problems/palindromic-substrings/
 *
 * Given a string s, return the number of palindromic substrings in it.
 *
 * A string is a palindrome when it reads the same backward as forward.
 *
 * A substring is a contiguous sequence of characters within the string.
 *
 * Example 1:
 *   Input:  s = "abc"
 *   Output: 3
 *   Explanation: Three palindromic strings: "a", "b", "c".
 *
 * Example 2:
 *   Input:  s = "aaa"
 *   Output: 6
 *   Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 * Constraints:
 *   1 <= s.length <= 1000
 *   s consists of lowercase English letters.
 *
 * Approach: Enumerate every (i, j) substring pair and verify each with a converging
 *           two-pointer palindrome check — O(n^3) time, O(n^2) space. The dp table is
 *           written but never read back, since each (i, j) is visited exactly once.
 */
package twopointers;

import java.util.*;

class LT_0647_Palindromic_Substrings {
    public int countSubstrings(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for(int [] a : dp) Arrays.fill(a, -1);
        int count = 0;
        for (int i=0; i<len; i++) {
            for(int j=i; j<len; j++) {
                if(dp[i][j]!=-1) {
                    count += dp[i][j];
                }  else {
                    if(isPal(s, i, j)) {
                        dp[i][j] = 1;
                        count += 1;
                    }
                }
            }
        }

        return count;
    }

    private boolean isPal (String s, int i, int j) {
        while (i<=j) {
            if(s.charAt(i)==s.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LT_0647_Palindromic_Substrings sol = new LT_0647_Palindromic_Substrings();
        System.out.println(sol.countSubstrings("abc")); // expected: 3
        System.out.println(sol.countSubstrings("aaa")); // expected: 6
    }

}
