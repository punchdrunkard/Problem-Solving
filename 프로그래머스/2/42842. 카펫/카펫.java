class Solution {
    // 가능한 모든 r과 c에 대하여 조건을 만족하는지 확인한다.
    
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        for (int r = 1; r <= 2_000_000; r++) {
            for (int c = 1; c <= 2_000_000; c++) {
                int b = 2 * r + 2 * c - 4;
                int y = r * c - b;
                if (b == brown && y == yellow && r >= c) {
                    answer[0] = r;
                    answer[1] = c;
                    return answer;
                }
            }
        }
        
        return answer;
    }
}