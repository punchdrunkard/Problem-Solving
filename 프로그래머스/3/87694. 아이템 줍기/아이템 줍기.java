import java.util.*;
class Solution {
    final int OUTSIDE = 0; // 바깥 영역
    final int AREA = 1; //안쪽 영역 
    final int PATH = 2; // 캐릭터가 움직일 수 있는 둘레 영역
   
    final int[] DX = {-1, 1, 0, 0};
    final int[] DY = {0, 0, -1, 1};
    
    class Pair {
        int x, y;
        
        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;
        
        int[][] map = createMap(rectangle);
        int answer = bfs(map, new Pair(characterX, characterY), new Pair(itemX, itemY));
        return answer / 2; 
    } // end of solution
    
    int bfs(int[][] map, Pair st, Pair dest) {
        Queue<Pair> q = new LinkedList<>();
        int[][] dist = new int[101][101]; 
        for (int i = 0; i < dist.length; i++) {
            Arrays.fill(dist[i], -1);
        }
        q.offer(st);
        dist[st.x][st.y] = 0;
        
        while (!q.isEmpty()) {
            Pair current = q.poll();
            int cx = current.x;
            int cy = current.y;
            
            if (cx == dest.x && cy == dest.y) {
                return dist[cx][cy];
            }
            
            // 상하좌우 이동만 가능
            for (int d = 0; d < 4; d++) {
                int nx = cx + DX[d];
                int ny = cy + DY[d];
                if (isOutOfRange(nx, ny) || dist[nx][ny] != -1 || map[nx][ny] != PATH) {
                    continue;
                }
                q.offer(new Pair(nx, ny));
                dist[nx][ny] = dist[cx][cy] + 1;
            }
        }
        return -1; // 길을 찾지 못한 경우
    } // end of bfs
    
    int[][] createMap(int[][] rectangle) {
        // 좌표를 2배로 확대하여 처리
        int[][] map = new int[101][101]; // 좌표 범위 확장
        
        // 직사각형 내부를 모두 AREA로 채움
        for (int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0] * 2;
            int y1 = rectangle[i][1] * 2;
            int x2 = rectangle[i][2] * 2;
            int y2 = rectangle[i][3] * 2;
            
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    map[x][y] = AREA;
                }
            }
        }
        
        // 둘레 찾기
        for (int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0] * 2;
            int y1 = rectangle[i][1] * 2;
            int x2 = rectangle[i][2] * 2;
            int y2 = rectangle[i][3] * 2;
            
            // 직사각형의 내부는 다시 AREA로 설정 (다른 직사각형의 둘레와 겹치는 부분 처리)
            for (int x = x1 + 1; x < x2; x++) {
                for (int y = y1 + 1; y < y2; y++) {
                    map[x][y] = AREA;
                }
            }
            
            // 직사각형의 테두리만 PATH로 설정
            for (int x = x1; x <= x2; x++) {
                map[x][y1] = PATH; // 아래쪽 가로변
                map[x][y2] = PATH; // 위쪽 가로변
            }
            for (int y = y1; y <= y2; y++) {
                map[x1][y] = PATH; // 왼쪽 세로변
                map[x2][y] = PATH; // 오른쪽 세로변
            }
        }
        
        // 다른 직사각형 내부에 있는 경로는 다시 AREA로 변경
        for (int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0] * 2;
            int y1 = rectangle[i][1] * 2;
            int x2 = rectangle[i][2] * 2;
            int y2 = rectangle[i][3] * 2;
            
            for (int x = x1 + 1; x < x2; x++) {
                for (int y = y1 + 1; y < y2; y++) {
                    map[x][y] = AREA;
                }
            }
        }
        
        return map;
    } // end of createMap
    
    boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= 101 || y < 0 || y >= 101; // 좌표 범위 확장
    }
}