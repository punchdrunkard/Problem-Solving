import java.util.*;

class Solution {
    // y -> x 로 가자 
    public int solution(int x, int y, int n) {
        
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[1_000_0001];
        visited[y] = true;
        q.offer(y);
        
        int level = 0;
        
        while(!q.isEmpty()) {
            int sz = q.size();
            
            for (int i = 0; i < sz; i++) {
                int current = q.poll();
                if (current == x) {
                    return level;
                }
                
                if (current % 3 == 0 && !visited[current / 3]) {
                    q.offer(current / 3);
                    visited[current / 3] = true;
                } 
                
                if (current % 2 == 0 && !visited[current / 2]) {
                    q.offer(current / 2);
                    visited[current / 2] = true;
                }
                
                if (current > n && !visited[current - n]) {
                    q.offer(current - n);
                    visited[current - n] = true;
                }
            }
            
            level++;
        }
        
        
        return -1;
    }
}