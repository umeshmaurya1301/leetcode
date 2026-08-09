/*
 * 33. Search in Rotated Sorted Array — Medium
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 *   Input:  nums = [4,5,6,7,0,1,2], target = 0
 *   Output: 4
 *   Explanation: 0 sits at index 4 in the rotated array.
 *
 * Example 2:
 *   Input:  nums = [4,5,6,7,0,1,2], target = 3
 *   Output: -1
 *   Explanation: 3 is not present anywhere in nums.
 *
 * Example 3:
 *   Input:  nums = [1], target = 0
 *   Output: -1
 *   Explanation: Single-element array that does not contain the target.
 *
 * Constraints:
 *   1 <= nums.length <= 5000
 *   -10^4 <= nums[i] <= 10^4
 *   All values of nums are unique
 *   nums is an ascending array that is possibly rotated
 *   -10^4 <= target <= 10^4
 *
 * Approach: Binary search, deciding each step which half is the sorted one — O(log n) time, O(1) space.
 */
package binarysearch;

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
