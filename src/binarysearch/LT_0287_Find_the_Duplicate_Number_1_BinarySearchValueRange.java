/*
 * 287. Find the Duplicate Number — Medium
 * https://leetcode.com/problems/find-the-duplicate-number/
 *
 * Given an array of integers nums containing n + 1 integers where each integer is in the range
 * [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 *
 * You must solve the problem without modifying the array nums and using only constant extra space.
 *
 * Example 1:
 *   Input:  nums = [1,3,4,2,2]
 *   Output: 2
 *
 * Example 2:
 *   Input:  nums = [3,1,3,4,2]
 *   Output: 3
 *
 * Example 3:
 *   Input:  nums = [3,3,3,3,3]
 *   Output: 3
 *
 * Constraints:
 *   1 <= n <= 10^5
 *   nums.length == n + 1
 *   1 <= nums[i] <= n
 *   All the integers in nums appear only once except for precisely one integer which appears
 *   two or more times.
 *
 * Approach 1: Binary search on the value range [1, n]; for each candidate mid, count elements
 * <= mid and use the pigeonhole principle (count > mid means the duplicate is in [lo, mid]) to
 * halve the range — O(n log n) time, O(1) space.
 */
package binarysearch;

import java.util.*;

class LT_0287_Find_the_Duplicate_Number_1_BinarySearchValueRange {
    public int findDuplicate(int[] nums) {
        int len = nums.length;
        int lo = 0;
        int hi = len-1;

        while (lo < hi) {
            int mid = lo + (hi-lo)/2;
            int count = 0;
            for (int num : nums) {
                if (num<=mid) count++;
            }

            if (count > mid) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    public static void main(String[] args) {
        LT_0287_Find_the_Duplicate_Number_1_BinarySearchValueRange sol = new LT_0287_Find_the_Duplicate_Number_1_BinarySearchValueRange();
        System.out.println(sol.findDuplicate(new int[]{1,3,4,2,2})); // expected: 2
        System.out.println(sol.findDuplicate(new int[]{3,1,3,4,2})); // expected: 3
        System.out.println(sol.findDuplicate(new int[]{3,3,3,3,3})); // expected: 3
    }
}
