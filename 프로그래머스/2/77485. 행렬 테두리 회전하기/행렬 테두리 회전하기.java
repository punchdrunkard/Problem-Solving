class Solution {
    
    int[][] matrix;
    int rows, columns;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        init(rows, columns);
        
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            answer[i] = rotate(query[0], query[1], query[2], query[3]);
        }
        
        return answer;
    }
    
    // 행렬을 회전시키고, 위치가 바뀐 수 중 가장 작은 수를 리턴한다. 
    int rotate(int x1, int y1, int x2, int y2) {
        int min = matrix[x1][y1];
        int cache = matrix[x1][y1];
        
        // 위 (y == y1, x)
        for (int x = x1; x < x2; x++) {
            matrix[x][y1] = matrix[x + 1][y1];
            min = Math.min(min, matrix[x][y1]);
        }
        
        // <- (x == x2, y) 
        for (int y = y1; y < y2; y++) {
            matrix[x2][y] = matrix[x2][y + 1];
            min = Math.min(min, matrix[x2][y]);
        }
        
        // 아래 
        for (int x = x2; x > x1; x--) {
            matrix[x][y2] = matrix[x - 1][y2];
            min = Math.min(min, matrix[x][y2]);
        }
        
        // -> 
        for (int y = y2; y > y1; y--) {
            matrix[x1][y] = matrix[x1][y -1];
            min = Math.min(min, matrix[x1][y]);
        }
        
        matrix[x1][y1 + 1] = cache;
        
        return min;
    }
    
    void init(int rows, int columns) {
        // 1-indexed 
        matrix = new int[rows + 1][columns + 1];
        
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                matrix[i][j] = (i - 1) * columns + j;
            }
        }    
    }
    
    void print2D(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[i].length; j++) {
                sb.append(arr[i][j]).append('\t');
            }
            sb.append('\n');
        }
        sout(sb);
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}