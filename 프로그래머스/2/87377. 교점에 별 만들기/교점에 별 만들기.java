import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        String[] answer = {};
        List<Pair> intersections = findIntersections(line);
        return getAnswer(intersections);
    }
    
    String[] getAnswer(List<Pair> points) {
        long minX = Long.MAX_VALUE;
        long minY = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long maxY = Long.MIN_VALUE;
        
        for (Pair p: points) {
            long x = p.x;
            long y = p.y;
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
        }
        
        int width = (int) (maxX - minX + 1);
        int height = (int) (maxY - minY + 1);
        char[][] map = new char[height][width];
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], '.');
        }
        
        // 좌표 변환
        for (Pair p: points) {
            int row = (int) (maxY - p.y);
            int col = (int) (p.x - minX);
            map[row][col] = '*';
        }
        
        String[] answer = new String[height];
        for (int i = 0; i < height; i++) {
            answer[i] = new String(map[i]);
        }

        return answer;
    }
    
    void print2D(char[][] square) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square[i].length; j++) {
                sb.append(square[i][j]);
            }
            sb.append('\n');
        }
        
        sout(sb);
    }
    
    List<Pair> findIntersections(int[][] line) {
        List<Pair> intersections = new ArrayList<>();
        
        // 모든 직선 쌍에 대하여 
        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                int[] line1 = line[i];
                int[] line2 = line[j];
                
                long a = line1[0], b = line1[1], e = line1[2];
                long c = line2[0], d = line2[1], f = line2[2];
                
                long mod = a * d - b * c;
                if (mod == 0) {
                    continue;
                }
                
                long numeratorX = b * f - e * d;
                long numeratorY = e * c - a * f;
                
                if (numeratorX % mod != 0 || numeratorY % mod != 0) {
                    continue;
                }
                
                intersections.add(new Pair(numeratorX / mod, numeratorY / mod));
            }
        }
    
        return intersections;
    }
    
  
    class Pair {
        long x, y;
        
        Pair(long x, long y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}