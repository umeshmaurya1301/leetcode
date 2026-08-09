/*
 * 17. Letter Combinations of a Phone Number — Medium
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 *
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 * Example 1:
 *   Input:  digits = "23"
 *   Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * Example 2:
 *   Input:  digits = ""
 *   Output: []
 *
 * Example 3:
 *   Input:  digits = "2"
 *   Output: ["a","b","c"]
 *
 * Constraints:
 *   0 <= digits.length <= 4
 *   digits[i] is a digit in the range ['2', '9'].
 *
 * Approach: Backtracking over the keypad letters, one digit per depth — O(4^n * n) time, O(n) recursion depth.
 */
package backtracking;

import java.util.*;

class LT_0017_Letter_Combinations_of_a_Phone_Number {

    private static final String[] KEYPAD = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits == null || digits.length() == 0) {
            return result;
        }

        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder path, List<String> result) {
        // Base case
        if (index == digits.length()) {
            result.add(path.toString());
            return;
        }

        String letters = KEYPAD[digits.charAt(index) - '0'];

        for (char ch : letters.toCharArray()) {
            path.append(ch);
            backtrack(digits, index + 1, path, result);
            path.deleteCharAt(path.length() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        LT_0017_Letter_Combinations_of_a_Phone_Number sol = new LT_0017_Letter_Combinations_of_a_Phone_Number();
        System.out.println(sol.letterCombinations("23")); // expected: [ad, ae, af, bd, be, bf, cd, ce, cf]
        System.out.println(sol.letterCombinations("")); // expected: []
        System.out.println(sol.letterCombinations("2")); // expected: [a, b, c]
    }
}
