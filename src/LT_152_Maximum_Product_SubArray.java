// Maximum Product SubArray — https://leetcode.com/problems/maximum-product-subarray/
import java.util.*;

class LT_152_Maximum_Product_SubArray {
    public int maxProduct(int[] nums) {
        
        int maxEnding = nums[0];
        int minEnding = nums[0];
        int answer = nums[0];

        for (int i=1; i<nums.length; i++) {
            int num = nums[i];

            int tempMax = Math.max( num, Math.max (maxEnding*num, minEnding*num) );
            int tempMin = Math.min( num, Math.min (maxEnding*num, minEnding*num) );

            maxEnding = tempMax;
            minEnding = tempMin;

            answer = Math.max (answer, maxEnding);
        }

        return answer;
    }

    public static void main(String[] args) {
        LT_152_Maximum_Product_SubArray sol = new LT_152_Maximum_Product_SubArray();
        System.out.println(sol.maxProduct(new int[]{2, 3, -2, 4})); // expected: 6
        System.out.println(sol.maxProduct(new int[]{-2, 0, -1})); // expected: 0
    }
}
