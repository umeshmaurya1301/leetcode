/*
 * 3964. Minimum Lights To Illuminate A Road — Medium
 * https://leetcode.com/problems/minimum-lights-to-illuminate-a-road/
 *
 * You are given a 0-indexed integer array lights of length n. If lights[i] != 0, a working bulb exists at position i that illuminates every position in [max(0, i - lights[i]), min(n-1, i + lights[i])], inclusive. A position is visible if illuminated by at least one working bulb.
 *
 * You may install additional bulbs at any positions. Each additional bulb installed at position j illuminates [max(0, j-1), min(n-1, j+1)] (range = 1), inclusive.
 *
 * Return the minimum number of additional bulbs required to make every position on the road visible.
 *
 * Example 1:
 *   Input:  lights = [0, 0, 0, 0]
 *   Output: 2
 *   Explanation: Place a bulb at index 1 (covers [0,2]) and index 3 (covers [2,3]). All 4 positions lit with 2 extra bulbs.
 *
 * Example 2:
 *   Input:  lights = [0, 0, 0, 2, 0]
 *   Output: 1
 *   Explanation: Existing bulb at index 3 with range 2 covers [1,4]. Only index 0 is dark — one extra bulb at index 0 covers [0,1].
 *
 * Constraints:
 *   1 <= lights.length <= 10^5
 *   0 <= lights[i] <= 10^5
 *
 * Approach: Difference array + prefix sum to mark what is already lit, then a greedy left-to-right placement — O(n) time, O(n) space.
 */
package arrays;

import java.util.*;

class LT_3964_Minimum_Lights_To_Illuminate_A_Road {
    public int minLights(int[] lights) {
        int len = lights.length;
        int[] diff = new int[len + 1];

        // O(1) per light instead of O(val) per light
        for (int i = 0; i < len; i++) {
            int val = lights[i];
            if (val > 0) {
                int start = Math.max(0, i - val);
                int end = Math.min(len - 1, i + val);
                diff[start]++;
                diff[end + 1]--;
            }
        }

        // prefix sum -> O(n) total to build the illuminated array
        int[] illuminated = new int[len];
        int running = 0;
        for (int i = 0; i < len; i++) {
            running += diff[i];
            illuminated[i] = running > 0 ? 1 : 0;
        }

        // unchanged: already O(n), since val is always 1 here
        int bulbs = 0;
        int i = 0;
        while (i < len) {
            int val = illuminated[i];
            if (val == 0) {
                if (i + 1 < len && illuminated[i + 1] == 0) {
                    updateIlluminated(illuminated, i + 1, 1);
                } else {
                    updateIlluminated(illuminated, i, 1);
                }
                bulbs++;
            }
            i++;
        }

        return bulbs;
    }

    private void updateIlluminated(int[] illuminated, int idx, int val) {
        int len = illuminated.length;
        for (int j = idx - val; j <= idx + val; j++) {
            if (j >= 0 && j < len) {
                illuminated[j] = 1;
            }
        }
    }

    public static void main(String[] args) {
        LT_3964_Minimum_Lights_To_Illuminate_A_Road sol = new LT_3964_Minimum_Lights_To_Illuminate_A_Road();
        System.out.println(sol.minLights(new int[]{0, 0, 0, 0})); // expected: 2
        System.out.println(sol.minLights(new int[]{0, 0, 0, 2, 0})); // expected: 1
    }
}
