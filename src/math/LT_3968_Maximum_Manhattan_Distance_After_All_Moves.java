// Maximum Manhattan Distance After All Moves — https://leetcode.com/problems/maximum-manhattan-distance-after-all-moves/
package math;

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
