class Solution {
    // multi source bfs

    int[] DX = { -1, 1, 0, 0 };
    int[] DY = { 0, 0, -1, 1 };

    int m, n;

    public int[][] updateMatrix(int[][] mat) {
        init(mat);
        return bfs(mat);
    }

    int[][] bfs(int[][] mat) {
        int[][] result = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    q.offer(new Pair(i, j));
                    visited[i][j] = true;
                }
            }
        }

        int dist = 1;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                Pair current = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = current.x + DX[d];
                    int ny = current.y + DY[d];

                    if (isOutOfBound(nx, ny) || visited[nx][ny]) {
                        continue;
                    }

                    result[nx][ny] = dist;
                    visited[nx][ny] = true;
                    q.offer(new Pair(nx, ny));
                }
            }

            dist++;
        }

        return result;
    }

    boolean isOutOfBound(int x, int y) {
        return x < 0 || x >= m || y < 0 || y >= n;
    }

    void init(int[][] mat) {
        m = mat.length;
        n = mat[0].length;
    }

    class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}