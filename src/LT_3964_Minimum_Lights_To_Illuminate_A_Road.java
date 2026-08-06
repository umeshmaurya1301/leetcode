// Minimum Lights To Illuminate A Road — https://leetcode.com/problems/minimum-lights-to-illuminate-a-road/
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
}
