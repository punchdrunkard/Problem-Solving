import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static final int WALL = 1;
    static final int EMPTY = 0;
    static final int INF = Integer.MAX_VALUE;

    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};

    static int n, m, fuel;
    static int[][] board;
    static int bx, by; // 택시의 위치
    static int[] cx, cy, tx, ty; // 손님의 현재 위치, 목적지
    static boolean[] arrived;

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
        while (fuel != 0 && !allArrived()) { // 연료가 다 떨어졌거나 모두 이동시킬 때 까지
            // 1. 손님을 고른다.
            int passenger = select();

            if (passenger == -1) {
                return -1;
            }

            // 2. 이동
            // 2-1. 택시 -> 손님
            if (!moveTaxi(cx[passenger], cy[passenger], false)) {
                return -1;
            }

            // 2-2. 택시 -> 목적지
            if (!moveTaxi(tx[passenger], ty[passenger], true)) {
                return -1;
            }

            arrived[passenger] = true;
            // printProgress();
        }

        if (!allArrived()) {
            return -1;
        }

        return fuel;
    }

    static boolean moveTaxi(int nx, int ny, boolean isDestination) { // 택시를 nx, ny로 이동시킨다. 이동에 성공하면 true리턴
        int distance = getMinDistance(bx, by, nx, ny);

        if (distance > fuel) {
            return false;
        }

        bx = nx;
        by = ny;
        fuel -= distance;

        if (isDestination) {
            fuel += (2 * distance);
        }

        return true;
    }

    // 승객을 고르는 함수
    static int select() {
        int selected = -1;
        int minDistance = INF;

        for (int i = 0; i < m; i++) {
            if (arrived[i]) {
                continue;
            }

            if (selected == -1) {
                selected = i;
                minDistance = getMinDistance(bx, by, cx[i], cy[i]);
                continue;
            }

            int nextDistance = getMinDistance(bx, by, cx[i], cy[i]);

            if (shouldUpdate(cx[selected], cy[selected], minDistance,
                    cx[i], cy[i], nextDistance)) {
                minDistance = nextDistance;
                selected = i;
            }
        }

        return selected;
    }

    static boolean shouldUpdate(int x, int y, int dist,
                                int nx, int ny, int nDist) {
        if (dist != nDist) {
            return dist > nDist;
        }

        if (x != nx) {
            return x > nx;
        }

        return y > ny;
    }

    // x, y ~ targetX, targetY 최단거리 구하는 함수
    static int getMinDistance(int x, int y, int targetX, int targetY) {
        Queue<Pair> q = new LinkedList<>();
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = INF;
            }
        }

        dist[x][y] = 0;
        q.offer(new Pair(x, y));

        while (!q.isEmpty()) {
            Pair current = q.poll();

            if (current.x == targetX && current.y == targetY) {
                return dist[targetX][targetY];
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = current.x + DX[dir];
                int ny = current.y + DY[dir];

                if (!isValidRange(nx, ny) || dist[nx][ny] != INF || board[nx][ny] == WALL) {
                    continue;
                }

                dist[nx][ny] = dist[current.x][current.y] + 1;
                q.offer(new Pair(nx, ny));
            }
        }

        return dist[targetX][targetY];
    }

    static boolean allArrived() {
        for (int i = 0; i < m; i++) {
            if (!arrived[i]) return false;
        }

        return true;
    }

    static boolean isValidRange(int x, int y) {
        return 1 <= x && x <= n && 1 <= y && y <= n;
    }

    static void printProgress() {
        System.out.println("택시의 위치 : " + bx + ", " + by);
        System.out.println("남은 연료 : " + fuel);
        System.out.println("=== 승객의 위치들 ===");
        System.out.println(Arrays.toString(cx));
        System.out.println(Arrays.toString(cy));
    }

    static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        fuel = scan.nextInt();

        board = new int[n + 1][n + 1];
        cx = new int[m];
        cy = new int[m];
        tx = new int[m];
        ty = new int[m];
        arrived = new boolean[m];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                board[i][j] = scan.nextInt();
            }
        }

        bx = scan.nextInt();
        by = scan.nextInt();

        for (int i = 0; i < m; i++) {
            cx[i] = scan.nextInt();
            cy[i] = scan.nextInt();
            tx[i] = scan.nextInt();
            ty[i] = scan.nextInt();
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
                while (st == null || !st.hasMoreElements()) {
                    st = new StringTokenizer(br.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return st.nextToken();
        }
    }
}
