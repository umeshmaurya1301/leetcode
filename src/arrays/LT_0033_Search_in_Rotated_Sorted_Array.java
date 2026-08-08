// Search in Rotated Sorted Array — https://leetcode.com/problems/search-in-rotated-sorted-array/
package arrays;

import java.util.*;

class LT_0033_Search_in_Rotated_Sorted_Array {
    public int search(int[] nums, int target) {
        int len = nums.length;
        int lo = 0;
        int hi = len-1;

        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            int val = nums[mid];
            if (val == target) return mid;
            if (nums[lo] <= val) {

                if (nums[lo] <= target && target < val) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }

            } else {

                if (target>val && target<=nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        LT_0033_Search_in_Rotated_Sorted_Array sol = new LT_0033_Search_in_Rotated_Sorted_Array();
        System.out.println(sol.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0)); // expected: 4
        System.out.println(sol.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3)); // expected: -1
    }
}
