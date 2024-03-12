import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static int n, m, r;
    static int[][] board;

    public static void main(String[] args) {
        input();
        solve();
        print();
    }

    static void print() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(board[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static void solve() {
        int ringCount = Math.min(n, m) / 2;

        // 기준점
        int x1 = 0;
        int y1 = 0;
        int nextN = n;
        int nextM = m;

        for (int count = 0; count < ringCount; count++) {
            // 끝 점
            int x2 = x1 + nextN - 1;
            int y2 = y1 + nextM - 1;

            int mod = 2 * nextN + 2 * nextM - 4;

            for (int i = 0; i < r % mod; i++) {
                rotate(x1, x2, y1, y2);
            }

            x1++;
            y1++;
            nextN -= 2;
            nextM -= 2;
        }
    }

    static void rotate(int x1, int x2, int y1, int y2) {
        int temp = board[x1][y1];

        for (int j = y1; j < y2; j++) board[x1][j] = board[x1][j + 1];
        for (int i = x1; i < x2; i++) board[i][y2] = board[i + 1][y2];
        for (int j = y2; j > y1; j--) board[x2][j] = board[x2][j - 1];
        for (int i = x2; i > x1; i--) board[i][y1] = board[i - 1][y1];

        board[x1 + 1][y1] = temp;
    }


    static void input() {
        n = scan.nextInt();
        m = scan.nextInt();
        r = scan.nextInt();

        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
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

