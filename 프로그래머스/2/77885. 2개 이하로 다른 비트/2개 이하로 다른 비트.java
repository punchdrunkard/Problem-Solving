class Solution {
    public long[] solution(long[] numbers) {
        int n = numbers.length;
        long[] answer = new long[n];
        
        for (int i = 0; i < n; i++) {
            long num = numbers[i];
            
            if (num % 2 == 0) {
                // 짝수인 경우: +1
                answer[i] = num + 1;
            } else {
                // 홀수인 경우
                // 1. 가장 낮은 '0' 비트의 위치 찾기 (예: 7(0111) -> 8(1000))
                long lowestZeroBit = (num + 1) & ~num;
                
                // 2. 원래 수에 위에서 찾은 비트를 더해서 0->1로 변경
                long temp = num | lowestZeroBit;
                
                // 3. 그 바로 아래 비트(lowestZeroBit/2)를 1->0으로 변경
                answer[i] = temp & ~(lowestZeroBit >> 1);
            }
        }
        
        return answer;
    }
}