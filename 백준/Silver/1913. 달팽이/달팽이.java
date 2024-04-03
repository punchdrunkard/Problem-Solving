import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();

    static int[] DX = {-1, 0, 1, 0};
    static int[] DY = {0, 1, 0, -1};

    static int n, target;
    static int[][] arr;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        n = scan.nextInt();
        target = scan.nextInt();
        arr = new int[n][n];

        solve();

        int x = -1;
        int y = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(arr[i][j]).append(' ');
                if (arr[i][j] == target) {
                    x = i;
                    y = j;
                }
            }
            sb.append('\n');
        }

        sb.append(x + 1).append(' ').append(y + 1);

        System.out.println(sb);
    }

    private static void solve() {
        boolean[][] visited = new boolean[n][n];

        int cx = n / 2;
        int cy = n / 2;
        int dir = -1;

        arr[cx][cy] = 1;

        int count = 1;

        while (cx != 0 || cy != 0) {
            visited[cx][cy] = true;

            int nd = (dir + 1) % 4;
            int nx = cx + DX[nd];
            int ny = cy + DY[nd];

            if (visited[nx][ny]) {
                nd = dir; // 이전 방향 유지
                nx = cx + DX[nd];
                ny = cy + DY[nd];
            }

            arr[nx][ny] = ++count;

            cx = nx;
            cy = ny;
            dir = nd;
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
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return st.nextToken();
        }

        public String nextLine() {
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
