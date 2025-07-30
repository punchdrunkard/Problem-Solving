import java.util.*;

class Solution {
    
    int[][] matrix;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        init(rows, columns);
        int[] answer = new int[queries.length];
        
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            
            answer[i] = rotate(query[0] - 1, query[1] - 1, 
                               query[2] - 1, query[3] - 1);
            
            // break;
        }
        
        return answer;
    }
    
    int rotate(int x1, int y1, int x2, int y2) {
        // sout("x1 : " + x1  + ", y1 : " + y1 + ", x2 : " + x2 + ", y2: " + y2);
        
        int[][] snapshot = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                snapshot[i][j] = matrix[i][j];
            }
        }
        
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        int temp = matrix[x1][y1];
        minHeap.offer(temp);
        
        // sout("==== 이전 ====");
        // print2D(matrix);
        // ->
        for (int y = y2; y > y1; y--) {
            matrix[x1][y] = snapshot[x1][y - 1];
            minHeap.offer(matrix[x1][y]);
        }
        
        for (int x = x2; x > x1; x--) {
            matrix[x][y2] = snapshot[x - 1][y2];
            minHeap.offer(matrix[x][y2]);
        }
    
    
       for (int y = y1; y < y2; y++) {
           matrix[x2][y] = snapshot[x2][y + 1];
           minHeap.offer(matrix[x2][y]);
       }
        
        for (int x = x1; x < x2; x++) {
           matrix[x][y1] = snapshot[x + 1][y1];
           minHeap.offer(matrix[x][y1]);
       }
        
        // sout("==== 이후 ====");
        // print2D(matrix);
        
        return minHeap.poll();
    }
    
    void init(int rows, int columns) {
        matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = (i * columns) + (j + 1);
            }
        }
    }
    
    void print2D(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
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