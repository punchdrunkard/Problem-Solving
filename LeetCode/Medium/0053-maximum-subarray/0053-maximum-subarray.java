class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        // dp[i] := nums[i]를 반드시 포함하는 Maximum Subarray
        int[] dp = new int[n];
        int max = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            max = Math.max(dp[i], max);
        }

        return max;
    }
}