// Longest Nice Subarray — https://leetcode.com/problems/longest-nice-subarray/
package slidingwindow;

import java.util.*;

class LT_2401_Longest_Nice_SubArray {
    public int longestNiceSubarray(int[] nums) {

        int left  = 0;
        int mask  = 0;   // OR of all numbers currently in the window
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {

            // Shrink window from left until no bit conflict with nums[right]
            while ((mask & nums[right]) != 0) {
                mask ^= nums[left];   // remove nums[left]'s bits from mask
                left++;
            }

            // Safely add nums[right] — no shared bits
            mask |= nums[right];

            // Update answer
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        LT_2401_Longest_Nice_SubArray sol = new LT_2401_Longest_Nice_SubArray();
        System.out.println(sol.longestNiceSubarray(new int[]{1, 3, 8, 48, 10})); // expected: 3
        System.out.println(sol.longestNiceSubarray(new int[]{3, 1, 5, 11, 13})); // expected: 1
    }
}
