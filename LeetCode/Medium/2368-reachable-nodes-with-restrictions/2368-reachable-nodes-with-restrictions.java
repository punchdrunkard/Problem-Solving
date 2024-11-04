class Solution {
    
    List<Integer>[] adj;
    boolean[] visited, isRestricted;
    Queue<Integer> q;

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        init(n, edges, restricted);
        return bfs(0);
    }

    // node에서 부터 bfs 탐색을 시작하고, 해당 node에서 도달할 수 있는 노드의 갯수를 반환
    int bfs(int node) {
        int n = adj.length;
        
        visited[node] = true;
        q.offer(node);
        int nodeCount = 0;
        
        while (!q.isEmpty()){
            int current = q.poll();
            
            for (int adjNode: adj[current]) {
                if (visited[adjNode] || isRestricted[adjNode]) {
                    continue;
                }

                q.offer(adjNode);
                visited[adjNode] = true;
            }

            nodeCount++;
        }

        System.out.println(nodeCount);
        return nodeCount;
    }

    void init(int n, int[][] edges, int[] restricted) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        isRestricted = new boolean[n];
        for (int restrictedNode: restricted) {
            isRestricted[restrictedNode] = true;
        }

        // undirected
        for (int[] edge: edges) {
            int st = edge[0];
            int en = edge[1];

            adj[st].add(en);
            adj[en].add(st);
        }

        // bfs init
        visited = new boolean[n];
        
        q = new LinkedList<>();
    }
}