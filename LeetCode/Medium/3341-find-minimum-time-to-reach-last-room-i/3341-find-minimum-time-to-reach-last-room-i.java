class Solution {

    final int[] DX = { -1, 1, 0, 0 };
    final int[] DY = { 0, 0, -1, 1 };
    final int INF = Integer.MAX_VALUE;

    int n, m;

    public int minTimeToReach(int[][] moveTime) {
        init(moveTime);
        return solve(moveTime);
    }

    int solve(int[][] moveTime) {
        PriorityQueue<State> pq = new PriorityQueue<>();
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }

        // for starting point
        State start = new State(0, 0, 0);
        dist[0][0] = 0;
        pq.offer(start);

        while (!pq.isEmpty()) {

            State current = pq.poll();
            System.out.println(current);

            int cx = current.x;
            int cy = current.y;
            int cTime = current.time;

            // dijkstra 검증
            if (cTime > dist[cx][cy]) {
                continue;
            }

            if (cx == n - 1 && cy == m - 1) {
                return dist[cx][cy];
            }

            for (int d = 0; d < 4; d++) {
                int nx = cx + DX[d];
                int ny = cy + DY[d];

                if (isOutOfRange(nx, ny) || dist[nx][ny] != INF) {
                    continue;
                }

                int nTime = Math.max(cTime, moveTime[nx][ny]) + 1;
                if (dist[nx][ny] > nTime) {
                    pq.offer(new State(nx, ny, nTime));
                    dist[nx][ny] = nTime;
                }
            }
        } // end of pq-loop

        return dist[n - 1][m - 1];
    }

    boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    class State implements Comparable<State> {
        int x, y, time;

        State(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public int compareTo(State o) {
            return Integer.compare(time, o.time);
        }

        @Override
        public String toString() {
            return "x : " + x + ", y : " + y + ", time: " + time;
        }
    }

    void init(int[][] moveTime) {
        n = moveTime.length;
        m = moveTime[0].length;
    }
}