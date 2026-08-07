// Subarray Product Less Than K — https://leetcode.com/problems/subarray-product-less-than-k/
import java.util.*;

class LT_0713_Subarray_Product_Less_Than_K {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int left = 0;
        int len = nums.length;
        int count = 0;
        int prod = 1;
        if(k<=1) return 0;

        for(int right = 0; right<len; right++) {
            prod = prod * nums[right];

            while(prod >= k) {
                prod = prod/nums[left];
                left++;
            }
            count += right-left+1;
        }
        return count;
    }

    public static void main(String[] args) {
        LT_0713_Subarray_Product_Less_Than_K sol = new LT_0713_Subarray_Product_Less_Than_K();
        System.out.println(sol.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100)); // expected: 8
        System.out.println(sol.numSubarrayProductLessThanK(new int[]{1, 2, 3}, 0)); // expected: 0
    }
}
