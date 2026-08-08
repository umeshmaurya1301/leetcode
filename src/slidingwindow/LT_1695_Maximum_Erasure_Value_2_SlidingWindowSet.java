// LT_1695_Maximum_Erasure_Value
package slidingwindow;

import java.util.*;

class LT_1695_Maximum_Erasure_Value_2_SlidingWindowSet {
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        int left = 0, right = 0;
        int sum = 0, max = 0;
        Set<Integer> set = new HashSet<>();

        while (right < n) {
            if (!set.contains(nums[right])) {
                set.add(nums[right]);
                sum += nums[right];
                max = Math.max(max, sum);
                right++;
            } else {
                set.remove(nums[left]);
                sum -= nums[left];
                left++;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        LT_1695_Maximum_Erasure_Value_2_SlidingWindowSet sol = new LT_1695_Maximum_Erasure_Value_2_SlidingWindowSet();
        System.out.println(sol.maximumUniqueSubarray(new int[]{4, 2, 4, 5, 6})); // expected: 17
        System.out.println(sol.maximumUniqueSubarray(new int[]{5, 2, 1, 2, 5, 2, 1, 2, 5})); // expected: 8
    }
}
