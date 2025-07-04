import java.util.*;

// 무거운 사람 + 가벼운 사람 조합으로 최대한 limit 에 딱 맞게 구성해야 한다.

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int n = people.length;

        Arrays.sort(people);
 
        int lo = 0;
        int hi = n - 1;
        
        while (lo < hi) {
            int sum = people[lo] + people[hi];
            
            if (sum > limit) {
                // hi 를 따로 태워야 하는 경우
                answer++;
                hi--;
            } else {
                // lo 와 hi 를 같이 태울 수 있는 경우
                answer++;
                lo++;
                hi--;
            }
        }
        
        if (lo == hi) {
            answer++;
        }
        
        return answer;
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}