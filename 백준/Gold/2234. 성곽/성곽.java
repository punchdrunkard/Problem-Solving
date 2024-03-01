import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static FastReader scan = new FastReader();

    static class State {
        int x;
        int y;
        int wall; // 벽 현재 칸에 벽 존재 상태

        State(int x, int y, int wall) {
            this.x = x;
            this.y = y;
            this.wall = wall;
        }
    }

    static int[][] board;
    static int n, m;
    static boolean[][] visited;

    public static void main(String[] args) {
        input();
        solve();
    }

    static void solve() {
        int count = 0; // 이 섬에 있는 방의 갯수
        int largest = -1; // 가장 넓은 방의 넓이

        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (visited[x][y]) continue;

                count++;
                largest = Math.max(largest, bfs(x, y));
            }
        }

        System.out.println(count);
        System.out.println(largest);

        resetVisited();
        int largestIfRemoveWall = -1;

        // 존재하는 모든 방에서 벽을 하나씩 없애 보기
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                // 벽을 없앤다.
                for (int dir = 0; dir < 4; dir++) {
                    if ((board[x][y] & (1 << dir)) == 0) continue;

                    resetVisited();
                    board[x][y] -= (1 << dir);
                    largestIfRemoveWall = Math.max(largestIfRemoveWall, bfs(x, y));
                    board[x][y] += (1 << dir); // 원상 복구
                }
            }
        }

        System.out.println(largestIfRemoveWall);
    }

    static int bfs(int x, int y) {
        int[] DX = {0, -1, 0, 1}; // 서 북 동 남
        int[] DY = {-1, 0, 1, 0};

        int size = 1;

        Deque<State> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.offer(new State(x, y, board[x][y]));

        while (!queue.isEmpty()) {
            State current = queue.poll();
            int cx = current.x;
            int cy = current.y;

            for (int dir = 0; dir < 4; dir++) {
                // 현재 방향으로 벽이 있다면 방문할 수 없음
                if ((current.wall & (1 << dir)) != 0) continue;

                int nx = cx + DX[dir];
                int ny = cy + DY[dir];

                if (!isValidRange(nx, ny) || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                size++;
                queue.offer(new State(nx, ny, board[nx][ny]));
            }
        }

        return size;
    }

    static boolean isValidRange(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }

    static void resetVisited(){
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
            }
        }
    }

    static void input() {
        n = scan.nextInt();
        m = scan.nextInt();

        board = new int[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
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
