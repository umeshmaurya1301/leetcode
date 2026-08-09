/*
 * 2982. Find Longest Special Substring That Occurs Thrice II — Medium
 * https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-ii/
 *
 * You are given a string s that consists of lowercase English letters.
 *
 * A string is called special if it is made up of only a single character. For example, "abc" is not special, whereas "ddd", "zz", and "f" are special.
 *
 * Return the length of the longest special substring of s which occurs at least thrice, or -1 if no special substring occurs at least thrice.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 *
 * Note: This is the same problem as 2981 but with n up to 5 × 10^5, requiring an O(n) solution.
 *
 * Example 1:
 *   Input:  s = "aaaa"
 *   Output: 2
 *   Explanation: The longest special substring occurring at least thrice is "aa", appearing at 3 overlapping positions.
 *
 * Example 2:
 *   Input:  s = "abcdef"
 *   Output: -1
 *   Explanation: Every special substring is a unique single character — none appear thrice.
 *
 * Example 3:
 *   Input:  s = "abcaba"
 *   Output: 1
 *   Explanation: "a" appears exactly 3 times; no longer special substring reaches that threshold.
 *
 * Constraints:
 *   3 <= s.length <= 5 * 10^5
 *   s consists of only lowercase English letters.
 *
 * Approach: Same run-length counting into a 26 x (n+1) grid, then suffix sums per character — O(26n) time, O(26n) space.
 */
package arrays;

import java.util.*;

class LT_2982_Find_Longest_Special_Substring_That_Occurs_Thrice_II {
    public int maximumLength(String s) {
        int n = s.length();
        int[][] lenArr = new int[26][n+1];

        for(int i=0; i<n;) {
            char ch = s.charAt(i);
            int j=i;
            while(j<n && ch== s.charAt(j)) {
                j++;
            }
            int len = j-i;
            for(int k=1; k<=len; k++) {
                lenArr[ch-'a'][k]++;
            }
            i=j;
        }

        for(int i=0; i<lenArr.length; i++) {
            int sum = 0;
            for (int j=n; j>=1; j--) {
                int val = lenArr[i][j];
                sum += val;
                lenArr[i][j] = sum;
            }
        }

        int max = -1;
        for(int i=0; i<lenArr.length; i++) {
            for(int j=n-1; j>=0; j--) {
                if(lenArr[i][j]>=3) {
                    max = Math.max(max, j);
                    break;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LT_2982_Find_Longest_Special_Substring_That_Occurs_Thrice_II sol = new LT_2982_Find_Longest_Special_Substring_That_Occurs_Thrice_II();
        System.out.println(sol.maximumLength("aaaa")); // expected: 2
        System.out.println(sol.maximumLength("abcdef")); // expected: -1
        System.out.println(sol.maximumLength("abcaba")); // expected: 1
    }
}
