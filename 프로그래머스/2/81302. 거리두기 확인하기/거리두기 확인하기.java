import java.util.*;

class Solution {
    static final char PARTITION = 'X';
    
    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};
    
    static int n; // 대기실의 개수
    
    public int[] solution(String[][] places) {
        n = places.length;
        int[] answer = new int[n];
        
        for (int i = 0; i < answer.length; i++) {
            if (doesComplyDistance(places[i])) {
                answer[i] = 1;
            }
        }
        
        return answer;
    }
    
    boolean doesComplyDistance(String[] _place) {
        List<Pair> people = new ArrayList<>();
        char[][] place = new char[5][5];
        
        // preprocess -> 2차원 char 배열로 바꾼다.
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                place[i][j] = _place[i].charAt(j);
                if (place[i][j] == 'P') {
                    people.add(new Pair(i, j));
                }
            }
        }

        // 모든 사람들의 순서쌍에 대해 
        for (int i = 0; i < people.size(); i++) {
            for (int j = i + 1; j < people.size(); j++) {
                if (getDistance(people.get(i), people.get(j)) > 2) {
                    continue;
                } 
                
                if (!bfs(people.get(i), people.get(j), place)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    boolean bfs(Pair st, Pair en, char[][] place) {
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        visited[st.x][st.y] = true;
        q.offer(st);
        int dist = 0;
        
        while (!q.isEmpty()) {
            int sz = q.size();
            
            if (dist > 2) {
                break;
            }
            
            for (int i = 0; i < sz; i++) {
                Pair current = q.poll();
                int cx = current.x;
                int cy = current.y;
                
                if (cx == en.x && cy == en.y) {
                    return false;
                }
                
                for (int d = 0; d < 4; d++) {
                    int nx = cx + DX[d];
                    int ny = cy + DY[d];
                    
                    if (isOutOfBound(nx, ny) || visited[nx][ny] 
                        || place[nx][ny] == PARTITION) {
                        continue;
                    }
                    
                    visited[nx][ny] = true;
                    q.offer(new Pair(nx, ny));
                }
            }
            
            dist++;
        }
        
        return true;
    }
    
    int getDistance(Pair p1, Pair p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
    
    boolean isOutOfBound(int x, int y) {
        return x < 0 || x >= 5 || y < 0 || y >= 5;
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