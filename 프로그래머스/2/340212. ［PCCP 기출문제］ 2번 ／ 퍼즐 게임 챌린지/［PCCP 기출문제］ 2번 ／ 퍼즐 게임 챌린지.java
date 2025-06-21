import java.util.*;
import java.io.*;

class Solution {
    
    int[] diffs, times;
    long limit;
    int n; // 퀴즈의 갯수 
    
    public int solution(int[] diffs, int[] times, long limit) {
        init(diffs, times, limit);
        int answer = (int) solve();
        return answer;
    }
    
    long solve() {
        long lo = 0;
        long hi = Integer.MAX_VALUE;
        
        while (lo + 1 < hi) {
            long mid = lo + (hi - lo) / 2;
            
            if (!canSolve(mid)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        
        return hi;
    }
    
    // 현재 레벨로 limit 시간 안에 다 풀수 있나요? 
    boolean canSolve(long level) {
        long time = 0;
        
        // i번째 퍼즐에 대해 
        for (int i = 0; i < n; i++) {
            // 퍼즐을 틀리는 경우
            if (level < diffs[i]) {
                long wrongCount = Math.abs(level - diffs[i]);

                // 걸리는 시간 계산
                int prevTime = i > 0 ? times[i - 1] : 0;
                time += (wrongCount * (times[i] + prevTime));
            }
            
            time += times[i];
        }
        
        return limit >= time;
    }
    
    
    void init(int[] diffs, int[] times, long limit) {
        this.diffs = diffs;
        this.times = times;
        this.limit = limit;
        this.n = diffs.length;
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}