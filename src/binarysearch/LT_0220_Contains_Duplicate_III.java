/*
 * 220. Contains Duplicate III — Hard
 * https://leetcode.com/problems/contains-duplicate-iii/description/
 *
 * Given an integer array nums and two integers indexDiff and valueDiff, return true if there exist two indices i and j such that:
 * - i != j
 * - abs(i - j) <= indexDiff
 * - abs(nums[i] - nums[j]) <= valueDiff
 *
 * Return false otherwise.
 *
 * Example 1:
 *   Input:  nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
 *   Output: true
 *   Explanation: We can choose i = 0, j = 3. |0 - 3| = 3 <= 3 and |1 - 1| = 0 <= 0.
 *
 * Example 2:
 *   Input:  nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3
 *   Output: false
 *   Explanation: No pair of indices satisfies both conditions simultaneously.
 *
 * Constraints:
 *   2 <= nums.length <= 10^5
 *   -10^9 <= nums[i] <= 10^9
 *   1 <= indexDiff <= nums.length
 *   0 <= valueDiff <= 10^9
 *
 * Approach: TreeSet holding the last indexDiff values; ceiling() finds a value within valueDiff — O(n log k) time, O(k) space.
 */
package binarysearch;

import java.util.*;

class LT_0220_Contains_Duplicate_III {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> set = new TreeSet<>();

        for(int i=0; i<nums.length; i++) {
            long curr = nums[i];
            Long candidate = set.ceiling(curr-valueDiff);
            if(candidate!=null && candidate <= curr+valueDiff) return true;
            set.add(curr);
            if(i>=indexDiff) {
                set.remove( (long)nums[i-indexDiff] );
            }
        }

        return false;
    }

    public static void main(String[] args) {
        LT_0220_Contains_Duplicate_III sol = new LT_0220_Contains_Duplicate_III();
        System.out.println(sol.containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0)); // expected: true
        System.out.println(sol.containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3)); // expected: false
    }
}
