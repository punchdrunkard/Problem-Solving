import java.util.*;
import java.io.*;


public class Main {
    static FastReader scan = new FastReader();

    // TODO: 꽃을 심을 수 있는 세 자리를 고르기
    static int[] DX = {-1, 1, 0, 0};
    static int[] DY = {0, 0, -1, 1};

    static final int INF = Integer.MAX_VALUE;

    static int answer = INF;

    static int n;
    static int[][] board;
    static int[] fx, fy; // 꽃들의 좌표

    public static void main(String[] args) {
        init();
        dfs(-1, 0, 0);
        System.out.println(answer);
    }

    static boolean[][] visited;

    static void dfs(int lastPick, int count, int cost) {
        if (lastPick >= n * n) return;


        if (count == 3) {
            answer = Math.min(answer, cost);
            return;
        }

        for (int i = lastPick + 1; i < n * n; i++) {
            if (!isValid(fx[i], fy[i])) {
                continue;
            }

            int nextCost = cost;

            visited[fx[i]][fy[i]] = true;
            nextCost += board[fx[i]][fy[i]];

            for (int dir = 0; dir < 4; dir++) {
                int nx = fx[i] + DX[dir];
                int ny = fy[i] + DY[dir];

                visited[nx][ny] = true;
                nextCost += board[nx][ny];
            }

            dfs(i, count + 1, nextCost);

            visited[fx[i]][fy[i]] = false;

            for (int dir = 0; dir < 4; dir++) {
                int nx = fx[i] + DX[dir];
                int ny = fy[i] + DY[dir];
                visited[nx][ny] = false;
            }
        }

    }

    static boolean isValid(int x, int y) {
//        System.out.println("Main.isValid");
//        System.out.println("x = " + x + ", y = " + y);

        if (!inRange(x, y) || visited[x][y]) {
            return false;
        }

        // 인접 네 방향에 대해서...
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];

//            System.out.println("nx = " + nx);
//            System.out.println("ny = " + ny);

            if (!inRange(nx, ny) || visited[nx][ny]) {
                return false;
            }
        }

        return true;
    }

    static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }


    static void init() {
        n = scan.nextInt();
        board = new int[n][n];
        visited = new boolean[n][n];
        fx = new int[n * n];
        fy = new int[n * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = scan.nextInt();
                fx[n * i + j] = i;
                fy[n * i + j] = j;
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
