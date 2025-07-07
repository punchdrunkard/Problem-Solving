class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while (storey > 0) {
            int remainder = storey % 10;
            int nextDigit = (storey / 10) % 10;
            
            if (remainder > 5) {
                // 1의 자리가 6, 7, 8, 9 => 올리는 것이 이득
                answer += (10 - remainder);
                storey += (10 - remainder);
            } else if (remainder < 5) {
                answer += remainder; // 내리는게 이득 
            } else {
                if (nextDigit >= 5) {
                    answer += 5;
                    storey += 5;
                } else {
                    answer += 5;
                }
            }
            
            storey /= 10;
        }
        return answer;
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}