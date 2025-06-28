import java.util.*;
import java.io.*;

class Solution {
    
    final int[] DX = {-1, 1, 0, 0};
    final int[] DY = {0, 0, -1, 1};
    
    char[][] board;
    int n, m;
    int rx, ry, gx, gy; // 로봇의 처음 위치, 목표 지점 
    
    public int solution(String[] board) {
        init(board);
        int answer = bfs();
        return answer;
    }
    
    int bfs() {
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        q.offer(new Pair(rx, ry));
        visited[rx][ry] = true;
        int dist = 0;
        
        while(!q.isEmpty()) {
            int sz = q.size();
            
            for (int i = 0; i < sz; i++) {
                Pair current = q.poll();
                int cx = current.x;
                int cy = current.y;
                
                if (board[cx][cy] == 'G') {
                    return dist;
                }
                
                for (int d = 0; d < 4; d++) {
                    // 해당 방향으로 끝까지 이동시키는 함수
                    Pair next = moveDir(cx, cy, d);
                    
                    if (visited[next.x][next.y]) {
                        continue;
                    }
                    
                    q.offer(next);
                    visited[next.x][next.y] = true;
                }
            }
            
            dist++;
        }
        
        return -1;
    }
    
    Pair moveDir(int x, int y, int d) {
        // d 방향으로 장애물이 나올때까지 or 가장자리에 부딪힐떄 까지 이동시킴
        while (true) {
            int nx = x + DX[d];
            int ny = y + DY[d];
            
            if (isOutOfRange(nx, ny) || board[nx][ny] == 'D') {
                break;
            }
            
            x = nx;
            y = ny;
        }
        
        return new Pair(x, y);
    }

    boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
    
    void init(String[] board) {
        this.n = board.length;
        this.m = board[0].length();
        this.board = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.board[i][j] = board[i].charAt(j);
                if (this.board[i][j] == 'R') {
                    rx = i;
                    ry = j;
                }
                
                if (this.board[i][j] == 'G') {
                    gx = i;
                    gy = j;
                }
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
    
    void print2D(char[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
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