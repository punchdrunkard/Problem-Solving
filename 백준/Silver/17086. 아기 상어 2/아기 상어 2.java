import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int[] DX = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int[] DY = {-1, 0, 1, -1, 1, -1, 0, 1};

    static FastReader scan = new FastReader();
    static int n, m;
    static int[][] board;

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        input();
        System.out.println(solve());
    }

    static int solve() {
        int[][] dist = findDist();
        return findMax(dist);
    }

    // multi-source bfs
    // 상어들을 기준으로 각 칸의 거리를 잰다.
    static int[][] findDist(){
        Deque<Point> q = new LinkedList<>();
        int[][] dist = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) {
                    q.offer(new Point(i, j));
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = Integer.MAX_VALUE; // 아직 방문하지 않음
                }
            }
        }

        while (!q.isEmpty()) {
            Point current = q.poll();
            int cx = current.x;
            int cy = current.y;

            for (int dir = 0; dir < 8; dir++) {
                int nx = cx + DX[dir];
                int ny = cy + DY[dir];

                if (!isValidRange(nx, ny)) {
                    continue;
                }

                if (dist[nx][ny] > dist[cx][cy] + 1) {
                    dist[nx][ny] = dist[cx][cy] + 1;
                    q.offer(new Point(nx, ny));
                }
            }
        }

        return dist;
    }


    static int findMax(int[][] dist) {
        int res = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res = Math.max(res, dist[i][j]);
            }
        }

        return res;
    }

    static boolean isValidRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    static void input() {
        n = scan.nextInt();
        m = scan.nextInt();
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = scan.nextInt();
            }
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
