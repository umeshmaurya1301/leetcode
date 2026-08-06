// Longest Nice Subarray — https://leetcode.com/problems/longest-nice-subarray/
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
}
