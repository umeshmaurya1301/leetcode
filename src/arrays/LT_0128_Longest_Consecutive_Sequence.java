// Longest Consecutive Sequence — https://leetcode.com/problems/longest-consecutive-sequence/description/
package arrays;

import java.util.*;

class LT_0128_Longest_Consecutive_Sequence {

    public int longestConsecutive(int[] nums) {

        Set<Integer> set = new HashSet<>();

        for (int n : nums) {
            set.add(n);
        }

        int longest = 0;

        for (int n : set) {

            // Start only if sequence beginning
            if (!set.contains(n - 1)) {

                int curr = n;
                int streak = 1;

                while (set.contains(curr + 1)) {
                    curr++;
                    streak++;
                }

                longest = Math.max(longest, streak);
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        LT_0128_Longest_Consecutive_Sequence sol = new LT_0128_Longest_Consecutive_Sequence();
        System.out.println(sol.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2})); // expected: 4
        System.out.println(sol.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1})); // expected: 9
    }
}
