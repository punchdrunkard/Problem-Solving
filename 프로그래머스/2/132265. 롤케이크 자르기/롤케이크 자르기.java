import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        int n = topping.length;
        int[] forward = new int[n + 1];
        int[] backward = new int[n + 1];
        
        forward[0] = 0;
        Set<Integer> fSet = new HashSet<>();
        Set<Integer> bSet = new HashSet<>();
        
        for (int i = 0; i < n; i++) {
            fSet.add(topping[i]);
            forward[i + 1] = fSet.size();
        }
        
        for (int i = n - 1; i >= 0; i--) {
            bSet.add(topping[i]);
            backward[i] = bSet.size();
        }
        
        for (int i = 0; i < n + 1; i++) {
            if (forward[i] == backward[i]) {
                answer++;
            }
        }
        
        return answer;
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}