// Combination Sum IV — https://leetcode.com/problems/combination-sum-iv/description/
import java.util.*;

class LT_0377_Combination_Sum_IV {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        Arrays.fill(dp, -1);
        dfs(nums, dp, target);
        return dp[target];
    }

    private int dfs(int[] nums, int[] dp, int target) {
        if (target==0) return 1;
        if (target < 0) return 0;
        if (dp[target]!=-1) return dp[target];
        
        int ways = 0;
        for (int num : nums) {
            ways += dfs(nums, dp, target-num);
        }
        
        return dp[target] = ways;
    }
}
