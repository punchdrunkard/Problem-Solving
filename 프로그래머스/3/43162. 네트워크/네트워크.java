import java.util.*;

class Solution {

    // 네트워크의 갯수 = connected component 의 갯수!
    public int solution(int n, int[][] computers) {
        // 1. 그래프 만들기
        List<Integer>[] adj = createGraph(n, computers);
        // System.out.println(Arrays.toString(adj));
        // 2. connected component 의 갯수 세기 
        int answer = countNetwork(n, adj);

        return answer;
    }

    int countNetwork(int n, List<Integer>[] adj) {
        int count = 0;
        boolean[] visited = new boolean[n];

        for (int st = 0; st < n; st++) {
            if (visited[st]) {
                continue;
            }

            bfs(st, adj, visited);
            count++;

        }

        return count;
    }

    void bfs(int st, List<Integer>[] adj, boolean[] visited) {
        visited[st] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(st);

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int current = q.poll();

                for (int next : adj[current]) {
                    if (visited[next]) {
                        continue;
                    }

                    visited[next] = true;
                    q.offer(next);
                }

            }
        }
    } // end of bfs

    List<Integer>[] createGraph(int n, int[][] computers) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j] == 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) { // compters[i][i]는 항상 1 
                    continue;
                }

                if (computers[i][j] == 1) {
                    adj[i].add(j);
                }
            }
        }

        return adj;
    } // end of createGraph
}