/*
 * 1711. Count Good Meals — Medium
 * https://leetcode.com/problems/count-good-meals/
 *
 * A good meal is a pair of food items whose deliciousness values sum to a power of two.
 *
 * Given an array deliciousness, return the number of different good meals.
 *
 * Since the answer can be very large, return it modulo 10^9 + 7.
 *
 * Example:
 *   Input:  [1,3,5,7,9]
 *   Output: 4
 *
 *   Explanation:
 *   Good pairs are:
 *
 *   - (1,3) → 4
 *   - (1,7) → 8
 *   - (3,5) → 8
 *   - (7,9) → 16
 *
 * Constraints:
 *   1 ≤ deliciousness.length ≤ 10^5
 *   0 ≤ deliciousness[i] ≤ 2^20
 *   # 💡 Solutions
 *
 * Approach: For each item probe all 22 powers of two against a frequency map of items seen so far — O(22n) time, O(n) space.
 */
package bitmanipulation;

import java.util.*;

class LT_1711_Count_Good_Meals {

    public int countPairs(int[] deliciousness) {

        long count = 0;

        int MOD = 1_000_000_007;

        Map<Integer, Integer> map = new HashMap<>();

        for (int n : deliciousness) {

            int power = 1;

            for (int i = 0; i <= 21; i++) {

                int required = power - n;

                count += map.getOrDefault(required, 0);

                power = power << 1;
            }

            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        return (int)(count % MOD);
    }

    public static void main(String[] args) {
        LT_1711_Count_Good_Meals sol = new LT_1711_Count_Good_Meals();
        System.out.println(sol.countPairs(new int[]{1, 3, 5, 7, 9})); // expected: 4
    }
}
