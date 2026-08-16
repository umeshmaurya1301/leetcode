/*
 * 4021. Minimum Operations to Make a Rotated Palindrome I — Medium
 * https://leetcode.com/problems/minimum-operations-to-make-a-rotated-palindrome-i/
 *
 * You are given a string s consisting of lowercase English letters. You can perform
 * the following operations any number of times (including zero) and in any order:
 * Increment: choose any index i and replace s[i] with the next lowercase English
 * letter (the letter after 'z' is 'a'). Left rotate: move the first character of
 * the string to the end.
 * Return the minimum number of operations required to make s a palindrome.
 *
 * Example 1:
 *   Input:  s = "abc"
 *   Output: 2
 *   Explanation: Left rotate "abc" -> "bca", then increment 'a' to 'b': "bca" -> "bcb".
 *                "bcb" is a palindrome. Total operations = 2.
 *
 * Example 2:
 *   Input:  s = "yb"
 *   Output: 3
 *   Explanation: Increment the first character three times: "yb" -> "zb" -> "ab" -> "bb".
 *                "bb" is a palindrome. Total operations = 3.
 *
 * Constraints:
 *   2 <= s.length <= 2000
 *   s consists only of lowercase English letters.
 *
 * Approach: Enumerate all n rotations, then for each fixed rotation sum the
 * cheapest cyclic-distance increment for every mirrored index pair — O(n^2) time, O(1) space.
 */
package twopointers;

import java.util.*;

class LT_4021_Minimum_Operations_to_Make_a_Rotated_Palindrome_I {
    public int minOperations(String s) {
        int n = s.length();
        int ans = Integer.MAX_VALUE;

        for (int k = 0; k < n; k++) {
            int cost = k; // rotation cost

            for (int i = 0; i < n / 2; i++) {
                char left = s.charAt((k + i) % n);
                char right = s.charAt((k + (n - 1 - i)) % n);

                int a = left - 'a';
                int b = right - 'a';

                int diff1 = (a - b + 26) % 26;
                int diff2 = (b - a + 26) % 26;

                cost += Math.min(diff1, diff2);
            }

            ans = Math.min(ans, cost);
        }

        return ans;
    }

    public static void main(String[] args) {
        LT_4021_Minimum_Operations_to_Make_a_Rotated_Palindrome_I sol = new LT_4021_Minimum_Operations_to_Make_a_Rotated_Palindrome_I();
        System.out.println(sol.minOperations("abc")); // expected: 2
        System.out.println(sol.minOperations("yb"));  // expected: 3
    }
}
