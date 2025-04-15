import java.util.*;

// 1. wires 중 하나를 택해서 끊는다.
// 2. 이렇게 끊은 경우, 각 컴포넌트의 갯수를 센다. => 그래프를 만든다.

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = 101;
        
        for (int idx = 0; idx < wires.length; idx++) {
            List<Integer>[] graph = makeGraph(n, wires, idx);
            
            // 먼저 한번 탐색
            boolean[] visited = new boolean[n];
            int component1 = bfs(graph, 0, visited);
            
            for (int node = 0; node < n; node++) {
                if (visited[node]) {
                    continue;
                }
                
                int component2 = bfs(graph, node, visited);
                answer = Math.min(answer, Math.abs(component1 - component2));
            }
        }
        
        return answer;
    }
    
    // 현재 그래프를 탐색 후, 포함된 엣지 갯수를 센다.
    int bfs(List<Integer>[] graph, int st, boolean[] visited) {
        int count = 1;
    
        Queue<Integer> q = new LinkedList<>();
        visited[st] = true;
        q.offer(st);
        
        while (!q.isEmpty()) {
            int sz = q.size();
            
            for (int i = 0; i < sz; i++) {
                // 인접한 원소를 방문하기
                int current = q.poll();
                
                for (int adjNode: graph[current]) {
                    if (visited[adjNode]) {
                        continue;
                    }
                    
                    visited[adjNode] = true;
                    count++;
                    q.offer(adjNode);
                }
            }
        }
        
        return count;
    }
    
    // 그래프를 만든다.
    List<Integer>[] makeGraph(int n, int[][] wires, int excluded) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < wires.length; i++) {
            if (i == excluded) {
                continue;
            }
            
            int[] wire = wires[i];
            int a = wire[0] - 1;
            int b = wire[1] - 1;
            adj[a].add(b);
            adj[b].add(a);
        }
        
        return adj;
    }
}