class Solution {

    static final int UNVISITED = 0;
    static final int VISITING = 1;
    static final int SAFE = 2;

    int n;
    int[] visited;

    public List<Integer> eventualSafeNodes(int[][] graph) {
        n = graph.length;
        visited = new int[n];

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (dfs(i, graph)) {
                answer.add(i);
            }
        }

        return answer;    
    }

    boolean dfs(int i, int[][] graph) {
        // 현재 방문 중인 노드를 다시 방문하면 사이클
        if (visited[i] == VISITING) {
            return false;
        }

        if (visited[i] == SAFE) {
            return true;
        }

        visited[i] = VISITING;
        
        for (int node: graph[i]) {
            if (!dfs(node, graph)) {
                return false;
            }
        }

        visited[i] = SAFE;
        return true; 
    }
}