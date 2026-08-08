// Count Good Meals — https://leetcode.com/problems/count-good-meals/
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
