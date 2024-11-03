class Solution {

    public int coinChange(int[] coins, int amount) {
        final int INF = amount + 1;

        // bottom up 
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, INF);

        // base case
        dp[0] = 0;

        // 모든 상태의 모든 값을 순환 
        for (int rest = 0; rest <= amount; rest++){
            // 모든 선택 (코인) 의 최솟값을 구함
            for (int coin: coins) {
                if (rest - coin < 0) {
                    continue;
                }

                dp[rest] = Math.min(dp[rest], dp[rest - coin] + 1);
            }   
        }

        return dp[amount] == INF ? -1 : dp[amount];
    }
}