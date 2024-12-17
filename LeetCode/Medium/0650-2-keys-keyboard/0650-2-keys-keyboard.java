class Solution {

    static final int INF = Integer.MAX_VALUE; 
    int[] memo; 

    public int minSteps(int n) {
        memo = new int[n + 1]; 
        Arrays.fill(memo, -1); 
        return dp(n); 
    }

    int dp(int aCount) {
        // base case
        if (aCount == 1) {
            return 0;
        }

        if (memo[aCount] != -1) {
            return memo[aCount];
        }

        int minSteps = INF;

        // 약수들을 찾아서 현재 수를 조합하는 최소 비용 계산
        for (int i = 1; i <= aCount / 2; i++) {
            if (aCount % i == 0) { // i가 aCount의 약수라면
                int steps = dp(i) + (aCount / i); // i까지 만드는 비용 + i를 (aCount / i - 1)번 붙여넣기
                minSteps = Math.min(minSteps, steps); // 최소 비용 갱신
            }
        }

        memo[aCount] = minSteps;
        return memo[aCount];
    }
}
