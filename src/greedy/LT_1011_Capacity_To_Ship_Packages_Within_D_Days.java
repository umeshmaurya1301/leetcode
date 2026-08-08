// Capacity To Ship Packages Within D Days — https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
package greedy;

import java.util.*;

class LT_1011_Capacity_To_Ship_Packages_Within_D_Days {
    public int shipWithinDays(int[] weights, int days) {
        int lo = 1;
        int hi = Arrays.stream(weights)
            .sorted()
            .sum();

        int capacity = hi;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            boolean isValid = isConditionValid(weights, mid, days);
            if (isValid) {
                capacity = Math.min (capacity, mid);
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return capacity;   
    }

    private boolean isConditionValid(int[] weights, int capacity, int days) {
        int currLoad = 0;
        int count = 1;
        for (int weight : weights) {
            if (weight > capacity) return false;
            if (currLoad + weight <= capacity) {
                currLoad += weight;
            } else {
                count++;
                currLoad = weight;
            }
        }
        return count <= days;
    }

    public static void main(String[] args) {
        LT_1011_Capacity_To_Ship_Packages_Within_D_Days sol = new LT_1011_Capacity_To_Ship_Packages_Within_D_Days();
        System.out.println(sol.shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5)); // expected: 15
        System.out.println(sol.shipWithinDays(new int[]{3, 2, 2, 4, 1, 4}, 3)); // expected: 6
    }
}
