/*
 * 4. Median of Two Sorted Arrays — Hard
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log(m + n)).
 *
 * Example 1:
 *   Input:  nums1 = [1,3], nums2 = [2]
 *   Output: 2.00000
 *   Explanation: merged array = [1,2,3] and median is 2.
 *
 * Example 2:
 *   Input:  nums1 = [1,2], nums2 = [3,4]
 *   Output: 2.50000
 *   Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 *
 * Constraints:
 *   nums1.length == m, nums2.length == n
 *   0 <= m, n <= 1000
 *   1 <= m + n <= 2000
 *   -10^6 <= nums1[i], nums2[i] <= 10^6
 *
 * Approach: Binary search the partition of the smaller array — O(log min(m, n)) time, O(1) space.
 */
package binarysearch;

import java.util.*;

class LT_0004_Median_of_Two_Sorted_Arrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // Always binary search on smaller array
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int low = 0, high = m;

        while (low <= high) {
            int mid1 = low + (high - low) / 2;
            int mid2 = (m + n + 1) / 2 - mid1;

            int l1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[mid1 - 1];
            int l2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[mid2 - 1];
            int r1 = (mid1 == m) ? Integer.MAX_VALUE : nums1[mid1];
            int r2 = (mid2 == n) ? Integer.MAX_VALUE : nums2[mid2];

            if (l1 <= r2 && l2 <= r1) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else {
                    return Math.max(l1, l2);
                }
            } else if (l1 > r2) {
                high = mid1 - 1;
            } else {
                low = mid1 + 1;
            }
        }

        return 0.0;
    }

    public static void main(String[] args) {
        LT_0004_Median_of_Two_Sorted_Arrays sol = new LT_0004_Median_of_Two_Sorted_Arrays();
        System.out.println(sol.findMedianSortedArrays(new int[]{1, 3}, new int[]{2})); // expected: 2.00000
        System.out.println(sol.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4})); // expected: 2.50000
    }
}
