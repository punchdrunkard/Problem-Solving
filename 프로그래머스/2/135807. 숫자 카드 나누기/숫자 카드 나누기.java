import java.util.*;

class Solution {
    // 50만이라서 전부 도는건 일단 말이 안되고...
    // 이분탐색을 생각해볼까? 
    // arrayA 에 있는 원소를 기준으로 얘를 탐색하는데는 logN인데 각각 arrayB를 구하면 N 이라서
    // 아슬아슬하게 가능할 것 같기도..?
    
    public int solution(int[] arrayA, int[] arrayB) {
        Arrays.sort(arrayA);
        Arrays.sort(arrayB); // for cache hit 
        
        int answer = 0;
        return answer;
    }
    
    int bs() {
        return 0;
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
    
}