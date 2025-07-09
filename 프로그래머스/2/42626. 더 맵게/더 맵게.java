import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int s: scoville) {
            pq.offer((long) s);
        }
        
        int count = 0;
        while (pq.peek() < K) {
            
            // 더 이상 섞을 수 있는 음식이 없는 경우
            if (pq.size() < 2) {
                return -1;
            }
            
            long minScoville = pq.poll();
            long secondMinScoville = pq.poll();
            long mixedScoville = minScoville + 2 * secondMinScoville;
            pq.offer(mixedScoville);
            count++;
        }
        
        return count;
    }
}