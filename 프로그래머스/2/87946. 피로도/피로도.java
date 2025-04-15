import java.util.*;

class Solution {
    int max = -1;
    
    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        
        List<Integer> seq = new ArrayList<>();
        
        dfs(k, visited, dungeons, seq);
        return max;
    }
    
    // dfs: 현재 ~의 순서로 던전을 탐험했을 때, 탐험한 던전의 갯수를 리턴함
    void dfs(int k, boolean[] visited, int[][] dungeons, List<Integer> seq) {
        // 종료 조건 - 더 이상 던전을 탐험할 수 없음 (모든 순서를 생성한 경우)
        if (seq.size() == dungeons.length) {
            int count = 0;
            int current = k;
            
            for (int dungeonIdx: seq) {
                int minHp = dungeons[dungeonIdx][0];
                int useHp = dungeons[dungeonIdx][1];
                
                if (current < minHp) {
                    break;
                }
                
                if (current - useHp <= 0) {
                    break;
                }
                
                count++;
                current -= useHp;
            }
            
            max = Math.max(max, count);
            return;
        }
        
        // recursive - 던전 탐험의 순서를 만든다. 
        for (int idx = 0; idx < dungeons.length; idx++) {
            if (visited[idx]) {
                continue;
            }
            
            visited[idx] = true;
            seq.add(idx);
            dfs(k, visited, dungeons, seq);
            seq.remove(seq.size() - 1);
            visited[idx] = false;
        }
    }
}