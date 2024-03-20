import java.util.*;
import java.io.*;

public class Main {
    static FastReader scan = new FastReader();
    static int n, m, k;
    static int[][] board;

    static boolean[] selected;

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Pair[] points;
    static int INF = Integer.MIN_VALUE;
    static int answer = INF;

    public static void main(String[] args) {
        init();
        dfs(-1, 0, 0);
        System.out.println(answer == INF ? -1 : answer);
    }

    static void dfs(int lastPick, int count, int sum) {
        if (lastPick >= n * m) {
            return;
        }

        if (count == k) {
            if (!isNearBy()) { // 각 점이 유효한지 확인 후 답 갱신
                answer = Math.max(answer, sum);
            }

            return;
        }

        for (int i = lastPick + 1; i < n * m; i++) {
            selected[i] = true;
            dfs(i, count + 1, sum + board[points[i].x][points[i].y]);
            selected[i] = false;
        }
    }

    // 인접하다 -> 각 좌표값의 차이의 합이 1
    static boolean isNearBy() {
        // 유효한 인덱스들을 찾는다.
        int cnt = 0;
        int[] active = new int[k];

        for (int i = 0; i < n * m; i++) {
            if (selected[i]) {
                active[cnt++] = i;
            }
        }

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (i == j) continue;
                if (getDistance(points[active[i]], points[active[j]]) == 1){
                    return true;
                }
            }
        }

        return false;
    }

    static int getDistance(Pair p1, Pair p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }


    static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        k = scan.nextInt();

        board = new int[n][m];
        selected = new boolean[n * m];
        points = new Pair[n * m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = scan.nextInt();
                points[i * m + j] = new Pair(i, j);
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
