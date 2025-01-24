class Solution {
    
    static final int UNVISITED = 0;
    static final int VISITING = 1;
    static final int SAFE = 2;

    int[] visited;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int post = prerequisites[i][0];
            int pre = prerequisites[i][1];
            adj[pre].add(post);
        }

        visited = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, adj)) {
                return false;
            }
        }

        return true;
    }

    boolean dfs(int i, List<Integer>[] adj) {
        if (visited[i] == VISITING) {
            return false;
        }

        if (visited[i] == SAFE) {
            return true;
        }

        visited[i] = VISITING;

        for (int j: adj[i]) {
            if (!dfs(j, adj)) {
                return false;
            }
        }
        
        visited[i] = SAFE;
        return true;
    }
}