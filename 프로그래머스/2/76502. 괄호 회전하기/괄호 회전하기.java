import java.util.*;
import java.util.stream.*;

class Solution {
    LinkedList<Character> current = new LinkedList<>();
    int n;
    
    public int solution(String s) {
        // String 을 Deque로 바꾼다. 
        n = s.length();
        for (int i = 0; i < n; i++) {
            current.offerLast(s.charAt(i));
        }
        
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            if (isValid()) {
                count++;
            }
            
            // 회전
            current.offerLast(current.pollFirst());
        }
        
        return count;
    }
    
    boolean isValid() {
        Deque<Character> stk = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            char c = current.get(i);
            
            // 여는 괄호 -> 넣는다. 
            if (c == '(' || c == '[' || c == '{') {
                stk.push(c);
            } else { // 닫는 괄호일 때, 스택이 비어있으면 false 리턴 
                if (stk.isEmpty()) {
                    return false;
                }
                
                char top = stk.pop();
            
                if (c == ')' && top != '(') {
                    return false;
                }
                if (c == '}' && top != '{') {
                    return false;
                }
                if (c == ']' && top != '[') {
                    return false;
                }
            }
        }
       
        return stk.isEmpty();
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}