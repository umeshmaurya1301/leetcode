// Fruit Into Baskets — https://leetcode.com/problems/fruit-into-baskets/
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
}
