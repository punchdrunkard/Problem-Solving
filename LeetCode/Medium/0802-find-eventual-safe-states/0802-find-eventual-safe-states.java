class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> reverseGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            reverseGraph.add(new ArrayList<>());
        }

        // Build reverse graph
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j : graph[i]) {
                reverseGraph.get(j).add(i);
                indegree[i]++;
            }
        }

        // Topological sort
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        boolean[] marked = new boolean[n];
        while (!q.isEmpty()) {
            int current = q.poll();
            marked[current] = true;

            for (int neighbor : reverseGraph.get(current)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }

        // Collect safe nodes
        List<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (marked[i]) {
                safeNodes.add(i);
            }
        }
        Collections.sort(safeNodes);
        return safeNodes;
    }
}
