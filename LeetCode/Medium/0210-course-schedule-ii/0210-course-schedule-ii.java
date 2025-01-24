class Solution {
    // topology sort
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        List<Integer>[] adj = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        int[] indegree = new int[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            int pre = prerequisites[i][1];
            int post = prerequisites[i][0];

            adj[pre].add(post);
            indegree[post]++;
        }

        List<Integer> answer = new ArrayList<>();

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                answer.add(i);
            }
        }

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int node : adj[current]) {
                indegree[node]--;

                if (indegree[node] == 0) {
                    q.offer(node);
                    answer.add(node);
                }
            }
        }

        if (answer.size() != numCourses) {
            return new int[] {};
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}