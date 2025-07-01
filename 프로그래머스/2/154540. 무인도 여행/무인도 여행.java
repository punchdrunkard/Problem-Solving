import java.util.*;

class Solution {
    
    final int[] DX = {-1, 1, 0, 0};
    final int[] DY = {0, 0, -1, 1};
    char[][] maps; 
    int n, m;
    
    public int[] solution(String[] maps) {
        init(maps);
        int[] answer = solve();
        return answer;
    }
    
    int[] solve() {
        List<Integer> answer = new ArrayList<>();
        
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (maps[x][y] == 'X' || visited[x][y]) {
                    continue;
                }
                
                answer.add(bfs(q, visited, x, y));
             }
        }
        
        Collections.sort(answer);
        if (answer.isEmpty()) {
            answer.add(-1);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    int bfs(Queue<Pair> q, boolean[][] visited, int x, int y) {
        q.offer(new Pair(x, y));
        visited[x][y] = true;
        int count = maps[x][y] - '0';
        
        while(!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                Pair current = q.poll();
                int cx = current.x;
                int cy = current.y;
                
                for (int d = 0; d < 4; d++) {
                    int nx = cx + DX[d];
                    int ny = cy + DY[d];
                    
                    if (isOutOfRange(nx, ny) || visited[nx][ny] 
                        || maps[nx][ny] == 'X') {
                        continue;
                    }
                    
                    visited[nx][ny] = true;
                    q.offer(new Pair(nx, ny));
                    count += (maps[nx][ny] - '0');
                }
            }
        }
        
        return count;
    }
    
    boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;    
    }
    
    void init(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        this.maps = new char[n][m];
        for (int i = 0; i < n; i++) {
            this.maps[i] = maps[i].toCharArray();
        }
    }
    
    // 1. bfs 로 connected component (무인도) 찾기 + 식량 누적 
    class Pair {
        int x, y;
        
        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}