import java.util.*;
import java.io.*;

public class Main {

    static FastReader scan = new FastReader();
    static final int EMPTY = -1;

    static int rows, cols, golemCount;

    // 골렘의 이동 방향 (중앙 기준)
    static final int[] GOLEM_DX = { 1, 1, 1 };
    static final int[] GOLEM_DY = { 0, -1, 1 };

    // 4방향: 북, 동, 남, 서
    static final int[] DX = { -1, 0, 1, 0 };
    static final int[] DY = { 0, 1, 0, -1 };

    static Golem[] golems;
    static int[][] golemMap; // 골렘 위치 정보
    static boolean[][] exitMap; // 출구 위치 정보
    static Queue<Pair> bfsQueue;
    static int answer;

    // 골렘 클래스
    static class Golem {
        int centerX, centerY, exitDirection;

        public Golem(int initialY, int exitDirection) {
            this.centerX = -1; // 초기값으로 -1 설정
            this.centerY = initialY;
            this.exitDirection = exitDirection;
        }

        @Override
        public String toString() {
            return "중앙위치: " + new Pair(centerX, centerY) + ", 출구 방향: " + exitDirection;
        }
    }

    // 좌표 쌍 클래스
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        init();
        solve();
        System.out.println(answer);
    }

    // 풀이 함수
    static void solve() {
        for (int i = 0; i < golemCount; i++) {
            moveGolem(i);
            if (golems[i].centerX <= 1) {
                clearGolemMap();
                continue;
            }
            placeGolem(i);
            answer += moveSpirit(i);
        }
    }

    // 골렘을 이동시키는 함수
    static void moveGolem(int idx) {
        while (true) {
            Pair center = new Pair(golems[idx].centerX, golems[idx].centerY);
            if (canMoveDown(center)) {
                move(idx, 0);
            } else if (canMoveLeft(center) && canMoveDown(new Pair(center.x, center.y - 1))) {
                move(idx, 1);
            } else if (canMoveRight(center) && canMoveDown(new Pair(center.x, center.y + 1))) {
                move(idx, 2);
            } else {
                break;
            }
        }
    }

    // 골렘 위치 설정 함수
    static void placeGolem(int idx) {
        int cx = golems[idx].centerX;
        int cy = golems[idx].centerY;
        int ed = golems[idx].exitDirection;

        for (int dir = 0; dir < 4; dir++) {
            int x = cx + DX[dir];
            int y = cy + DY[dir];
            golemMap[x][y] = idx;
        }
        golemMap[cx][cy] = idx;
        exitMap[cx + DX[ed]][cy + DY[ed]] = true;
    }

    // 정령을 이동시키는 함수
    static int moveSpirit(int idx) {
        boolean[][] visited = new boolean[rows + 1][cols + 1];
        Pair start = new Pair(golems[idx].centerX, golems[idx].centerY);
        bfsQueue.offer(start);
        visited[start.x][start.y] = true;
        int maxRow = start.x;

        while (!bfsQueue.isEmpty()) {
            Pair current = bfsQueue.poll();
            int cx = current.x;
            int cy = current.y;
            int currentArea = golemMap[cx][cy];

            for (int dir = 0; dir < 4; dir++) {
                int nx = cx + DX[dir];
                int ny = cy + DY[dir];

                if (isOutOfBound(nx, ny) || visited[nx][ny]) continue;

                int nextArea = golemMap[nx][ny];
                if (currentArea == nextArea || (exitMap[cx][cy] && nextArea != EMPTY)) {
                    visited[nx][ny] = true;
                    bfsQueue.offer(new Pair(nx, ny));
                    maxRow = Math.max(maxRow, nx);
                }
            }
        }

        return maxRow;
    }

    // 골렘 이동 함수
    static void move(int idx, int direction) {
        golems[idx].centerX += GOLEM_DX[direction];
        golems[idx].centerY += GOLEM_DY[direction];
        golems[idx].exitDirection = rotateExitDirection(direction, golems[idx].exitDirection);
    }

    // 출구 방향 회전 함수
    static int rotateExitDirection(int moveDirection, int currentExitDirection) {
        if (moveDirection == 1) {
            return (4 + currentExitDirection - 1) % 4; // 반시계 회전
        } else if (moveDirection == 2) {
            return (currentExitDirection + 1) % 4; // 시계 회전
        }
        return currentExitDirection;
    }

    // 경계 체크 함수
    static boolean canMoveDown(Pair pos) { return canMoveToDirection(pos, 1, 0); }
    static boolean canMoveLeft(Pair pos) { return canMoveToDirection(pos, 0, -1); }
    static boolean canMoveRight(Pair pos) { return canMoveToDirection(pos, 0, 1); }

    // 공통 골렘 이동 경계 체크 함수
    static boolean canMoveToDirection(Pair pos, int dx, int dy) {
        int nextX = pos.x + dx;
        int nextY = pos.y + dy;
        for (int d = 0; d < 4; d++) {
            int x = nextX + DX[d];
            int y = nextY + DY[d];
            if (isValidRange(x, y) && golemMap[x][y] != EMPTY) {
                return false;
            }
        }
        return true;
    }

    // 경계 체크 함수
    static boolean isValidRange(int x, int y) {
        return x >= 1 && x <= rows && y >= 1 && y <= cols;
    }

    static boolean isOutOfBound(int x, int y) {
        return x < 1 || x > rows || y < 1 || y > cols;
    }

    // 골렘 정보 초기화 함수
    static void clearGolemMap() {
        for (int i = 1; i <= rows; i++) {
            Arrays.fill(golemMap[i], EMPTY);
            Arrays.fill(exitMap[i], false);
        }
    }

    // 초기화 함수
    static void init() {
        rows = scan.nextInt();
        cols = scan.nextInt();
        golemCount = scan.nextInt();

        golems = new Golem[golemCount];
        golemMap = new int[rows + 1][cols + 1];
        exitMap = new boolean[rows + 1][cols + 1];
        answer = 0;
        clearGolemMap();

        for (int i = 0; i < golemCount; i++) {
            golems[i] = new Golem(scan.nextInt(), scan.nextInt());
        }

        bfsQueue = new LinkedList<>();
    }

    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int nextInt() {
            return Integer.parseInt(next());
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
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
