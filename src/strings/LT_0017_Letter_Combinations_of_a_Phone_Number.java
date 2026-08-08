// Letter Combinations of a Phone Number — https://leetcode.com/problems/letter-combinations-of-a-phone-number/
package strings;

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
