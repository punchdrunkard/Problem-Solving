import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] knight;
    static int[][] targets;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    public static void solve() {
        StringBuilder sb = new StringBuilder();

        int[][] dist = bfs();

        Arrays.stream(targets).forEach(target -> {
            sb.append(dist[target[0]][target[1]]).append(" ");
        });

        System.out.println(sb);
    }

    public static int[][] bfs() {
        int[][] dist = new int[n + 1][n + 1];
        boolean[][] visited = new boolean[n + 1][n + 1];

        int[] DX = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] DY = {-1, 1, -2, 2, -2, 2, -1, 1};

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(knight);
        visited[knight[0]][knight[1]] = true;

        while (!queue.isEmpty()){
            int[] current = queue.poll();

            int cx = current[0];
            int cy = current[1];

            for (int dir = 0; dir < 8; dir++){
                int nx = cx + DX[dir];
                int ny = cy + DY[dir];

                if (!isValidRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                dist[nx][ny] = dist[cx][cy] + 1;
                queue.offer(new int[]{nx, ny});
            }
        }

        return dist;
    }

    public static boolean isValidRange(int x, int y) {
        return 0 < x && x <= n && 0 < y && y <= n;
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size[] = readPair(br);
        n = size[0];
        m = size[1];

        knight = readPair(br);
        targets = new int[m][];

        for (int i = 0; i < m; i++) {
            targets[i] = readPair(br);
        }
    }

    public static int[] readPair(BufferedReader bufferedReader) throws IOException {
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        return new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
    }
}
