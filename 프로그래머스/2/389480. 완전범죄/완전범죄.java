class Solution {
    int[][] info;
    int n, m;
    int answer = Integer.MAX_VALUE;
    // 메모이제이션 배열: [물건 인덱스][A 흔적 개수][B 흔적 개수]
    int[][][] memo;
    
    public int solution(int[][] info, int n, int m) {
        init(info, n, m);
        int result = solve(0, 0, 0);
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    
    // i := 물건의 인덱스, aCount := A 흔적 개수, bCount := B 흔적 개수
    int solve(int i, int aCount, int bCount) {
        // 경찰에 붙잡히는 경우
        if (aCount >= n || bCount >= m) {
            return Integer.MAX_VALUE;
        }
        
        // 모든 물건을 훔친 경우
        if (i >= info.length) {
            return aCount;
        }
        
        // 이미 계산된 결과가 있으면 반환
        if (memo[i][aCount][bCount] != -1) {
            return memo[i][aCount][bCount];
        }
        
        // A가 훔치는 경우와 B가 훔치는 경우 중 최소값 계산
        int result = Math.min(
            solve(i + 1, aCount + info[i][0], bCount),   // A가 훔치는 경우
            solve(i + 1, aCount, bCount + info[i][1])    // B가 훔치는 경우
        );
        
        // 계산 결과 저장
        memo[i][aCount][bCount] = result;
        return result;
    }
    
    void init(int[][] info, int n, int m) {
        this.info = info;
        this.n = n;
        this.m = m;
        
        // 메모이제이션 배열 초기화
        memo = new int[info.length][n][m];
        // 모든 값을 -1로 초기화 (계산되지 않은 상태 표시)
        for (int i = 0; i < info.length; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    memo[i][j][k] = -1;
                }
            }
        }
    }
}