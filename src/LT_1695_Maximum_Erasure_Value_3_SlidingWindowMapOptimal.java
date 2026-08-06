// LT_1695_Maximum_Erasure_Value
import java.util.*;

class LT_1695_Maximum_Erasure_Value_3_SlidingWindowMapOptimal {
    public int maximumUniqueSubarray(int[] nums) {
        int len = nums.length;
        if (len == 1) return nums[0];

        int i = 0;
        int j = 0;

        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int globalMax = 0;

        while (j < len) {
            int val = nums[j];

            if (map.containsKey(val) && map.get(val) >= i) {
                int prevIdx = map.get(val);

                // shrink window from left until i > prevIdx
                while (i <= prevIdx) {
                    sum -= nums[i];
                    i++;
                }
            }

            map.put(val, j);
            sum += val;
            globalMax = Math.max(globalMax, sum);
            j++;
        }

        return globalMax;
    }
}
