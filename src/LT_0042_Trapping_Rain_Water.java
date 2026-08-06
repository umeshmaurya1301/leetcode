// Trapping Rain Water — https://leetcode.com/problems/trapping-rain-water/description/
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
