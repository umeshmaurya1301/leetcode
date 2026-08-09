/*
 * 904. Fruit Into Baskets — Medium
 * https://leetcode.com/problems/fruit-into-baskets/
 *
 * You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
 *
 * You want to collect as much fruit as possible. However, the owner has some rules:
 * - You only have two baskets, and each basket can only hold a single type of fruit. No limit on amount per basket.
 * - Starting from any tree of your choice, you must pick exactly one fruit from every tree while moving to the right. The picked fruits must fit in one of your two baskets.
 * - Once you reach a tree whose fruit doesn't fit in your two baskets, you must stop.
 *
 * Return the maximum number of fruits you can pick.
 *
 * Example 1:
 *   Input:  fruits = [1,2,1]
 *   Output: 3
 *   Explanation: We can pick from all 3 trees.
 *
 * Example 2:
 *   Input:  fruits = [0,1,2,2]
 *   Output: 3
 *   Explanation: We can pick from trees [1,2,2]. Starting at the first tree only gives [0,1].
 *
 * Example 3:
 *   Input:  fruits = [1,2,3,2,2]
 *   Output: 4
 *   Explanation: We can pick from trees [2,3,2,2]. Starting at the first tree only gives [1,2].
 *
 * Constraints:
 *   1 <= fruits.length <= 10^5
 *   0 <= fruits[i] < fruits.length
 *
 * Approach: Sliding window with a frequency map, shrinking while more than 2 fruit types — O(n) time, O(1) space.
 */
package hashing;

import java.util.*;

class LT_0904_Fruit_Into_Baskets {
    public int totalFruit(int[] fruits) {
        int left = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        
        int len = fruits.length;
        int right = 0;
        int max = Integer.MIN_VALUE;

        while(right < len) {
            int fruit = fruits[right];           
            freq.merge(fruit, 1, Integer::sum);

            while(freq.size() > 2) {
                int leftFruit = fruits[left];
                freq.merge(leftFruit, -1, Integer::sum);
                if (freq.get(leftFruit) == 0) {
                    freq.remove(leftFruit);
                }
                left++;
            }
            max = Math.max(max, right - left + 1);
            right++;
        }

        return max;
    }

    public static void main(String[] args) {
        LT_0904_Fruit_Into_Baskets sol = new LT_0904_Fruit_Into_Baskets();
        System.out.println(sol.totalFruit(new int[]{1, 2, 1})); // expected: 3
        System.out.println(sol.totalFruit(new int[]{0, 1, 2, 2})); // expected: 3
        System.out.println(sol.totalFruit(new int[]{1, 2, 3, 2, 2})); // expected: 4
    }
}
