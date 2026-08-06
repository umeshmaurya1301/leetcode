// Longest Consecutive Sequence — https://leetcode.com/problems/longest-consecutive-sequence/description/
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
}
