import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static int[] DX = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] DY = {0, 0, -1, 1, -1, 1, -1, 1};

    static int n, m;
    static int[][] board;
    static boolean[][] visited;
    static Queue<Pair> q = new LinkedList<>();

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        init();
        System.out.println(solve());
    }

    static int solve() {
        int count = 0;

        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (visited[x][y] || board[x][y] == 0) continue;
                bfs(x, y);
                count++;
            }
        }

        return count;
    }

    static void bfs(int x, int y) {
        visited[x][y] = true;
        q.offer(new Pair(x, y));

        while (!q.isEmpty()) {
            Pair c = q.poll();

            for (int dir = 0; dir < 8; dir++) {
                int nx = c.x + DX[dir];
                int ny = c.y + DY[dir];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny] || board[nx][ny] == 0) {
                    continue;
                }

                visited[nx][ny] = true;
                q.offer(new Pair(nx, ny));
            }
        }
    }

    static void init() {
        m = scan.nextInt();
        n = scan.nextInt();

        board = new int[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = scan.nextInt();
            }
        }
    }

    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public String next() {
            try {
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return st.nextToken();
        }

        public String nextLine() {
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
