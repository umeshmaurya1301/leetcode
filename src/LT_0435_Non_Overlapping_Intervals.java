// Non-overlapping Intervals — https://leetcode.com/problems/non-overlapping-intervals/
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
}
