// Longest Increasing Subsequence — https://leetcode.com/problems/longest-increasing-subsequence/
package dynamicprogramming;

import java.util.*;

class LT_0300_Longest_Increasing_Subsequence_3_BinarySearchPatience {
    public int lengthOfLIS(int[] nums) {
        List<Integer> tails = new ArrayList<>();

        for (int x : nums) {
            // first index with tails[idx] >= x
            int lo = 0, hi = tails.size();
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (tails.get(mid) < x) lo = mid + 1;
                else hi = mid;
            }

            if (lo == tails.size()) tails.add(x);   // extend
            else tails.set(lo, x);                  // tighten
        }

        return tails.size();
    }

    public static void main(String[] args) {
        LT_0300_Longest_Increasing_Subsequence_3_BinarySearchPatience sol = new LT_0300_Longest_Increasing_Subsequence_3_BinarySearchPatience();
        System.out.println(sol.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18})); // expected: 4
        System.out.println(sol.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3})); // expected: 4
        System.out.println(sol.lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7})); // expected: 1
    }
}
