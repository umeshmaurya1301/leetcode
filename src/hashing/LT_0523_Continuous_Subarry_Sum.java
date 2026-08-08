// Continuous Subarray Sum — https://leetcode.com/problems/continuous-subarray-sum/
package hashing;

import java.util.*;

class LT_0523_Continuous_Subarry_Sum {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // base case

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            int rem = sum % k;

            if (map.containsKey(rem)) {
                if (i - map.get(rem) > 1) {
                    return true;
                }
            } else {
                map.put(rem, i);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        LT_0523_Continuous_Subarry_Sum sol = new LT_0523_Continuous_Subarry_Sum();
        System.out.println(sol.checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6)); // expected: true
        System.out.println(sol.checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 6)); // expected: true
        System.out.println(sol.checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 13)); // expected: false
    }
}
