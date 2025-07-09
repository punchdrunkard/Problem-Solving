import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        // 모든 라운드를 막을 수 있는 경우
        if (enemy.length <= k) {
            return enemy.length;
        }
        
        // 최소 힙을 최대 힙처럼 사용하기 위해 음수로 값을 저장
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i = 0; i < enemy.length; i++) {
            int currentEnemy = enemy[i];
            
            n -= currentEnemy;
            pq.add(-currentEnemy); // 최대 힙처럼 사용하기 위해 음수로 변환하여 추가
            
            // 병사가 부족하면 무적권을 사용
            if (n < 0) {
                // 사용할 무적권이 없으면 현재 라운드(i)는 막을 수 없으므로 i를 반환
                if (k <= 0) {
                    return i;
                }
                
                k--;
                // 지금까지 병사로 막았던 가장 병력 소모가 컸던 라운드를 무적권으로 대체
                int maxEnemy = -pq.poll(); 
                n += maxEnemy;
            }
        }
        
        return enemy.length; 
    }
}