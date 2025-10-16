class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        // 구해야 하는 것 
        // - 현재 가지고 있는 수 중 당첨 번호와 일치하는 수의 갯수 구하기
        // - 0의 개수 
        
        int zeroCount = 0;
        int matchCount = 0;
        for (int lotto: lottos) {
            if (lotto == 0) {
                zeroCount++;
                continue;
            }
            
            for (int winNum: win_nums) {
                if (lotto == winNum) {
                    matchCount++;
                    break;
                }
            }
        }
        
        int maxMatchCount = matchCount + zeroCount;
        int minMatchCount = matchCount;
        
        int[] answer = {convertToRank(maxMatchCount), convertToRank(minMatchCount)};
        return answer;
    }
    
    int convertToRank(int count) {
        if (count <= 1) {
            return 6;
        }
        
        return 6 - count + 1;
    }
}