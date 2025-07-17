import java.util.*;

class Solution {
    // 두배해서 set으로 집어넣으면 되지 않나? 
    // 어짜피 총길이가 1000이라서 상관없음 
    public int solution(int[] elements) {
        int n = elements.length;
        int[] extended = createExtendedArr(elements);
        
        Set<Integer> sums = new HashSet<>();
        
        // 부분 수열을 계산할 시작점 
        for (int i = 0; i < n; i++) {
            int sum = 0;
            
            for (int j = i; j < i + n; j++) {
                sum += extended[j];
                sums.add(sum);
            }
        }
        
        // sout(sums);
        
        return sums.size();
    }
    
    int[] createExtendedArr(int[] elements) {
        int n = elements.length;
        int[] extended = new int[2 * n];
        for (int i = 0; i < n; i++) {
            extended[i] = elements[i];
        }
        for (int i = n; i < 2 * n; i++) {
            extended[i] = elements[i % n];
        }
        return extended;
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}