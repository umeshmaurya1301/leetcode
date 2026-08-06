// Count K-th Roots in a Range — https://leetcode.com/problems/count-k-th-roots-in-a-range/
import java.util.*;

class LT_3932_Count_K_th_Roots_in_a_Range {
    public int countKthRoots(int l, int r, int k) {
        // Find smallest x where x^k >= l
        int minX = (int) Math.ceil(Math.pow(l, 1.0 / k));
        // Find largest x where x^k <= r
        int maxX = (int) Math.floor(Math.pow(r, 1.0 / k));

        // Adjust for floating point errors
        // Walk minX down if needed
        while (pow(minX, k) < l) minX++;
        while (minX > 1 && pow(minX - 1, k) >= l) minX--;

        // Walk maxX up if needed  
        while (pow(maxX + 1, k) <= r) maxX++;
        while (pow(maxX, k) > r) maxX--;

        return maxX >= minX ? maxX - minX + 1 : 0;
    }

    // Safe integer power to avoid overflow — use long
    private long pow(long base, int exp) {
        long result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
            if (result > (long) 2e9) return (long) 2e9; // cap to avoid overflow
        }
        return result;
    }
}
