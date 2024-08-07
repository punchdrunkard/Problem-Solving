import java.util.*;

class Solution {
    static int n, m;
    static int[][] land;
    static int answer = 0;
    
    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};
    
    static boolean[][] visited;
    static int[] totalCount; // totalCount[x] := y좌표가 ~일 때 뽑을 수 있는 총 석유량
    
    static Queue<Point> q = new LinkedList<>();
    
    public int solution(int[][] _land) {
        // init data
        n = _land.length;
        m = _land[0].length;
        land = _land;
        visited = new boolean[n][m];
        totalCount = new int[m];
        
        solve();
        
        for (int i = 0; i < m; i++) {
            answer = Math.max(answer, totalCount[i]);
        }
        
        return answer;
    }
    
    static void solve() {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (land[x][y] == 0 || visited[x][y]) {
                    continue;
                }
                
                bfs(x, y);
            }
        }
    }
    
    static void bfs(int x, int y) {
        // 현재 지나친 y좌표를 저장함
        Set<Integer> yPoints = new HashSet();
        int count = 1; // 석유 덩어리의 크기를 저장
        q.offer(new Point(x, y));
        visited[x][y] = true;
        yPoints.add(y);
        
        while (!q.isEmpty()) {
            Point current = q.poll();
            int cx = current.x;
            int cy = current.y;
            
            for (int dir = 0; dir < 4; dir++) {
                int nx = cx + DX[dir];
                int ny = cy + DY[dir];
                
                if (isOutOfRange(nx, ny) || visited[nx][ny] || land[nx][ny] == 0) {
                    continue;
                }
                
                visited[nx][ny] = true;
                count++;
                q.offer(new Point(nx, ny));
                yPoints.add(ny);
            }
        }
            
        for (int point: yPoints) {
            // System.out.println("y : " + point);
            totalCount[point] += count;
        }
        
        // System.out.println("count: " + count);
    }
    
    static boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
    
    static class Point {
        int x;
        int y;
        
        Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}