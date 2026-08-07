// Subarray Sums Divisible by K — https://leetcode.com/problems/subarray-sums-divisible-by-k/
import java.util.*;

class LT_0974_Subarray_Sums_Divisible_by_K_2_PrefixModArray {
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        int prefixMod = 0, result = 0;

        // There are k mod groups 0...k-1.
        int[] modGroups = new int[k];
        modGroups[0] = 1;

        for (int num: nums) {
            // Take modulo twice to avoid negative remainders.
            prefixMod = (prefixMod + num % k + k) % k;
            // Add the count of subarrays that have the same remainder as the current
            // one to cancel out the remainders.
            result += modGroups[prefixMod];
            modGroups[prefixMod]++;
        }

        return result;
    }

    public static void main(String[] args) {
        LT_0974_Subarray_Sums_Divisible_by_K_2_PrefixModArray sol = new LT_0974_Subarray_Sums_Divisible_by_K_2_PrefixModArray();
        System.out.println(sol.subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5)); // expected: 7
        System.out.println(sol.subarraysDivByK(new int[]{5}, 5)); // expected: 1
    }
}
