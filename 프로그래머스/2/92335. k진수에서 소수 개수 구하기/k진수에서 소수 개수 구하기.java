import java.util.*;

class Solution {
    
    Set<Long> memo = new HashSet<>(); // 이미 등장한 소수 메모 
    public int solution(int n, int k) {
        
        int answer = 0;
        String[] splited = convertToBase(n, k).split("0");
        
        for (String number: splited) {
            if ("".equals(number)) {
                continue;
            }
            
            long num = Long.parseLong(number);
            
            if (memo.contains(num)) {
                answer++;
            } else {
                if (isPrimeNumber(num)) { // 소수인 경우
                    answer++;
                    memo.add(num);
                }
            }
        }
       
        return answer;
    }
    
    boolean isPrimeNumber(long num) {
        if (num <= 1) {
            return false;
        }
        
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    // n을 k진법으로 바꾸는 함수
    String convertToBase(int n, int k) {
        StringBuilder sb = new StringBuilder();
        
        while(n > 0) {
            sb.append(n % k);
            n /= k;
        }
        
        return sb.reverse().toString();
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}