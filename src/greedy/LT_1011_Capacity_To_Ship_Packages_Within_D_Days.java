/*
 * 1011. Capacity To Ship Packages Within D Days — Medium
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
 *
 * A conveyor belt has packages that must be shipped from one port to another within days days.
 *
 * The i-th package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
 *
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.
 *
 * Example 1:
 *   Input:  weights = [1,2,3,4,5,6,7,8,9,10], days = 5
 *   Output: 15
 *   Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 *   1st day: 1, 2, 3, 4, 5
 *   2nd day: 6, 7
 *   3rd day: 8
 *   4th day: 9
 *   5th day: 10
 *   Note that the cargo must be shipped in the given order, so using a ship of capacity 14 and splitting the packages into parts like (2,3,4,5), (1,6,7), (8), (9), (10) is not allowed.
 *
 * Example 2:
 *   Input:  weights = [3,2,2,4,1,4], days = 3
 *   Output: 6
 *   Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
 *   1st day: 3, 2
 *   2nd day: 2, 4
 *   3rd day: 1, 4
 *
 * Example 3:
 *   Input:  weights = [1,2,3,1,1], days = 4
 *   Output: 3
 *   Explanation:
 *   1st day: 1
 *   2nd day: 2
 *   3rd day: 3
 *   4th day: 1, 1
 *
 * Constraints:
 *   1 <= days <= weights.length <= 5 * 10^4
 *   1 <= weights[i] <= 500
 *
 * Approach: Binary search the capacity answer space, greedily simulating the days for each candidate — O(n log sum) time, O(1) space.
 */
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
