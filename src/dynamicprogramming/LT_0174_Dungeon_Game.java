/*
 * 174. Dungeon Game — Hard
 * https://leetcode.com/problems/dungeon-game/
 *
 * The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of m x n rooms laid out in a 2D grid. Our valiant knight was initially
 * positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
 *
 * The knight has an initial health point represented by a positive integer. If at any point his
 * health point drops to 0 or below, he dies immediately.
 *
 * Some of the rooms are guarded by demons (represented by negative integers), so the knight loses
 * health upon entering these rooms; other rooms are either empty (represented as 0) or contain
 * magic orbs that increase the knight's health (represented by positive integers).
 *
 * To reach the princess as quickly as possible, the knight decides to move only rightward or
 * downward in each step. Return the knight's minimum initial health so that he can rescue the
 * princess. Any room can contain threats or power-ups, even the first and the last room.
 *
 * Example 1:
 *   Input:  dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
 *   Output: 7
 *   Explanation: The initial health of the knight must be at least 7 if he follows the optimal
 *                path: RIGHT -> RIGHT -> DOWN -> DOWN.
 *
 * Example 2:
 *   Input:  dungeon = [[0]]
 *   Output: 1
 *
 * Constraints:
 *   m == dungeon.length
 *   n == dungeon[i].length
 *   1 <= m, n <= 200
 *   -1000 <= dungeon[i][j] <= 1000
 *
 * Approach: Backward tabulation where memo[i][j] is the minimum health needed upon ENTERING (i,j)
 *           to survive to the princess: memo[i][j] = max(1, min(down, right) - dungeon[i][j]).
 *           Going backwards is what makes the state self-contained — a forward "accumulate health"
 *           DP cannot express the survival floor — O(m*n) time, O(m*n) space.
 */
package dynamicprogramming;

import java.util.*;

class LT_0174_Dungeon_Game {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        int[][] memo = new int[m][n];
        int lastVal = dungeon[m-1][n-1];
        if (lastVal<0) {
            memo[m-1][n-1] = -1 * lastVal + 1;
        } else {
            memo[m-1][n-1] = 1;
        }

        for (int i=n-2; i>=0; i--) {
            if (dungeon[m-1][i] >= memo[m-1][i+1]) {
                memo[m-1][i] = 1;
            } else {
                memo[m-1][i] = memo[m-1][i+1] - dungeon[m-1][i];
            }
        }

        for (int i=m-2; i>=0; i--) {
            if (dungeon[i][n-1] >= memo[i+1][n-1]) {
                memo[i][n-1] = 1;
            } else {
                memo[i][n-1] = memo[i+1][n-1] - dungeon[i][n-1];
            }
        }

        for (int i=m-2; i>=0; i--) {
            for (int j=n-2; j>=0; j--) {
                int val = Math.min (memo[i+1][j], memo[i][j+1]);
                memo[i][j] = Math.max(1, val - dungeon[i][j]);
            }
        }

        System.out.println(Arrays.deepToString(memo));
        return memo[0][0];
    }

    public static void main(String[] args) {
        LT_0174_Dungeon_Game sol = new LT_0174_Dungeon_Game();
        System.out.println(sol.calculateMinimumHP(new int[][]{{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}})); // expected: 7
        System.out.println(sol.calculateMinimumHP(new int[][]{{0}})); // expected: 1
    }

}
