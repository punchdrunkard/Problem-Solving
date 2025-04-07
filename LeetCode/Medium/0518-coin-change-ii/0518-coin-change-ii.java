class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // dp[i][cost] := 동전 i개 사용, cost 를 만들 수 있는 경우의 수
        int[][] dp = new int[n + 1][amount + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;

            for (int cost = 1; cost <= amount; cost++) {
                dp[i][cost] += dp[i - 1][cost];

                if (cost - coins[i - 1] >= 0) {
                    dp[i][cost] += dp[i][cost - coins[i - 1]];
                }
            }
        }

        return dp[n][amount];
    }
}