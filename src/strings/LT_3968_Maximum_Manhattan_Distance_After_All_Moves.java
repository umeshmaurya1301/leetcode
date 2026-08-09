/*
 * 3968. Maximum Manhattan Distance After All Moves — Easy
 * https://leetcode.com/problems/maximum-manhattan-distance-after-all-moves/
 *
 * You are given a string moves consisting of characters 'U', 'D', 'L', 'R', and '_'. Starting at the origin (0, 0):
 * - 'U' moves up 1 unit (y++)
 * - 'D' moves down 1 unit (y--)
 * - 'L' moves left 1 unit (x--)
 * - 'R' moves right 1 unit (x++)
 * - '_' is a wildcard — you choose any one of the four directions
 *
 * Return the maximum Manhattan distance |x| + |y| achievable after performing all moves optimally.
 *
 * Example 1:
 *   Input:  moves = "L_D_"
 *   Output: 4
 *   Explanation: Replace the two _s with 'D' and 'L' respectively. Path reaches (-2, -2), giving distance 2 + 2 = 4.
 *
 * Example 2:
 *   Input:  moves = "U_R"
 *   Output: 3
 *   Explanation: Replace _ with 'U'. Path reaches (1, 2), giving distance 1 + 2 = 3.
 *
 * Constraints:
 *   1 <= moves.length <= 10^5
 *   moves consists only of 'U', 'D', 'L', 'R', '_'
 *
 * Approach: Single pass for the net displacement, then spend every wildcard outward — O(n) time, O(1) space.
 */
package strings;

import java.util.*;

class LT_3968_Maximum_Manhattan_Distance_After_All_Moves {
    public int maxDistance(String moves) {
        int x = 0, y = 0, underscore = 0;
        
        for (char c : moves.toCharArray()) {
            switch (c) {
                case 'U': y++; break;
                case 'D': y--; break;
                case 'L': x--; break;
                case 'R': x++; break;
                case '_': underscore++; break;
            }
        }
        
        return Math.abs(x) + Math.abs(y) + underscore;
    }

    public static void main(String[] args) {
        LT_3968_Maximum_Manhattan_Distance_After_All_Moves sol = new LT_3968_Maximum_Manhattan_Distance_After_All_Moves();
        System.out.println(sol.maxDistance("L_D_")); // expected: 4
        System.out.println(sol.maxDistance("U_R")); // expected: 3
    }
}
