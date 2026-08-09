class Solution {
    public int maxProduct(int[] nums) {
        
        int maxEnding = nums[0];
        int minEnding = nums[0];
        int answer = nums[0];

        for (int i=1; i<nums.length; i++) {
            int num = nums[i];

            int tempMax = Math.max( num, Math.max (maxEnding*num, minEnding*num) );
            int tempMin = Math.min( num, Math.min (maxEnding*num, minEnding*num) );

            maxEnding = tempMax;
            minEnding = tempMin;

            answer = Math.max (answer, maxEnding);
        }

        return answer;
    }
}