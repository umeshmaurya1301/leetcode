/*
 * 435. Non-overlapping Intervals — Medium
 * https://leetcode.com/problems/non-overlapping-intervals/
 *
 * Given an array of intervals intervals where intervals[i] = [start_i, end_i], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 * Note that intervals which only touch at a point are non-overlapping — e.g. [1, 2] and [2, 3] are non-overlapping.
 *
 * Example 1:
 *   Input:  intervals = [[1,2],[2,3],[3,4],[1,3]]
 *   Output: 1
 *   Explanation: Remove [1,3] and the remaining intervals are non-overlapping.
 *
 * Example 2:
 *   Input:  intervals = [[1,2],[1,2],[1,2]]
 *   Output: 2
 *   Explanation: You need to remove two [1,2] intervals so only one remains.
 *
 * Example 3:
 *   Input:  intervals = [[1,2],[2,3]]
 *   Output: 0
 *   Explanation: Already non-overlapping — touching at 2 is allowed.
 *
 * Constraints:
 *   1 <= intervals.length <= 10^5
 *   intervals[i].length == 2
 *   -5  10^4 <= start_i < end_i <= 5  10^4
 *
 * Approach: Sort by end time and greedily keep the earliest finisher, counting the rest as removals — O(n log n) time, O(1) extra space.
 */
package sorting;

import java.util.*;

class LT_0435_Non_Overlapping_Intervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[1] - b[1]);
        int removed = 0;
        int prevEnd = intervals[0][1];

        for (int i=1; i<intervals.length; i++) {
            if (intervals[i][0]  < prevEnd) {
                removed++;
            } else {
                prevEnd = intervals[i][1];
            }
        }

        return removed;
    }

    public static void main(String[] args) {
        LT_0435_Non_Overlapping_Intervals sol = new LT_0435_Non_Overlapping_Intervals();
        System.out.println(sol.eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}})); // expected: 1
        System.out.println(sol.eraseOverlapIntervals(new int[][]{{1, 2}, {1, 2}, {1, 2}})); // expected: 2
        System.out.println(sol.eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}})); // expected: 0
    }
}
