// LT_1695_Maximum_Erasure_Value
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
}
