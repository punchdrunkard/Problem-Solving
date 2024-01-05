import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 10;
    static int answer = MAX + 1;

    static int n, m;
    static char[][] map;

    static Point red, blue, target;

    // 0: 상, 1: 하, 2: 좌, 3: 우
    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        input();
        dfs(red, blue, 0, -1);
        System.out.println(answer > 10 ? -1 : answer);
    }

    // 이동 카운트, 현재 방향
    public static void dfs(Point red, Point blue, int count, int prevDir) {
        // 정답이 될 수 없는 경우
        if (blue.equals(target)) return;
        if (count > 10) return;
        if (answer <= count) return;

        // 정답을 찾은 경우
        if (red.equals(target)) {
            answer = Math.min(answer, count);
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            if (isMovementUnnecessary(prevDir, dir)) continue;

            Point nextRed = move(red, dir);
            Point nextBlue = move(blue, dir);

            // 같은 위치로 이동한 경우, 더 많이 이동한 구슬을 반대 방향으로 한 칸 보낸다.
            if (nextRed.equals(nextBlue)) {
                if (!nextRed.equals(target)) {
                    Point[] validPoints = getValidPoints(nextRed, nextBlue, red, blue, dir);
                    nextRed = validPoints[0];
                    nextBlue = validPoints[1];
                }
            }

            dfs(nextRed, nextBlue, count + 1, dir);
        }
    }

    public static Point[] getValidPoints(Point r, Point b, Point prevR, Point prevB, int dir) {
        Point nextR = r;
        Point nextB = b;

        if (Point.getDist(r, prevR) > Point.getDist(b, prevB)) {
            nextR = new Point(r.x - DX[dir], r.y - DY[dir]);
        } else {
            nextB = new Point(b.x - DX[dir], b.y - DY[dir]);
        }

        return new Point[]{nextR, nextB};
    }

    // point를 dir 방향으로 끝까지 움직임
    public static Point move(Point p, int dir) {
        int x = p.x + DX[dir];
        int y = p.y + DY[dir];

        while (isValidRange(x, y) && map[x][y] != '#' && map[x][y] != 'O') {
            x += DX[dir];
            y += DY[dir];
        }

        // 마지막 유효한 위치로 되돌리기
        if (!isValidRange(x, y) || map[x][y] == '#') {
            x -= DX[dir];
            y -= DY[dir];
        }

        return new Point(x, y);
    }

    public static boolean isValidRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static boolean isMovementUnnecessary(int prev, int next) {
        return next == prev || next == reverseDirection(prev);
    }

    public static int reverseDirection(int dir) {
        if (dir == 0) return 1;
        if (dir == 1) return 0;
        if (dir == 2) return 3;
        if (dir == 3) return 2;
        return -1;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static int getDist(Point p1, Point p2) {
            return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
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
