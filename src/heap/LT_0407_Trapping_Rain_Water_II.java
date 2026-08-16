/*
 * 407. Trapping Rain Water II — Hard
 * https://leetcode.com/problems/trapping-rain-water-ii/
 *
 * Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D
 * elevation map, return the volume of water it can trap after raining.
 *
 * Example 1:
 *   Input:  heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 *   Output: 4
 *   Explanation: After the rain, water is trapped between the blocks. There are two small ponds
 *                — 1 and 3 units trapped. Total volume trapped is 4.
 *
 * Example 2:
 *   Input:  heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 *   Output: 10
 *
 * Constraints:
 *   m == heightMap.length
 *   n == heightMap[i].length
 *   1 <= m, n <= 200
 *   0 <= heightMap[i][j] <= 2 * 10^4
 *
 * Approach: Min-heap BFS from the grid boundary inward (Dijkstra-style). Seed every boundary
 * cell into a min-heap; repeatedly pop the smallest height, track the running maxValue as the
 * flood's effective water level, and for each newly visited neighbor trap max(0, maxValue -
 * height) then push it back with effective height max(height, maxValue) — O(m*n*log(m*n)) time,
 * O(m*n) space.
 */
package heap;

import java.util.*;

class LT_0407_Trapping_Rain_Water_II {
    private class Entry {
        int h, i, j;

        Entry(int h, int i, int j) {
            this.h = h;
            this.i = i;
            this.j = j;
        }
    }

    public int trapRainWater(int[][] heightMap) {
        int rows = heightMap.length;
        int cols = heightMap[0].length;
        PriorityQueue<Entry> queue = new PriorityQueue<>((a, b) -> a.h - b.h);
        boolean[][] visited = new boolean[rows][cols];
        int[][] directions = {
            {0, -1}, {0, 1}, {-1, 0}, {1, 0}
        };

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isOnBoundary(i, j, rows, cols)) {
                    queue.offer(new Entry(heightMap[i][j], i, j));
                    visited[i][j] = true;
                }
            }
        }

        int maxValue = 0;
        int trapWater = 0;

        while (!queue.isEmpty()) {
            Entry entry = queue.poll();
            maxValue = Math.max(maxValue, entry.h);

            for (int[] dir : directions) {
                int newX = entry.i + dir[0];
                int newY = entry.j + dir[1];

                if (isValid(newX, newY, rows, cols) && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    int currentHeight = heightMap[newX][newY];
                    if (currentHeight < maxValue) {
                        trapWater += (maxValue - currentHeight);
                    }

                    queue.offer(new Entry(Math.max(currentHeight, maxValue), newX, newY));
                }
            }
        }

        return trapWater;
    }

    private boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && y >= 0 && x < rows && y < cols;
    }

    private boolean isOnBoundary(int i, int j, int rows, int cols) {
        return i == 0 || j == 0 || i == rows - 1 || j == cols - 1;
    }

    public static void main(String[] args) {
        LT_0407_Trapping_Rain_Water_II sol = new LT_0407_Trapping_Rain_Water_II();
        int[][] map1 = {{1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}};
        System.out.println(sol.trapRainWater(map1)); // expected: 4

        int[][] map2 = {{3,3,3,3,3},{3,2,2,2,3},{3,2,1,2,3},{3,2,2,2,3},{3,3,3,3,3}};
        System.out.println(sol.trapRainWater(map2)); // expected: 10
    }
}
