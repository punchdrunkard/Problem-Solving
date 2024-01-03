import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n; // 편의점의 갯수
    static Point[] stores; // 편의점 좌표
    static Point start, target;

    static final int MAX_DISTANCE = 1000; // 한 번에 갈 수 있는 최대 거리

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            input();
            System.out.println(bfs() ? "happy" : "sad");
        }
    }

    public static boolean bfs() {
        Queue<Point> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n]; // visited[i] := i번째 편의점을 이미 방문하였는가?
        q.offer(start);

        while (!q.isEmpty()) {
            Point current = q.poll();
            if (canArrive(current, target)) return true;

            // 편의점에 들러야 하는 경우
            for (int storeIdx = 0; storeIdx < n; storeIdx++) {
                if (visited[storeIdx]) continue;
                if (!canArrive(current, stores[storeIdx])) continue;

                q.offer(stores[storeIdx]);
                visited[storeIdx] = true;
            }
        }

        return false;
    }

    public static boolean canArrive(Point p1, Point p2){
        return getDistance(p1, p2) <= MAX_DISTANCE;
    }

    public static int getDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
    
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        start = readPointInput();
        stores = new Point[n];

        for (int i = 0; i < n; i++) {
            stores[i] = readPointInput();
        }

        target = readPointInput();
    }

    public static Point readPointInput() throws IOException {
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        return new Point(inputs[0], inputs[1]);
    }

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
