import java.util.*;

class Solution {

    public int solution(int[] cards) {
        // 1. 전체 그룹을 만드는 함수 -> bfs... 비슷하게 만들 수 있을 듯! 
        List<List<Integer>> groups = findGroups(cards);
        
        // 2. 여기서 가장 사이즈가 큰 그룹 두 개를 찾아야 한다.
        if (groups.size() <= 1) {
            return 0;
        }
        
        groups.sort((a, b) -> Integer.compare(b.size(), a.size()));
        
        return groups.get(0).size() * groups.get(1).size();
    }
    
    List<List<Integer>> findGroups(int[] cards) {
        List<List<Integer>> groups = new ArrayList<>();
        
        int n = cards.length;
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            
            groups.add(bfs(cards, i, visited));
        }
        
        return groups;
    }
    
    List<Integer> bfs(int[] cards, int st, boolean[] visited) {
        List<Integer> result = new ArrayList<>();
        
        Queue<Integer> q = new LinkedList<>();
        visited[st] = true;
        q.offer(st);
        result.add(cards[st]);
        
        while(!q.isEmpty()) {
            int sz = q.size();
            
            for (int i = 0; i < sz; i++) {
                // 현재 카드 인덱스
                // 여기 인덱스에 적혀있는 수가 다음 진행 방향을 의미
                int current = q.poll(); 
                int nextIdx = cards[current] - 1; 
                
                if (visited[nextIdx]) {
                    continue;
                }
                
                q.offer(nextIdx);
                visited[nextIdx] = true;
                result.add(cards[nextIdx]);
            }
        }
        
        return result;
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}