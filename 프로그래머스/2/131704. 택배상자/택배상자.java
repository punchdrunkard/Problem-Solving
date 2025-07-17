import java.util.*;

class Solution {
    public int solution(int[] order) {
        int n = order.length;
        int answer = 0;
        
        // 보조 컨테이너 
        ArrayDeque<Integer> stk = new ArrayDeque<>();
        int idx = 0;
        
        // 컨테이너에서 맨 앞에 놓인 상자 확인
        for (int i = 1; i <= n; i++) {
            if (order[idx] == i) { // 바로 내릴 수 있는 경우 
                answer++;
                idx++;
            } else { // 다른 곳에 보관해야 하는 경우 
                // 보조 컨테이너에서 뽑을 수 있는 경우
                while (!stk.isEmpty() && stk.peekLast() == order[idx]) {
                    idx++;
                    answer++;
                    stk.pollLast();
                }
                
                stk.offerLast(i);
            }
        }
        
        // sout(stk);
        
        while (!stk.isEmpty() && stk.peekLast() == order[idx]) {
            idx++;
            answer++;
            stk.pollLast();
        }
        
        return answer;
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}