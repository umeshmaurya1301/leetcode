// Non-overlapping Intervals — https://leetcode.com/problems/non-overlapping-intervals/
package arrays;

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
