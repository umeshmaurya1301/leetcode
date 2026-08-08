// Finish Time Of Tasks I — https://leetcode.com/problems/finish-time-of-tasks-i/
package graphs;

import java.util.*;

class LT_3965_Finish_Time_Of_Tasks_I {
    private List<List<Integer>> children;
    private int[] baseTime;

    public long finishTime(int n, int[][] edges, int[] baseTime) {
        this.baseTime = baseTime;
        children = new ArrayList<>();
        for (int i = 0; i < n; i++) children.add(new ArrayList<>());
        for (int[] e : edges) {
            children.get(e[0]).add(e[1]); // parent -> child
        }
  
        return dfs(0);
    }

    private long dfs(int node) {

        List<Integer> kids = children.get(node);
        if (kids.isEmpty()) {
            return baseTime[node]; // leaf case
        }
    
        long earliest = Long.MAX_VALUE, latest = Long.MIN_VALUE;
        for (int child : kids) {
            long childFinish = dfs(child);
            earliest = Math.min(earliest, childFinish);
            latest = Math.max(latest, childFinish);
        }
        long ownDuration = (latest - earliest) + baseTime[node];
        return latest + ownDuration;
    }

    public static void main(String[] args) {
        LT_3965_Finish_Time_Of_Tasks_I sol = new LT_3965_Finish_Time_Of_Tasks_I();
        System.out.println(sol.finishTime(3, new int[][]{{0, 1}, {1, 2}}, new int[]{9, 5, 3})); // expected: 17
        System.out.println(sol.finishTime(3, new int[][]{{0, 1}, {0, 2}}, new int[]{4, 7, 6})); // expected: 12
        System.out.println(sol.finishTime(4, new int[][]{{0, 1}, {0, 2}, {2, 3}}, new int[]{5, 8, 2, 1})); // expected: 18
    }
}
