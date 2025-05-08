class Solution {
    // 마지막 이동의 상태에 따라 이동하는 시간이 변한다! 
    // 가중치가 1이 아님! (이동시간이 가중치이므로)

    static final int[] DX = { -1, 1, 0, 0 };
    static final int[] DY = { 0, 0, -1, 1 };
    static final int INF = Integer.MAX_VALUE;

    int n, m;

    public int minTimeToReach(int[][] moveTime) {
        init(moveTime);
        return solve(moveTime);
    }

    int solve(int[][] moveTime) {

        // dist[i][j] := (i, j) 로 도달하기 위한 최단시간
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }

        PriorityQueue<State> pq = new PriorityQueue<>();
        State start = new State(0, 0, 0, 0);
        dist[0][0] = 0;
        pq.offer(start);

        while (!pq.isEmpty()) {
            State current = pq.poll();
            int cx = current.x;
            int cy = current.y;
            int cDist = current.dist;

            // validate shortest path
            if (dist[cx][cy] < cDist) {
                continue;
            }

            if (cx == n - 1 && cy == m - 1) {
                return dist[cx][cy];
            }

            // for adj rooms
            for (int dir = 0; dir < 4; dir++) {
                int nx = cx + DX[dir];
                int ny = cy + DY[dir];

                if (isOutOfRange(nx, ny)) {
                    continue;
                }

                // 다음 이동할 때 걸리는 시간
                int moveCost = (current.cnt % 2 == 0) ? 1 : 2;
                int nDist = Math.max(moveTime[nx][ny], cDist) + moveCost;

                if (dist[nx][ny] > nDist) {
                    dist[nx][ny] = nDist;
                    State next = new State(nx, ny, nDist, current.cnt + 1);
                    pq.offer(next);
                }
            } // end of adj 
        }

        return dist[n - 1][m - 1];
    }

    class State implements Comparable<State> {
        int x, y; // for location
        int dist;
        int cnt; // 이동 횟

        public State(int x, int y, int dist, int cnt) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(State o) {
            return Integer.compare(dist, o.dist);
        }

        @Override
        public String toString() {
            return "x: " + x + ", y: " + y + "dist: " + dist + ", cnt: " + cnt;
        }
    }

    boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    void init(int[][] moveTime) {
        n = moveTime.length;
        m = moveTime[0].length;
    }
}