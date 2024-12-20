class Solution {
    static int[] balloons;

    public int maxCoins(int[] nums) {
        init(nums);
        return solve();
    }

    public int solve() {
        int n = balloons.length;
        int[][] dp = new int[n][n];

        for (int len = 2; len < n; len++) { // 구간 크기: 최소 3부터 시작
            for (int st = 0; st < n - len; st++) {
                int en = st + len;

                dp[st][en] = 0; // 초기화

                // 중간 풍선을 마지막으로 터뜨릴 때의 최대 점수 계산
                for (int mid = st + 1; mid < en; mid++) {
                    dp[st][en] = Math.max(dp[st][en],
                            dp[st][mid] + dp[mid][en] + balloons[st] * balloons[mid] * balloons[en]);
                }
            }
        }

        return dp[0][n - 1];
    }

    void init(int[] nums) {
        int n = nums.length;
        balloons = new int[n + 2];
        balloons[0] = 1;
        balloons[n + 1] = 1;

        for (int i = 0; i < n; i++) {
            balloons[i + 1] = nums[i];
        }
    }
}
