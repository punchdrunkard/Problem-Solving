import java.util.*;
import java.io.*;

public class Main {
    static FastReader scan = new FastReader();
    static final int VIRUS = 2;
    static final int EMPTY = 0;
    static final int WALL = 1;
    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};

    static class Virus {
        int x, y;

        Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m, virusCount;
    static int[][] board;
    static Virus[] virus;

    static boolean[] selected;

    static int minTime = Integer.MAX_VALUE;
    static int emptyCount = 0; // 채워야하는 칸 개수

    public static void main(String[] args) {
        init();
        dfs(-1, 0);
        System.out.println(minTime == Integer.MAX_VALUE ? -1 : minTime);
    }

    // 바이러스 m개를 고르기
    static void dfs(int lastPick, int count) {
        if (lastPick >= virusCount) {
            return;
        }

        if (count == m) {
            minTime = Math.min(bfs(), minTime);
            return;
        }

        for (int i = lastPick + 1; i < virusCount; i++) {
            selected[i] = true;
            dfs(i, count + 1);
            selected[i] = false;
        }
    }

    // 바이러스가 퍼지는 최대 시간
    static int bfs() {
        Queue<Virus> q = new LinkedList<>();
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = -1;
            }
        }

        for (int i = 0; i < virusCount; i++) {
            if (selected[i]) {
                int vx = virus[i].x;
                int vy = virus[i].y;
                dist[vx][vy] = 0;
                q.offer(virus[i]);
            }
        }

        int fillCount = 0;
        int time = 0;

        while (!q.isEmpty()) {
            Virus current = q.poll();
            int cx = current.x;
            int cy = current.y;

            for (int dir = 0; dir < 4; dir++) {
                int nx = cx + DX[dir];
                int ny = cy + DY[dir];

                if (!isValidRange(nx, ny) || dist[nx][ny] != -1 || board[nx][ny] == WALL) {
                    continue;
                }

                dist[nx][ny] = dist[cx][cy] + 1;
                time = Math.max(time, dist[nx][ny]);
                if (time >= minTime) return Integer.MAX_VALUE;

                fillCount++;
                q.offer(new Virus(nx, ny));
            }
        }

        if (fillCount != emptyCount) {
            return Integer.MAX_VALUE;
        }

        return time;
    }

    static boolean isValidRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < n;
    }


    static void init() {
        n = scan.nextInt();
        m = scan.nextInt();

        board = new int[n][n];
        virus = new Virus[10];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = scan.nextInt();

                if (board[i][j] == VIRUS) {
                    virus[virusCount] = new Virus(i, j);
                    virusCount++;
                } else if (board[i][j] == WALL){
                    emptyCount--;
                }
            }
        }

        emptyCount += n * n - m;
        selected = new boolean[virusCount];
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
