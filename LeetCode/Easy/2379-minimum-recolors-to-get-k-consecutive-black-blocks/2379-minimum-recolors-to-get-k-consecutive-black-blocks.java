class Solution {

    static final char WHITE = 'W';

    public int minimumRecolors(String blocks, int k) {
        // sliding window
        int lo = 0;
        int hi = k - 1;
        int current = 0;
        
        for (int i = 0; i < k; i++) {
            if (blocks.charAt(i) == WHITE) {
                current++;
            }
        }   

        int answer = current;

        while (hi < blocks.length() - 1) {
            
            // 왼쪽 끝 문자를 제거한다. 
            if (blocks.charAt(lo) == WHITE) {
                current--;
            }
            lo++;

            // 오른쪽 끝 이동 후, 새 문자를 추가한다.
            hi++;
            if (blocks.charAt(hi) == WHITE) {
                current++;
            }


            answer = Math.min(answer, current);
        }

        return answer;
    }
}