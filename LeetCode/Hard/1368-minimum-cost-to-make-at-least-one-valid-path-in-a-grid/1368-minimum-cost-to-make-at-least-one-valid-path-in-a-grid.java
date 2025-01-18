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

        boolean[][] visited = new boolean[m][n];
        Deque<State> deque = new ArrayDeque<>();
        deque.offer(new State(0, 0, 0));

        while (!deque.isEmpty()) {
            int size = deque.size();

            for (int i = 0; i < size; i++) {
                State current = deque.pollFirst();
                int cx = current.x;
                int cy = current.y;
                int cost = current.cost;

                if (visited[cx][cy]) {
                    continue;
                }
                visited[cx][cy] = true;

                if (cx == m - 1 && cy == n - 1) {
                    return cost;
                }

                for (int dir = 1; dir <= 4; dir++) {
                    int nx = cx + DX[dir - 1];
                    int ny = cy + DY[dir - 1];

                    if (isOutOfRange(nx, ny) || visited[nx][ny]) {
                        continue;
                    }

                    if (dir == grid[cx][cy]) {
                        deque.offerFirst(new State(nx, ny, cost)); 
                    } else { // modify direction
                        deque.offerLast(new State(nx, ny, cost + 1)); 
                    }
                }
            }
        }

        return -1; 
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
