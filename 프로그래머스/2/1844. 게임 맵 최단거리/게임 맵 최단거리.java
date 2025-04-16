import java.util.*;

class Solution {

    final int[] DX = {-1, 1, 0, 0};
    final int[] DY = {0, 0, -1, 1};
    final int WALL = 0;
    int n, m;
    
    class Pair {
        int x, y;
        
        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    // 가중치가 1인 그래프(격자)에서의 최단거리 -> bfs
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        
        int answer = bfs(maps);
        return answer;
    } // end of solution
    
    int bfs(int[][] maps) {
        int cnt = 0;
        
        boolean[][] visited = new boolean[n][m];
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) { 
            Arrays.fill(dist[i], -1);
        }
        
        Queue<Pair> q = new LinkedList<>();
        visited[0][0] = true;
        dist[0][0] = 1;
        q.offer(new Pair(0, 0));
        
        while (!q.isEmpty()) {
            int sz = q.size();
            
            for (int i = 0; i < sz; i++) {
                Pair current = q.poll();
                int cx = current.x;
                int cy = current.y;
               
                if (cx == n - 1 && cy == m - 1) {
                    return dist[cx][cy];
                }
                
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cx + DX[dir];
                    int ny = cy + DY[dir];
                    
                    if (isOutOfRange(nx, ny) || visited[nx][ny] 
                        || maps[nx][ny] == WALL) {
                        continue;
                    }
                    
                    visited[nx][ny] = true;
                    dist[nx][ny] = dist[cx][cy] + 1;
                    q.offer(new Pair(nx, ny));
                }
            }
        }
        
        return dist[n - 1][m - 1];
    } // end of bfs
    
    boolean isOutOfRange(int x, int y){
        return x < 0 || x >= n || y < 0 || y >= m;
    } // end of isOutOfRange
    
    
    
}