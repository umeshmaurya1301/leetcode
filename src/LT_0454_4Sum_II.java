// 4Sum II — https://leetcode.com/problems/4sum-ii/description/
import java.util.*;

class LT_0454_4Sum_II {

    public int fourSumCount(
            int[] nums1,
            int[] nums2,
            int[] nums3,
            int[] nums4) {

        Map<Integer, Integer> map = new HashMap<>();

        // Store all sums of nums1 + nums2
        for (int a : nums1) {
            for (int b : nums2) {

                int sum = a + b;

                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int count = 0;

        // Find complements from nums3 + nums4
        for (int c : nums3) {
            for (int d : nums4) {

                int target = -(c + d);

                count += map.getOrDefault(target, 0);
            }
        }

        return count;
    }
}
