/*
 * 41. First Missing Positive — Hard
 * https://leetcode.com/problems/first-missing-positive/description/
 *
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 *
 * You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
 *
 * Example 1:
 *   Input:  nums = [1,2,0]
 *   Output: 3
 *   Explanation: The numbers in the range [1,2] are all in the array.
 *
 * Example 2:
 *   Input:  nums = [3,4,-1,1]
 *   Output: 2
 *   Explanation: 1 is in the array but 2 is missing.
 *
 * Example 3:
 *   Input:  nums = [7,8,9,11,12]
 *   Output: 1
 *   Explanation: The smallest positive integer 1 is missing.
 *
 * Constraints:
 *   1 <= nums.length <= 10^5
 *   -2^31 <= nums[i] <= 2^31 - 1
 *
 * Approach: Cyclic sort — swap each value v into index v-1, then scan for the first mismatch — O(n) time, O(1) space.
 */
package arrays;

import java.util.*;

class LT_0041_First_Missing_Positive {

    public int firstMissingPositive(int[] nums) {

        int n = nums.length;

        for (int i = 0; i < n; i++) {

            while (
                nums[i] > 0 &&
                nums[i] <= n &&
                nums[i] != nums[nums[i] - 1]
            ) {

                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < n; i++) {

            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }

    private void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        LT_0041_First_Missing_Positive sol = new LT_0041_First_Missing_Positive();
        System.out.println(sol.firstMissingPositive(new int[]{1, 2, 0})); // expected: 3
        System.out.println(sol.firstMissingPositive(new int[]{3, 4, -1, 1})); // expected: 2
        System.out.println(sol.firstMissingPositive(new int[]{7, 8, 9, 11, 12})); // expected: 1
    }
}
