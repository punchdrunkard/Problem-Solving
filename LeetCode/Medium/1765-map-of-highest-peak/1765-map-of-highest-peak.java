class Solution {

    record Pair(int x, int y) {
    };

    static final int LAND = 0;
    static final int WATER = 1;
    static final int[] DX = { -1, 1, 0, 0 };
    static final int[] DY = { 0, 0, -1, 1 };

    int m, n;
    List<Pair> waters;

    public int[][] highestPeak(int[][] isWater) {
        init(isWater);

        int[][] heightMap = new int[m][n];
        boolean[][] visited = new boolean[m][n];

        Queue<Pair> q = new LinkedList<>();
        for (Pair water : waters) {
            q.offer(water);
            visited[water.x][water.y] = true;
        }

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Pair current = q.poll();
                int cx = current.x;
                int cy = current.y;

                for (int dir = 0; dir < 4; dir++) {
                    int nx = cx + DX[dir];
                    int ny = cy + DY[dir];

                    if (isOutOfRange(nx, ny) || visited[nx][ny]) {
                        continue;
                    }

                    heightMap[nx][ny] = heightMap[cx][cy] + 1;
                    visited[nx][ny] = true;
                    q.offer(new Pair(nx, ny));
                }
            }
        }

        return heightMap;
    }

    boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= m || y < 0 || y >= n;
    }

    void init(int[][] isWater) {
        m = isWater.length;
        n = isWater[0].length;
        waters = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == WATER) {
                    waters.add(new Pair(i, j));
                }
            }
        }
    }
}