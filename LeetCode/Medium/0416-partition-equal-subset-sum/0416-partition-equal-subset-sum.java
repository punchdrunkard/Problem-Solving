class Solution {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) {
            return false;
        }

        int target = sum / 2;

        // dp[i][j] := i번째 원소까지 고려했을 때, 합이 j를 만족시킬 수 있는가?
        boolean[][] dp = new boolean[nums.length][sum + 1];
        // base case
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true; // 전부 선택안하면 무조건 가능하므로
        }

        // 첫 번째 원소
        dp[0][nums[0]] = true;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }

        return dp[nums.length - 1][target];
    }
}