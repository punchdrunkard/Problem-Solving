class Solution {
    
    public int lengthOfLIS(int[] nums) {
        int[] dp = initDp(nums.length);
        calculateLIS(nums, dp);
        return findMax(dp);
    }

    int[] initDp(int size) {
        int[] dp = new int[size];
        Arrays.fill(dp, 1);
        return dp;
    }

    void calculateLIS(int[] nums, int[] dp) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
    }

     int findMax(int[] dp) {
        return Arrays.stream(dp).max().orElse(1);
    }
}