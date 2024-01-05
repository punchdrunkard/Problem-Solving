import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 10;
    static int n, m;
    static char[][] map;
    static Point red, blue, target; // 빨간 구슬, 파란 구슬 좌표

    // 0: 상, 1: 하, 2: 좌, 3: 우
    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};

    static final int INIT_DIR = 4;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solve());
    }

    public static int solve() {
        int answer = MAX + 1;

        Queue<State> queue = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[MAX][MAX][MAX][MAX];

        State initialState = new State(red, blue, 0, INIT_DIR);
        queue.offer(initialState);

        while (!queue.isEmpty()) {
            State current = queue.poll();
            int count = current.count;
            Point currentRed = current.red;
            Point currentBlue = current.blue;

            // 안되는 경우들
            if (currentBlue.equals(target)) {
                continue;
            }

            if (count > 10) {
                continue;
            }

            if (answer < count) {
                continue;
            }

            // 도착한 경우
            if (currentRed.equals(target)) {
                answer = Math.min(answer, count);
                continue;
            }

            // 구슬을 이동시킨다.
            for (int dir = 0; dir < 4; dir++) {
                if (isMovementUnnecessary(dir, current.dir)) continue;

                Point nextRed = move(currentRed, dir);
                Point nextBlue = move(currentBlue, dir);

                // 구슬이 같은 곳으로 이동한다면?
                if (nextRed.equals(nextBlue)) {
                    if (!nextRed.equals(target)) {
                        Point[] points = processPoint(nextRed, nextBlue, currentRed, currentBlue, dir);
                        nextRed = points[0];
                        nextBlue = points[1];
                    }
                }

                if (visited[nextRed.x][nextRed.y][nextBlue.x][nextBlue.y]) continue;

                State newState = new State(nextRed, nextBlue, count + 1, dir);
                visited[nextRed.x][nextRed.y][nextBlue.x][nextBlue.y] = true;
                queue.offer(newState);
            }
        }

        return answer > 10 ? -1 : answer;
    }

    public static boolean isMovementUnnecessary(int dir, int prevDir) {
        return dir == prevDir || dir == reverseDirection(prevDir);
    }

    public static int reverseDirection(int dir) {
        if (dir == 0) return 1;
        if (dir == 1) return 0;
        if (dir == 2) return 3;
        if (dir == 3) return 2;

        return -1;
    }

    // 구슬의 위치를 조정한다.
    public static Point[] processPoint(Point red, Point blue, Point prevRed, Point prevBlue, int dir) {
        Point nextRed = red;
        Point nextBlue = blue;

        int redDist = getDist(red, prevRed);
        int blueDist = getDist(blue, prevBlue);

        if (redDist > blueDist) {
            nextRed = new Point(red.x - DX[dir], red.y - DY[dir]);
        } else {
            nextBlue = new Point(blue.x - DX[dir], blue.y - DY[dir]);
        }

        return new Point[]{nextRed, nextBlue};
    }

    public static int getDist(Point prev, Point next){
        return Math.abs(prev.x - next.x) + Math.abs(prev.y - next.y);
    }

    // 좌표 p를 dir 방향으로 이동시킬 수 있을 때 까지 이동시킨다.ㄴ
    public static Point move(Point p, int dir) {
        int x = p.x;
        int y = p.y;

        while (true) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];

            // 더 이상 다음으로 이동할 수 없는 경우
            if (!isValidRange(nx, ny)) break;
            if (map[nx][ny] == '#') break;

            x = nx;
            y = ny;

            if (map[nx][ny] == 'O') break;
        }

        return new Point(x, y);
    }

    public static boolean isValidRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    static class State { // 탐색 상태 정의
        Point red, blue;
        int count;
        int dir;

        public State(Point red, Point blue, int count, int dir) {
            this.red = red;
            this.blue = blue;
            this.count = count;
            this.dir = dir;
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'R') {
                    red = new Point(i, j);
                } else if (map[i][j] == 'B') {
                    blue = new Point(i, j);
                } else if (map[i][j] == 'O') {
                    target = new Point(i, j);
                }
            }
        }
    }
}
