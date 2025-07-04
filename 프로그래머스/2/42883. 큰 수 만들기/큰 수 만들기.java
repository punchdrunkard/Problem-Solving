import java.util.*;

class Solution {
    public String solution(String number, int k) {
        // 결과로 만들 숫자를 스택에 쌓음
        Deque<Character> stk = new ArrayDeque<>();
        
        for (char c: number.toCharArray()) {
            while (!stk.isEmpty() && stk.peekLast() < c && k > 0) {
                stk.pollLast();
                k--;
            }
            
            stk.offerLast(c);
        }
        
        // 모든 숫자를 확인했는데 k가 아직 남았다면, 뒤에서 부터 제거
        while (k > 0) {
            stk.pollLast();
            k--;
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stk.isEmpty()) {
            sb.append(stk.pollFirst());
        }
        
        return sb.toString();
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}