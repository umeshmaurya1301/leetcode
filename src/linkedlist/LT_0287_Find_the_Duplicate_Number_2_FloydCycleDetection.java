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
 * Approach 2: Treat index i -> nums[i] as a linked-list next pointer. n+1 indices mapping into
 * only n values forces a cycle, and the duplicate is exactly the cycle's entrance — found with
 * Floyd's Tortoise and Hare (same algorithm as Linked List Cycle II) — O(n) time, O(1) space.
 */
package linkedlist;

import java.util.*;

class LT_0287_Find_the_Duplicate_Number_2_FloydCycleDetection {
    public int findDuplicate(int[] nums) {

        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = nums[0];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    public static void main(String[] args) {
        LT_0287_Find_the_Duplicate_Number_2_FloydCycleDetection sol = new LT_0287_Find_the_Duplicate_Number_2_FloydCycleDetection();
        System.out.println(sol.findDuplicate(new int[]{1,3,4,2,2})); // expected: 2
        System.out.println(sol.findDuplicate(new int[]{3,1,3,4,2})); // expected: 3
        System.out.println(sol.findDuplicate(new int[]{3,3,3,3,3})); // expected: 3
    }
}
