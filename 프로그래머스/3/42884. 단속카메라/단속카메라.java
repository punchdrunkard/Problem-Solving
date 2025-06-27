import java.util.*;
import java.io.*;

class Solution {
    
    // 최대한 감시 카메라의 지점을 구간 뒤로 미뤄야 다음 구간 역시 같이 걸릴 확률이 높아진다. 
    public int solution(int[][] routes) {
        
        // 진출 지점이 앞에 있는 것을 우선 처리해야한다.
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
        
        int count = 1; // 맨 처음 지점
        int latestPoint = routes[0][1]; // 마지막으로 카메라 설치한 지점
        
        for (int i = 1; i < routes.length; i++) {
            
            // 구간에 걸린다 
            // -> 마지막으로 카메라 설치한 지점보다 다음 구간의 시작점이 작거나 같으면 됨
            if (routes[i][0] >  latestPoint) { // 카메라를 새로 설치해야 하는 경우
                count++;
                latestPoint = routes[i][1];
            }
        }
        
        return count;
    }
}