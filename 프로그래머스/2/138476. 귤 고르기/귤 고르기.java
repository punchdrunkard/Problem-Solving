import java.util.*;

class Solution {
    // 루프는 한번이고.. 정렬은 가능해보이는데? tangerine 이 십만이네 
    // 1. 카운팅 n
    // 2. 맵 순회후 우선순위큐에 넣기 nlogn
    // 3. 우선순위 큐에서 뺴면서 답 구하기 
    public int solution(int k, int[] tangerine) {
        
        
        Map<Integer, Integer> counter = createCountMap(tangerine);
        
        PriorityQueue<State> pq = new PriorityQueue<>();
        for (int key: counter.keySet()) {
            pq.offer(new State(key, counter.get(key)));
        }
        
        int answer = 0; // 현재 담은 귤의 종류 수 
        int count = 0; // 현재 담은 귤의 갯수 
        
        while (count < k) {
            State current = pq.poll();
            count += current.count;
            answer++;
        }
        
        
        return answer;
    }
    
    Map<Integer, Integer> createCountMap(int[] tangerine) {
        Map<Integer, Integer> counter = new HashMap<>();
        
        for (int t: tangerine) {
            counter.put(t, counter.getOrDefault(t, 0) + 1);
        }
        
        return counter;
    }
    
    class State implements Comparable<State> {
        int weight;
        int count;
        
        State (int weight, int count) {
            this.weight = weight;
            this.count = count;
        }
        
        @Override
        public int compareTo(State s) {
            return Integer.compare(s.count, count); // count 기준 내림차순 
        }
        
        @Override
        public String toString() {
            return "(weight: " + weight + ", count: " + count + ")";
        }
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}