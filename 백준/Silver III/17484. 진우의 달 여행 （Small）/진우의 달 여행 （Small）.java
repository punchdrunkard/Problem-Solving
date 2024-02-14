import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int[] DX = {1, 1, 1};
    static final int[] DY = {-1, 0, 1};

    static FastReader scan = new FastReader();
    static int n, m;
    static int[][] matrix;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) {
        input();

        for (int i = 0; i < m; i++){
            dfs(0, i, -1, 0);
        }

        System.out.println(answer);
    }

    public static void dfs(int x, int y, int prev, int cost){
        // 도착한 경우
        if (x >= n) {
            answer = Math.min(answer, cost);
            return;
        }

        // 안되는 경우
        if (y >= m){
            return;
        }

        for (int dir = 0; dir < 3; dir++){
            if (dir == prev) {
                continue;
            }

            int nx = x + DX[dir];
            int ny = y + DY[dir];

            if (nx < 0 || ny < 0) {
                continue;
            }

            dfs(nx, ny, dir, cost + matrix[x][y]);
        }
    }


    public static void input() {
        n = scan.nextInt();
        m = scan.nextInt();
        matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scan.nextInt();
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