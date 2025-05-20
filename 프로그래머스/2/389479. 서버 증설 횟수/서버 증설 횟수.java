import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < 24; i++) {
            // sout(i + " ~ " + (i + 1) + " 시, 이용자 수 : " + players[i]);
            
            // TODO 끝난 서버를 뽑아낸다. => 어짜피 정렬되있어서 아직 살아있으면 그냥 두면 될 듯
           while (!q.isEmpty() && q.peek() <= i) {
                q.poll();
            }
        
            // sout("서버 상태: " + q);
        
            int requiredServers = players[i] / m;
            
            if (q.size() < requiredServers) {
                // 필요한 서버의 갯수
                int additionalServers = requiredServers - q.size();
                // sout(additionalServers + "번 서버 증설");
                
                for (int j = 0; j < additionalServers; j++) {
                    q.offer(i + k);
                }
                answer += additionalServers;
            }
        }

        return answer;
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}