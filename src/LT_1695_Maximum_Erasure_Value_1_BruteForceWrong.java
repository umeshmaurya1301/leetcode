// LT_1695_Maximum_Erasure_Value
import java.util.*;

class LT_1695_Maximum_Erasure_Value_1_BruteForceWrong {
    public int maximumUniqueSubarray(int[] nums) {
        int len = nums.length;
        if (len == 1)
            return nums[0];
        int i = 0;
        int j = 1;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums[i], i);
        int sum = nums[i];
        int globalMax = Integer.MIN_VALUE;
        while (j < len) {
            int val = nums[j];
            if (map.containsKey(val)) {
                int prevIdx = map.get(val);
                int tempSum = 0;
                for (int idx = i; idx <= prevIdx; idx++)
                    tempSum += nums[idx];
                sum = sum - tempSum;
                i = prevIdx + 1;

                map.put(val, j);
                sum += val;
                j++;
            } else {
                map.put(val, j);
                j++;
                sum += val;
            }
            globalMax = Math.max(sum, globalMax);
        }
        return globalMax;
    }

    public static void main(String[] args) {
        LT_1695_Maximum_Erasure_Value_1_BruteForceWrong sol = new LT_1695_Maximum_Erasure_Value_1_BruteForceWrong();
        System.out.println(sol.maximumUniqueSubarray(new int[]{4, 2, 4, 5, 6})); // expected: 17
        System.out.println(sol.maximumUniqueSubarray(new int[]{5, 2, 1, 2, 5, 2, 1, 2, 5})); // expected: 8
    }
}
