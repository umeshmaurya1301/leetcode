// First Missing Positive — https://leetcode.com/problems/first-missing-positive/description/
import java.util.*;

class LT_0041_First_Missing_Positive {

    public int firstMissingPositive(int[] nums) {

        int n = nums.length;

        for (int i = 0; i < n; i++) {

            while (
                nums[i] > 0 &&
                nums[i] <= n &&
                nums[i] != nums[nums[i] - 1]
            ) {

                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < n; i++) {

            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }

    private void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        LT_0041_First_Missing_Positive sol = new LT_0041_First_Missing_Positive();
        System.out.println(sol.firstMissingPositive(new int[]{1, 2, 0})); // expected: 3
        System.out.println(sol.firstMissingPositive(new int[]{3, 4, -1, 1})); // expected: 2
        System.out.println(sol.firstMissingPositive(new int[]{7, 8, 9, 11, 12})); // expected: 1
    }
}
