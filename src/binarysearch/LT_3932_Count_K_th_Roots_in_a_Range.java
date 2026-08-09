/*
 * 3932. Count K-th Roots in a Range — Medium
 * https://leetcode.com/problems/count-k-th-roots-in-a-range/
 *
 * Given three integers l, r, and k. An integer y is said to be a perfect k-th power if there exists an integer x such that y = x^k. Return the number of integers y in the range [l, r] (inclusive) that are perfect k-th powers.
 *
 * Example 1:
 *   Input:  l = 1, r = 9, k = 3
 *   Output: 2
 *   Explanation: The perfect cubes in [1, 9] are 1 = 1³ and 8 = 2³.
 *
 * Example 2:
 *   Input:  l = 8, r = 30, k = 2
 *   Output: 3
 *   Explanation: The perfect squares in [8, 30] are 9 = 3², 16 = 4², 25 = 5².
 *
 * Constraints:
 *   0 <= l <= r <= 10^9
 *   1 <= k <= 30
 *
 * Approach: Estimate the bounds with Math.pow, then correct them with exact integer powers — O(k) time, O(1) space.
 */
package binarysearch;

import java.util.*;

class LT_3932_Count_K_th_Roots_in_a_Range {
    public int countKthRoots(int l, int r, int k) {
        // Find smallest x where x^k >= l
        int minX = (int) Math.ceil(Math.pow(l, 1.0 / k));
        // Find largest x where x^k <= r
        int maxX = (int) Math.floor(Math.pow(r, 1.0 / k));

        // Adjust for floating point errors
        // Walk minX down if needed
        while (pow(minX, k) < l) minX++;
        while (minX > 1 && pow(minX - 1, k) >= l) minX--;

        // Walk maxX up if needed  
        while (pow(maxX + 1, k) <= r) maxX++;
        while (pow(maxX, k) > r) maxX--;

        return maxX >= minX ? maxX - minX + 1 : 0;
    }

    // Safe integer power to avoid overflow — use long
    private long pow(long base, int exp) {
        long result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
            if (result > (long) 2e9) return (long) 2e9; // cap to avoid overflow
        }
        return result;
    }

    public static void main(String[] args) {
        LT_3932_Count_K_th_Roots_in_a_Range sol = new LT_3932_Count_K_th_Roots_in_a_Range();
        System.out.println(sol.countKthRoots(1, 9, 3)); // expected: 2
        System.out.println(sol.countKthRoots(8, 30, 2)); // expected: 3
    }
}
