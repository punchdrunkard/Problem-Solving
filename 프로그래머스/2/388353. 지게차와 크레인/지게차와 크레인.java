import java.util.*;
import java.io.*;

class Solution {
    
    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};
    static final int OUTER = 0;
    static final int INNER = 1;
    static final int CONTAINER = 2;
    
    String[] storage, requests;
    int n, m;
    
    int[][] area;
    
    public int solution(String[] storage, String[] requests) {
        init(storage, requests);
        process();
        return countContainer();
    }
    
    int countContainer() {
        int count = 0;
        
        for (int x = 0; x < n + 2; x++) {
            for (int y = 0; y < m + 2; y++) {
                if (area[x][y] == CONTAINER) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    void process() {
        for (String request: requests) {
            int target = request.charAt(0);
            
            if (request.length() == 1) {
                // 지게차 
                // sout("==== 지게차 요청 ====");
                processForkLift(target);
            } else {
                // 크레인 
                // sout("==== 크레인 요청 ====");
                processCrain(target);
            }

            updateArea();
            // sout("===== 요청 이후 영역 확인 ====");
            // print2D(area);
        }
    }
    
    
    void processCrain(int target) {
        for (int x = 0; x < n + 2; x++) {
            for (int y = 0; y < m + 2; y++){
                if (area[x][y] == CONTAINER 
                    && storage[x - 1].charAt(y - 1) == target) {
                    area[x][y] = INNER;
                }
            }
        }
    }
    
    void processForkLift(int target) {
        // 외부와 접해있는 것만 처리 가능
        for (int x = 0; x < n + 2; x++) {
            for (int y = 0; y < m + 2; y++) {
                if (area[x][y] == OUTER) {
                    for (int d = 0; d < 4; d++) {
                        int nx = x + DX[d];
                        int ny = y + DY[d];
                        
                        if (nx < 0 || nx >= n + 2 || ny < 0 || ny >= m + 2) {
                            continue;
                        }
                        
                        if (area[nx][ny] != CONTAINER 
                            || storage[nx - 1].charAt(ny - 1) != target) {
                            continue;
                        }
                        
                        area[nx][ny] = INNER;
                    }
                }
            }
        }
    }
    
    void updateArea() {
        Queue<Pair> q = new LinkedList<>();
        
        q.offer(new Pair(0, 0)); // (0, 0) 은 무조건 OUTER 임이 보장됨
        boolean[][] visited = new boolean[n + 2][m + 2];
        visited[0][0] = true;
    
        while (!q.isEmpty()) {
            int sz = q.size();
            
            for (int i = 0; i < sz; i++) {
                Pair current = q.poll();
                int cx = current.x;
                int cy = current.y;
                
                area[cx][cy] = OUTER;
                
                for (int d = 0; d < 4; d++) {
                    int nx = cx + DX[d];
                    int ny = cy + DY[d];
                    
                    if (isOutOfRange(nx, ny) || visited[nx][ny] 
                        || area[nx][ny] == CONTAINER) {
                        continue;
                    }
                    
                    q.offer(new Pair(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
    }
    
    boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= n + 2 || y < 0 || y >= m + 2;
    }
    
    void init(String[] storage, String[] requests) {
        this.storage = storage;
        this.requests = requests;
        n = storage.length;
        m = storage[0].length();
        area = new int[n + 2][m + 2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                area[i][j] = CONTAINER;
            }
        }
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
    
    void print2D(int[][] arr) {
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
}