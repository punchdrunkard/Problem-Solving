class Solution {

    public int countCompleteComponents(int n, int[][] edges) {
        List<Integer>[] graph = createGraph(n, edges);

        int completeComponents = 0;
        boolean[] visited = new boolean[n];

        for (int vertex = 0; vertex < n; vertex++) {
            // bfs 를 통해 connected component 를 찾는다.
            if (visited[vertex]) {
                continue;
            }

            List<Integer> component = bfs(vertex, graph, visited);
   
            if (isComplete(component, graph)) {
                completeComponents++;
            }
        }

        return completeComponents;
    }

    // 해당 connected component 가 complete 인지 확인한다.
    // 서로 direct edge 가 존재한다. => 모든 정점은 (전체 노드수 - 1) 개의 edge 가 존재
    boolean isComplete(List<Integer> component, List<Integer>[] graph) {
        int totalVertex = component.size();

        for (int vertex : component) {
            if (graph[vertex].size() != totalVertex - 1) {
                return false;
            }
        }

        return true;
    }

    // start 에서 graph 를 bfs 탐색하고, 탐색한 노드를 반환하는 함수
    List<Integer> bfs(int start, List<Integer>[] graph, boolean[] visited) {
        List<Integer> component = new ArrayList<>();

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        component.add(start);

        while (!q.isEmpty()) {
            int sz = q.size();

            for (int i = 0; i < sz; i++) {
                int vertex = q.poll();

                // vertex 에 연결되어 있는 노드들에 대해
                for (int neighbor : graph[vertex]) {
                    if (visited[neighbor]) {
                        continue;
                    }

                    q.offer(neighbor);
                    visited[neighbor] = true;
                    component.add(neighbor);
                }
            }
        }

        return component;
    }

    List<Integer>[] createGraph(int n, int[][] edges) {
        List<Integer>[] adj = new ArrayList[n];
        for (int vertex = 0; vertex < n; vertex++) {
            adj[vertex] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        return adj;
    }
}