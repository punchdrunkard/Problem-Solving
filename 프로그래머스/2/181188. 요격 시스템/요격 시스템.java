import java.util.*;
import java.io.*;

class Solution {
    
    // 가설: 어떤 구간이 끝나기 전에 미사일을 쏘는게 효율적일 것이다.
    // 근거: 요격지점을 미룰 수록, 해당 지점 이전에 걸쳐있는 구간의 미사일들이 존재할 가능성이 있음 
    // -> 그렇다면 가장 급한 미사일은 가장 먼저 끝나는 미사일 => e가 작은 미사일 
    public int solution(int[][] targets) {
        // 끝점 기준 오름차순 정렬 
        Arrays.sort(targets, (a, b) -> Integer.compare(a[1], b[1]));
        

        int latestPoint = targets[0][1];  // 최근 요격 지점 경계값 
        int count = 1;
        
        for (int i = 1; i < targets.length; i++) {
            if (targets[i][0] < latestPoint) { // 이전의 요격을 통해 처리된 경우 
                continue;
            }
            
            count++;
            latestPoint = targets[i][1]; 
        }
        
        return count;
    }
}