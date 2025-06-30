import java.util.*;

class Solution {
    
    // 레버를 먼저 들리면 손해 보지 않나? 
    // 아 애초에 레버가 더 가깝거나 하면 레버로 먼저가는거고
    // 가는길에 있으면 좋은거고.. 그런느낌일듯? 
    
    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};
    
    char[][] maps;
    int n, m;
    Pair start, lever, dest;
    
    public int solution(String[] maps) {
        init(maps);
    
        int answer = solve();
        return answer;
    }
    
    int solve() {
        int leverDist = bfs(start, lever);
        if (leverDist == -1) {
            return -1;
        }
  
        int destDist = bfs(lever, dest);
        if (destDist == -1) {
            return -1;
        }
        
        return leverDist + destDist;
    }
    
    int bfs(Pair st, Pair target) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(st);
        boolean[][] visited = new boolean[n][m];
        visited[st.x][st.y] = true;
        int level = 0;
        
        while (!q.isEmpty()) {
            int sz = q.size();
            
            for (int i = 0; i < sz; i++) {
                Pair current = q.poll();
                int cx = current.x;
                int cy = current.y;
                
                if (cx == target.x && cy == target.y) {
                    return level;
                }
                
                for (int d = 0; d < 4; d++) {
                    int nx = cx + DX[d];
                    int ny = cy + DY[d];
                    
                    if (isOutOfRange(nx, ny) || visited[nx][ny] 
                        || maps[nx][ny] == 'X') {
                        continue;
                    }
                    
                    q.offer(new Pair(nx, ny));
                    visited[nx][ny] = true;
                }
            }
            
            level++;
        }
        
        return -1; // 도달 못하는 경우
    }
    
    boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
    
    void init(String[] maps) {
        this.n = maps.length;
        this.m = maps[0].length();
        this.maps = new char[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.maps[i][j] = maps[i].charAt(j);
                
                if (this.maps[i][j] == 'S') {
                    start = new Pair(i, j);
                }
                
                if (this.maps[i][j] == 'L') {
                    lever = new Pair(i, j);
                }
                
                if (this.maps[i][j] == 'E') {
                    dest = new Pair(i, j);
                }
            }
        }
    }
    
    void print2D(char[][] arr) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sb.append(arr[i][j]).append(' ');
            }
            sb.append('\n');
        }
        
        sout(sb);
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
    
    class Pair {
        int x, y;
        
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}