/*
 * 42. Trapping Rain Water — Hard
 * https://leetcode.com/problems/trapping-rain-water/description/
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 *
 * Example 1:
 *   Input:  height = [0,1,0,2,1,0,1,3,2,1,2,1]
 *   Output: 6
 *   Explanation: The elevation map traps 6 units of rain water in the valleys between bars.
 *
 * Example 2:
 *   Input:  height = [4,2,0,3,2,5]
 *   Output: 9
 *
 * Constraints:
 *   n == height.length
 *   1 <= n <= 2 * 10^4
 *   0 <= height[i] <= 10^5
 *
 * Approach: Two pointers moving inward from the shorter side, carrying leftMax / rightMax — O(n) time, O(1) space.
 */
package arrays;

import java.util.*;

class LT_0042_Trapping_Rain_Water {
    public int trap(int[] height) {
        int len=height.length;
        int left=0;
        int right=len-1;

        int leftMax=height[0];
        int rightMax=height[len-1];

        int water=0;

        while (left<=right) {
            if(height[left] <= height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                water += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                water += rightMax - height[right];
                right--;
            }
        }
        return water;
    }

    public static void main(String[] args) {
        LT_0042_Trapping_Rain_Water sol = new LT_0042_Trapping_Rain_Water();
        System.out.println(sol.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1})); // expected: 6
        System.out.println(sol.trap(new int[]{4, 2, 0, 3, 2, 5})); // expected: 9
    }
}
