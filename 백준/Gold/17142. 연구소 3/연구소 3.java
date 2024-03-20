import java.util.*;
import java.io.*;

public class Main {
    static FastReader scan = new FastReader();
    static int[] DX = {-1, 1, 0, 0};
    static int[] DY = {0, 0, -1, 1};
    static final int EMPTY = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m, virusCount;
    static int[][] board;
    static boolean[] isActive;
    static int[] vx, vy;

    static int minTime = Integer.MAX_VALUE;

    public static void main(String[] args) {
        init();
        dfs(0, 0);
        System.out.println(minTime == Integer.MAX_VALUE ? -1 : minTime);
    }

    static void dfs(int idx, int count) {
        if (idx > virusCount) {
            return;
        }

        if (count == m) {
            minTime = Math.min(minTime, getFillTime());
            return;
        }

        for (int i = idx; i < virusCount; i++) {
            isActive[i] = true;
            dfs(i + 1, count + 1);
            isActive[i] = false;
        }
    }

    static int getFillTime() {
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = -1;
            }
        }

        for (int i = 0; i < virusCount; i++) {
            if (isActive[i]) {
                q.offer(new Pair(vx[i], vy[i]));
                visited[vx[i]][vy[i]] = true;
                dist[vx[i]][vy[i]] = 0;
            }
        }

        int maxTime = 0;

        while (!q.isEmpty()) {
            Pair current = q.poll();
            int cx = current.x;
            int cy = current.y;

            for (int dir = 0; dir < 4; dir++) {
                int nx = cx + DX[dir];
                int ny = cy + DY[dir];

                if (!isValidRange(nx, ny) || visited[nx][ny] || board[nx][ny] == WALL) {
                    continue;
                }

                dist[nx][ny] = dist[cx][cy] + 1;
                
                if (board[nx][ny] == EMPTY) { // dist는 빈칸까지 전파되는 거리를 의미하므로
                    maxTime = Math.max(maxTime, dist[nx][ny]);
                    if (maxTime >= minTime) return Integer.MAX_VALUE;
                }

                q.offer(new Pair(nx, ny));
                visited[nx][ny] = true;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == EMPTY && !visited[i][j]) { // 바이러스가 다 퍼지지 않은 경우
                    return Integer.MAX_VALUE;
                }
            }
        }

        return maxTime;
    }

    static void print2DArr(int[][] arr) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(arr[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static void init() {
        n = scan.nextInt();
        m = scan.nextInt();

        board = new int[n][n];

        virusCount = 0;
        vx = new int[10];
        vy = new int[10];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = scan.nextInt();

                if (board[i][j] == VIRUS) {
                    vx[virusCount] = i;
                    vy[virusCount] = j;
                    virusCount++;
                }
            }
        }

        isActive = new boolean[virusCount];
    }

    static boolean isValidRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }
    }
}
