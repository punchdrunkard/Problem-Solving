class Solution {
    final int MOD = 1_000_000_007;

    public int numTilings(int n) {
        // dp[i] = 2 * i 타일을 채우는 경우의 수 
        int[] dp = new int[1001];
        // base case
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;

        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[1] * dp[i - 1]) + (dp[2] * dp[i - 2])
                    + (dp[3] * dp[i - 3]);

            dp[i] %= MOD;
        }

        return dp[n] % MOD;
    }
}