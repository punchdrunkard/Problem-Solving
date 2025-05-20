import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        int[] end = new int[24]; // end[i] := i 시간에 만료되는 서버의 수
        int currentServer = 0;

        for (int i = 0; i < 24; i++) {
            // sout(i + " ~ " + (i + 1) + " 시, 이용자 수 : " + players[i]);
            currentServer -= end[i];
        
            int requiredServers = players[i] / m;
            
            if (currentServer < requiredServers) {
                int additionalServers = requiredServers - currentServer;
                if (i + k < 24) {
                    end[i + k] += additionalServers;
                }
                currentServer += additionalServers;
                answer += additionalServers;
            }
        }

        return answer;
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}