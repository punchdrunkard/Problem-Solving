import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 위, 아래, 왼쪽, 오른쪽
    static final int[] DX = {0, -1, 1, 0, 0};
    static final int[] DY = {0, 0, 0, -1, 1};

    static FastReader scan = new FastReader();

    static int r, c, k;
    static boolean[][] isObstacle;
    static boolean[][] visited;
    static int[] dirOrder = new int[4]; // 이동 순서
    static int rx, ry;

    public static void main(String[] args) {
        input();
        solve();
    }

    static void solve() {
        int dir = 0; // 초기 dir 인덱스 값

        while (true) {
            // dir 방향으로 이동한다.
            int nx = rx + DX[dirOrder[dir]];
            int ny = ry + DY[dirOrder[dir]];

            int nextDir = dir;

            // 벽이나 방문한 지역, 장애물을 만날 경우
            while (!isValidRange(nx, ny) || visited[nx][ny] || isObstacle[nx][ny]) {
                // 방향을 전환한다.
                nextDir = getNextDir(nextDir);


                // 더 이상 갈 수 없음 == 방향을 전환했는데 처음의 위치가 나옴
                if (nextDir == dir) {
                    System.out.println(rx + " " + ry);
                    return;
                }

                nx = rx + DX[dirOrder[nextDir]];
                ny = ry + DY[dirOrder[nextDir]];
            }

            rx = nx;
            ry = ny;
            dir = nextDir;
            visited[rx][ry] = true;
        }
    }

    static int getNextDir(int idx) {
        if (idx == 3) return 0;
        return idx + 1;
    }

    static boolean isValidRange(int x, int y) {
        return 0 <= x && x < r && 0 <= y && y < c;
    }

    static void input() {
        r = scan.nextInt();
        c = scan.nextInt();
        k = scan.nextInt();
        isObstacle = new boolean[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < k; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();

            isObstacle[x][y] = true;
        }

        rx = scan.nextInt();
        ry = scan.nextInt();
        visited[rx][ry] = true;

        for (int i = 0; i < 4; i++) {
            dirOrder[i] = scan.nextInt();
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
