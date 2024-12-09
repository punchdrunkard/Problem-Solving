class Solution {
    public int maxSubArray(int[] nums) {
        int size = nums.length;
        int[] dp = new int[size];

        int result = nums[0];
        dp[0] = nums[0];

        for (int i = 1; i < size; i++) {
            // add element to previous seq or start from i-th index 
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            result = Math.max(result, dp[i]);
        }

        return result;
    }
}