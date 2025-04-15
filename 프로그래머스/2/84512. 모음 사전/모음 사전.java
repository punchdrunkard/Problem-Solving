class Solution {
    
    final String[] VOWELS = {"A", "E", "I", "O", "U"};
    
    // A, E, I, O, U 를 이용해서 길이 5 이하의 모든 단어를 만들 때
    // 주어지는 word 는 몇 번째 단어인가? 
    
    // 처음부터 만들면서, 해당 word 의 순서가 온다면 리턴한다.
    int seq = 0;
    int answer = 0;
    
    public int solution(String word) {
        dfs(0, "", word);
        return answer;
    }
    
    void dfs(int count, String current, String target){
        // base case
        if (count > 5) {
            return;
        }
        
        seq++;
        
        if (current.equals(target)) {
            answer = seq - 1;
            return;
        }
        
        // 계속 진행
        for (int i = 0; i < 5; i++) {
            dfs(count + 1, current + VOWELS[i], target);
        }
    }
}