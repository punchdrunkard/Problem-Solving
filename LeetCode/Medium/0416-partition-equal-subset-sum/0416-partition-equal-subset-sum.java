class Solution {
    public boolean canPartition(int[] nums) {
        int totalSum = Arrays.stream(nums).sum();
        if (totalSum % 2 == 1) {
            return false;
        }

        int target = totalSum / 2;
        boolean[] dp = new boolean[totalSum + 1];
        dp[0] = true;

        for (int num: nums) {
            for (int i = target; i >= num; i--) {
                // 각 num 에 대해, 사용하거나 (dp[target - num], 사용하지 않거나 (dp[i])
                dp[i] = dp[i] || dp[i - num];
            }
        }


        return dp[target];
    }
}