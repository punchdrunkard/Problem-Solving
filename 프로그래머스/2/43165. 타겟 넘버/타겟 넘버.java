import java.util.*;
class Solution {
    class State {
        int idx, val;
        
        State(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
    
    public int solution(int[] numbers, int target) {
        return bfs(numbers, target);
    }
    
    int bfs(int[] numbers, int target) {
        int answer = 0;
        
        // init
        Queue<State> q = new LinkedList<>();
         
        q.offer(new State(0, numbers[0]));
        q.offer(new State(0, -numbers[0]));
        
        while (!q.isEmpty()) {
            State current = q.poll();
            
            if (current.idx == numbers.length - 1) {
                if (current.val == target) {
                    answer++;
                }
                continue;
            }
            
            int nextIdx = current.idx + 1;
            
            q.offer(new State(nextIdx, current.val + numbers[nextIdx]));
            q.offer(new State(nextIdx, current.val - numbers[nextIdx]));
        }
    
        return answer;
    }
}