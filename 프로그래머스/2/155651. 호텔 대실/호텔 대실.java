import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        
        int[][] times = convertToMinuteArray(book_time);
        Arrays.sort(times, (a, b) -> Integer.compare(a[0], b[0]));
        
        int answer = 1;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(times[0][1]);
        
        for (int i = 1; i < times.length; i++) {
            int latest = minHeap.peek(); // 가장 먼저 끝나는 시간 
            
            if (latest + 10 > times[i][0]) { // 해당 방을 쓸 수 없는 경우
                answer++;
            } else {
                minHeap.poll();
            }
            
            minHeap.offer(times[i][1]);
        }
        
        return answer;
    }
    
    int[][] convertToMinuteArray(String[][] book_time) {
        int[][] res = new int[book_time.length][2];
        for (int i = 0; i < book_time.length; i++) {
            res[i][0] = convertToMinute(book_time[i][0]);
            res[i][1] = convertToMinute(book_time[i][1]);
        }
        return res;
    }
    
    int convertToMinute(String timeUnit) {
        int[] units = Arrays.stream(timeUnit.split(":")) 
                            .mapToInt(Integer::parseInt)
                            .toArray();
                        
        return units[0] * 60 + units[1];
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