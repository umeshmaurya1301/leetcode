/*
 * 43. Multiply Strings — Medium
 * https://leetcode.com/problems/multiply-strings/
 *
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 *
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 * Example 1:
 *   Input:  num1 = "2", num2 = "3"
 *   Output: "6"
 *   Explanation: Basic single-digit multiplication.
 *
 * Example 2:
 *   Input:  num1 = "123", num2 = "456"
 *   Output: "56088"
 *   Explanation: Standard multi-digit multiplication.
 *
 * Example 3:
 *   Input:  num1 = "0", num2 = "523"
 *   Output: "0"
 *   Explanation: Any number multiplied by 0 is 0.
 *
 * Constraints:
 *   1 <= num1.length, num2.length <= 200
 *   num1 and num2 consist of digits only.
 *   Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 *
 * Approach: Schoolbook multiplication into an (m+n) digit array, carrying at the end — O(m * n) time, O(m + n) space.
 */
package strings;

import java.util.*;

class LT_0043_Multiply_Strings {
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        int m = num1.length();
        int n = num2.length();
        int[] pos = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');

                int p1 = i + j;
                int p2 = i + j + 1;

                int sum = mul + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = sum % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int p : pos) {
            if (!(sb.length() == 0 && p == 0)) {
                sb.append(p);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        LT_0043_Multiply_Strings sol = new LT_0043_Multiply_Strings();
        System.out.println(sol.multiply("2", "3")); // expected: "6"
        System.out.println(sol.multiply("123", "456")); // expected: "56088"
        System.out.println(sol.multiply("0", "523")); // expected: "0"
    }
}
