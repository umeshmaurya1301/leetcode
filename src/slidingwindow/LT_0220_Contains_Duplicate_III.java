// Contains Duplicate III — https://leetcode.com/problems/contains-duplicate-iii/description/
package slidingwindow;

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
