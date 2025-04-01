class Solution {
    long[] memo;
    int[][] questions;
    int n;

    public long mostPoints(int[][] questions) {
        this.questions = questions;
        n = questions.length;
        memo = new long[n];
        Arrays.fill(memo, -1);
        return dp(0);
    }

    long dp(int idx) {
        // Base case
        if (idx >= n) {
            return 0;
        }

        if (memo[idx] != -1) {
            return memo[idx];
        }

        long solved = questions[idx][0] + dp(idx + questions[idx][1] + 1);
        long skip = dp(idx + 1);
        memo[idx] = Math.max(solved, skip);
        return memo[idx];
    }
}