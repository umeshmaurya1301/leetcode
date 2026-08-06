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
}
