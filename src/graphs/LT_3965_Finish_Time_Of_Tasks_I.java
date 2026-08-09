/*
 * 3965. Finish Time Of Tasks I — Medium
 * https://leetcode.com/problems/finish-time-of-tasks-i/
 *
 * You manage n tasks (numbered 0 to n-1) organized as a tree rooted at task 0. The array edges of length n-1 defines parent-child relationships where edges[i] = [u, v] means task u is the parent of task v. Each task has a baseTime[i] representing its base completion duration.
 *
 * The finish time is defined recursively:
 * - Leaf task: finishTime = baseTime[i]
 * - Non-leaf task: Let earliest = min finish time among children, latest = max finish time among children.
 *   - ownDuration = (latest - earliest) + baseTime[i]
 *   - finishTime = latest + ownDuration
 *
 * Return the finish time of the root task 0.
 *
 * Example 1:
 *   Input:  n = 3, edges = [[0,1],[1,2]], baseTime = [9,5,3]
 *   Output: 17
 *   Explanation: Task 2 (leaf) → 3. Task 1 (one child, finish=3) → ownDuration=(3-3)+5=5, finish=8. Task 0 → ownDuration=(8-8)+9=9, finish=17.
 *
 * Example 2:
 *   Input:  n = 3, edges = [[0,1],[0,2]], baseTime = [4,7,6]
 *   Output: 12
 *   Explanation: Task 1 (leaf) → 7. Task 2 (leaf) → 6. Task 0: earliest=6, latest=7, ownDuration=(7-6)+4=5, finish=12.
 *
 * Example 3:
 *   Input:  n = 4, edges = [[0,1],[0,2],[2,3]], baseTime = [5,8,2,1]
 *   Output: 18
 *   Explanation: Task 3→1, Task 1→8, Task 2→3, Task 0: earliest=3, latest=8, ownDuration=(8-3)+5=10, finish=18.
 *
 * Constraints:
 *   1 <= n <= 10^5
 *   edges.length == n - 1
 *   Tree structure is valid (connected, acyclic)
 *   1 <= baseTime[i] <= 10^5
 *
 * Approach: DFS over the task tree, each node's finish time derived from its children's earliest and latest — O(n) time, O(n) space.
 */
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
