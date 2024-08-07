class Solution {
    public long solution(int r1, int r2) {
        long count = 0;
        // 1 사분면에서 범위에 포함되는 점들의 갯수를 구한다.
        // 1 사분면 범위 : x -> 0 ~ r2, y -> 0 ~ r2
        
        // 어짜피 한쪽의 범위가 r2 보다 작으니까 하나를 고정 시킬 수가 있음
        for (int x = 1; x <= r2; x++) {
            
            // 즉 x를 고정시켰을 때, y의 범위는?
            // r1^2 <= x^2 + y^2 <= r2^2 를 만족시켜야 하므로
            // r1^2 - x^2 <= y^2 <= r2^2 - x^2 를 만족시키는 y의 값
    
            long lo = (long) Math.ceil(Math.sqrt(1.0 * r1 * r1 - 1.0 * x * x));
            long hi = (long) Math.floor(Math.sqrt(1.0 * r2 * r2 - 1.0 * x * x)); 
            
            long yCount = hi - lo + 1;
            count += yCount;
        }
        
        // System.out.println("count : " + count);
        
        return 4 * count;
    }
}