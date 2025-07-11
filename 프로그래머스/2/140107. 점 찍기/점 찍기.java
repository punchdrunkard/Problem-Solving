class Solution {
    // (ak)^2 + (bk)^2 <= d^2 를 만족시키는 (a, b) 의 갯수
    // -> 식을 정리하면 k^2 (a^2 + b^2) <= d^2
    // -> a^2 + b^2 <= d^2 / k^2 인 (a, b) 의 갯수는? 
    // -> 즉, 반지름이 d/k 인 원 안에 들어가는 격자점 (a, b)의 개수 
    
   
    public long solution(int k, int d) {
        long answer = 0;
        
        long dSquare = (long) d * d;
        long kSquare = (long) k * k;
        
        long limit = dSquare / kSquare;
        
        for (long a = 0; a * a <= limit; a++) {
            long bCount = (long) Math.sqrt(limit - a * a) + 1;
            answer += bCount;
        }
        
        return answer;
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}