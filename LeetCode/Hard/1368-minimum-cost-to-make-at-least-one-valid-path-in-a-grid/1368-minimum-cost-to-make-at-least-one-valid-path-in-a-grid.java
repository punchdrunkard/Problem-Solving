class Solution {

    static final int[] DX = { 0, 0, 1, -1 };
    static final int[] DY = { 1, -1, 0, 0 };

    int[][] grid;
    int m, n;

    class State {
        int x, y; // position
        int cost;

        State(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    public int minCost(int[][] grid) {
        init(grid);
        return bfs();
    }

    // 0-1 BFS
    int bfs() {
        // init minCost 
        int[][] minCost = new int[m][n];
        for (int[] row : minCost) {
            Arrays.fill(row, Integer.MAX_VALUE); 
        }

        Deque<State> deque = new ArrayDeque<>();
        deque.offer(new State(0, 0, 0));
        minCost[0][0] = 0;

        while (!deque.isEmpty()) {
            State current = deque.pollFirst();
            int cx = current.x;
            int cy = current.y;
            int cost = current.cost;

            // 현재 비용이 기록된 비용보다 크면 스킵 (최적화)
            if (cost > minCost[cx][cy]) {
                continue;
            }

            if (cx == m - 1 && cy == n - 1) {
                return cost;
            }

            for (int dir = 1; dir <= 4; dir++) {
                int nx = cx + DX[dir - 1];
                int ny = cy + DY[dir - 1];
                int nextCost = cost + (dir == grid[cx][cy] ? 0 : 1);

                // 범위 초과 또는 더 적은 비용으로 도달 불가한 경우 스킵
                if (isOutOfRange(nx, ny) || nextCost >= minCost[nx][ny]) {
                    continue;
                }

                // 비용 업데이트
                minCost[nx][ny] = nextCost;

                // 비용에 따라 덱의 앞 또는 뒤에 삽입
                if (dir == grid[cx][cy]) {
                    deque.offerFirst(new State(nx, ny, nextCost));
                } else {
                    deque.offerLast(new State(nx, ny, nextCost));
                }
            }
        }

        return minCost[m - 1][n - 1]; // 도착지점의 최소 비용 반환
    }

    boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= m || y < 0 || y >= n;
    }

    void init(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
    }
}
