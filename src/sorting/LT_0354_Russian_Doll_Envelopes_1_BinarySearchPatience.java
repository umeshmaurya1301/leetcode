/*
 * 354. Russian Doll Envelopes — Hard
 * https://leetcode.com/problems/russian-doll-envelopes/
 *
 * You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.
 *
 * One envelope can fit into another if and only if both the width and height of one envelope are greater than the other envelope's width and height (both strict).
 *
 * Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
 *
 * Note: You cannot rotate an envelope.
 *
 * Example 1:
 *   Input:  envelopes = [[5,4],[6,4],[6,7],[2,3]]
 *   Output: 3
 *   Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 *
 * Example 2:
 *   Input:  envelopes = [[1,1],[1,1],[1,1]]
 *   Output: 1
 *   Explanation: Every envelope is identical, so none can strictly fit inside another.
 *
 * Constraints:
 *   1 <= envelopes.length <= 10^5
 *   envelopes[i].length == 2
 *   1 <= wi, hi <= 10^5
 *
 * Approach 1: Sort by width ascending / height descending, then LIS by binary search over heights — O(n log n) time, O(n) space.
 */
package sorting;

import java.util.*;

class LT_0354_Russian_Doll_Envelopes_1_BinarySearchPatience {
    public int maxEnvelopes(int[][] envelopes) {
        /*
        1  4
        2  2
        3  3
        4  4
        5  7
        5  6
        5  5
        6  9
        */
        Arrays.sort(envelopes, (a,b) -> {
            if (a[0]==b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });

        return lis(envelopes);

    }

    private int lis (int[][] nums) {
        int row = nums.length;
        int col = nums[0].length;
        List<Integer> tail = new ArrayList<>();

        for (int i=0; i<row; i++) {
            int num = nums[i][col-1];
            int left = 0;
            int right = tail.size();

            while (left < right) {
                int mid = left + (right - left)/2;
                if (tail.get(mid) < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            if (left==tail.size()) {
                tail.add(num);
            } else {
                tail.set(left, num);
            }
        }

        return tail.size();
    }

    public static void main(String[] args) {
        LT_0354_Russian_Doll_Envelopes_1_BinarySearchPatience sol = new LT_0354_Russian_Doll_Envelopes_1_BinarySearchPatience();
        System.out.println(sol.maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}})); // expected: 3
        System.out.println(sol.maxEnvelopes(new int[][]{{1, 1}, {1, 1}, {1, 1}})); // expected: 1
    }
}
