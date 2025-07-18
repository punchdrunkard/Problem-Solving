import java.util.*;

class Solution {
    // 언어에 따라 합 계산 과정 중 산술 오버플로우 발생 가능성이 있으므로 
    // long type 고려가 필요합니다.
    public int solution(int[] queue1, int[] queue2) {
        long s1 = Arrays.stream(queue1).sum();
        long s2 = Arrays.stream(queue2).sum();
        long totalSum = s1 + s2;
        // 전체 합이 홀수면 무조건 -1을 리턴해야 함
        if (totalSum % 2 == 1) {  
            return -1;
        }
        
        // 예제 보니까 합이 큰 거에서 작은거로 넘겨주는게 젤 빠른거 같음
        Queue<Integer> q1 = makeQueue(queue1);
        Queue<Integer> q2 = makeQueue(queue2);
        int answer = 0;
        
        // 종료 조건 -> 모든 원소가 한 번씩 다 왔다갔다했을때...
        int maxCount = 2 * (q1.size() + q2.size());
        while (s1 != s2) {
            if (s1 > s2) {
                int polled = q1.poll();
                q2.offer(polled);
                s1 -= polled;
                s2 += polled;
            } else {
                int polled = q2.poll();
                q1.offer(polled);
                s1 += polled;
                s2 -= polled;
            }
            
            answer++;
            if (s1 == s2) {
                return answer;
            } 
            
            if (answer >= maxCount) {
                return -1;
            }
        }
    
        return answer;
    }
    
    Queue<Integer> makeQueue(int[] arr) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            q.offer(arr[i]);
        }
        return q;
    }
    void sout(Object o) {
        System.out.println(o);
    }
}