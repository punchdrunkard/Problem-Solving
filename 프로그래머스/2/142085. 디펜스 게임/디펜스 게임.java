class Solution {
    // 일단 enemy <= 1,000,000 이라서 완탐은 절대 불가능이네 
    
    public int solution(int n, int k, int[] enemy) {
        if (enemy.length <= k) {
            return enemy.length;
        }
        
        int roundCount = 0; 
        
        // 절반이상이거나 죽기전에 써보자 
        for (int i = 0; i < enemy.length; i++) {
            if (k == 0 && enemy[i] > n) {
                break;
            }
            
            roundCount++; 
            
            if (k > 0 && enemy[i] >= (n / 2) || enemy[i] >= n) { // 무적권 사용
                k--;
                continue;
            }
            
            n -= enemy[i];
        }
        
        return roundCount;
    }
}