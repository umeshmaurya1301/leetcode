// Find Peak Element — https://leetcode.com/problems/find-peak-element/
import java.util.*;

class LT_0162_Find_Peak_Element {
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        int lo = 0;
        int hi = len-1;

        while (lo < hi) {
            int mid = lo + (hi-lo)/2;
            int val = nums[mid];
            // 1 2 1
            if ( nums[mid] > nums[mid+1]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
            
        }

        return lo;
    }
}
