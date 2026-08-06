// Multiply Strings — https://leetcode.com/problems/multiply-strings/
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
