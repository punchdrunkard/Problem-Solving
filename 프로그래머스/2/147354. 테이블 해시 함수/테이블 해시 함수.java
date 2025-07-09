import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        sortData(data, col);
        
        for (int i = row_begin; i <= row_end; i++) {
            int modSum = 0;
            
            for (int num: data[i - 1]) {
                modSum += (num % i);
            }
            
            answer ^= modSum;
        }
    
        return answer;
    }
    
    void sortData(int[][] data, int col) {
        Arrays.sort(data, (a, b) -> {
            if (a[col - 1] == b[col - 1]) {
                return Integer.compare(b[0], a[0]);
            }
            
            return Integer.compare(a[col - 1], b[col - 1]);
        });
    }
    
    void print2D(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
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