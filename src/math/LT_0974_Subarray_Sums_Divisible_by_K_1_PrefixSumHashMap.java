// Subarray Sums Divisible by K — https://leetcode.com/problems/subarray-sums-divisible-by-k/
package math;

import java.util.*;

class LT_0974_Subarray_Sums_Divisible_by_K_1_PrefixSumHashMap {
    public int subarraysDivByK(int[] nums, int k) {        
        Map<Integer, Integer> remainderCount = new HashMap<>();
        remainderCount.put(0, 1); // Important: empty prefix sum

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;

            int remainder = ((prefixSum % k) + k) % k;
            count += remainderCount.getOrDefault(remainder, 0);
            remainderCount.put(remainder, remainderCount.getOrDefault(remainder, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        LT_0974_Subarray_Sums_Divisible_by_K_1_PrefixSumHashMap sol = new LT_0974_Subarray_Sums_Divisible_by_K_1_PrefixSumHashMap();
        System.out.println(sol.subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5)); // expected: 7
        System.out.println(sol.subarraysDivByK(new int[]{5}, 5)); // expected: 1
    }
}
