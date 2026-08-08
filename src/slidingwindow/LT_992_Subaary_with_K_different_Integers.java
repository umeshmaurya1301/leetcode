// LT_992_Subaary_with_K_different_Integers
package slidingwindow;

import java.util.*;

class LT_992_Subaary_with_K_different_Integers {
 public int subarraysWithKDistinct(int[] nums, int k) {
     return atMost(nums, k) - atMost(nums, k - 1);
 }

 private int atMost(int[] nums, int limit) {
     if (limit < 0) return 0;

     int count = 0;
     Map<Integer, Integer> map = new HashMap<>();

     int i = 0;
     int j = 0;

     while (j < nums.length) {
         map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);

         while (map.size() > limit) {
             map.put(nums[i], map.get(nums[i]) - 1);
             if (map.get(nums[i]) == 0) {
                 map.remove(nums[i]);
             }
             i++;
         }

         count += j - i + 1;
         j++;
     }

     return count;
 }

    public static void main(String[] args) {
        LT_992_Subaary_with_K_different_Integers sol = new LT_992_Subaary_with_K_different_Integers();
        System.out.println(sol.subarraysWithKDistinct(new int[]{1, 2, 1, 2, 3}, 2)); // expected: 7
        System.out.println(sol.subarraysWithKDistinct(new int[]{1, 2, 1, 3, 4}, 3)); // expected: 3
    }
}
