import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        
        Map<String, Integer> target = initTarget(want, number);
        Map<String, Integer> window = new HashMap<>();
        
        int answer = 0; // 가능한 인덱스 수 
        
        int hi = 0;
        // sliding window -> 답이 만족할 떄 까지 expand 하고 줄임 
        for (int lo = 0; lo < discount.length; lo++) { // shrink phase
            
            // extend
            while (lo <= hi && hi - lo < 10
                   && hi < discount.length) {
                window.put(discount[hi], 
                           window.getOrDefault(discount[hi], 0) + 1);
                hi++;
            }
              
            if (canSignup(target, window)) {
                answer++;
            }
            
            window.put(discount[lo], window.get(discount[lo]) - 1);
            
            if (window.get(discount[lo]) == 0) {
                window.remove(discount[lo]);
            }
        }
        
        return answer;
    }
    
    
    boolean canSignup(Map<String, Integer> target, Map<String, Integer> window) {
        for (String key: target.keySet()) {
            if (target.get(key) != window.getOrDefault(key ,0)) {
                return false;
            }
        }
        
        return true;
        
    }
    
    Map<String, Integer> initTarget(String[] want, int[] number) {
        Map<String, Integer> target = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            target.put(want[i], number[i]);
        }
        
        return target;
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}