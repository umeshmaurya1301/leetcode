// LT_1695_Maximum_Erasure_Value
package slidingwindow;

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

    public static void main(String[] args) {
        LT_1695_Maximum_Erasure_Value_3_SlidingWindowMapOptimal sol = new LT_1695_Maximum_Erasure_Value_3_SlidingWindowMapOptimal();
        System.out.println(sol.maximumUniqueSubarray(new int[]{4, 2, 4, 5, 6})); // expected: 17
        System.out.println(sol.maximumUniqueSubarray(new int[]{5, 2, 1, 2, 5, 2, 1, 2, 5})); // expected: 8
    }
}
