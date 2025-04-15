class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int area = brown + yellow;
        
        // 가능한 width, height 에 대하여, 조건을 만족시켜야 한다. 
        for (int height = 3; height * height <= area; height++) {
            if (area % height == 0) {
                int width = area / height;
                
                if ((2 * height + 2 * width - 4) == brown) {
                    answer[0] = width;
                    answer[1] = height;
                    return answer;
                }
             }
        }
        
        return answer;
    }
}