class Solution {

    int[] DX = { -1, 1, 0, 0 };
    int[] DY = { 0, 0, -1, 1 };

    static class State implements Comparable<State> {
        int x, y, val;

        State(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(State o) {
            return Integer.compare(val, o.val);
        }
    }

    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length;
        int n = grid[0].length;
        int k = queries.length;
        int[] answer = new int[k];

        // TODO; BFS 횟수를 최소화하기!
        // sortedQueries[i][0] := i번째 쿼리의 value
        // sortedQueries[i][1] := i번째 쿼리의 원래 인덱스
        int[][] sortedQueries = new int[k][2];
        for (int idx = 0; idx < k; idx++) {
            sortedQueries[idx][0] = queries[idx];
            sortedQueries[idx][1] = idx;
        }
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<State> minHeap = new PriorityQueue<>();
        boolean[][] visited = new boolean[m][n];
        minHeap.offer(new State(0, 0, grid[0][0]));
        visited[0][0] = true;
        int totalPoints = 0;

        for (int[] query : sortedQueries) {
            int qValue = query[0];
            int qIndex = query[1];

            // 해당 쿼리에 대해 bfs 를 확장하기
            while (!minHeap.isEmpty() && minHeap.peek().val < qValue) {
                State current = minHeap.poll();
                totalPoints++;

                for (int dir = 0; dir < 4; dir++) {
                    int nx = current.x + DX[dir];
                    int ny = current.y + DY[dir];

                    if (isOutOfRange(grid, nx, ny) || visited[nx][ny]
                            || current.val >= qValue) {
                        continue;
                    }

                    minHeap.offer(new State(nx, ny, grid[nx][ny]));
                    visited[nx][ny] = true;
                }
            }

            answer[qIndex] = totalPoints;
        }

        return answer;
    }

    boolean isOutOfRange(int[][] grid, int x, int y) {
        return x < 0 || x >= grid.length || y < 0 || y >= grid[0].length;
    }
}