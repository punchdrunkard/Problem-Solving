import java.util.*;
import java.io.*;


public class Main {
    static FastReader scan = new FastReader();

    static int n, total;
    static int[][] a;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) {
        init();
        solve();
        System.out.println(answer);
    }

    static void solve() {
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                for (int d1 = 1; d1 <= n; d1++) {
                    for (int d2 = 1; d2 <= n; d2++) {
                        if (!isValidPos(x, y, d1, d2)) {
                            continue;
                        }

                        boolean[][] border = drawBorder(x, y, d1, d2);

                        int s1 = 0;
                        for (int i = 1; i < x + d1; i++) {
                            for (int j = 1; j <= y; j++) {
                                if (border[i][j]) break;
                                s1 += a[i][j];
                            }
                        }

                        int s2 = 0;
                        for (int i = 1; i <= x + d2; i++) {
                            for (int j = n; j > y; j--) {
                                if (border[i][j]) break;
                                s2 += a[i][j];
                            }
                        }

                        int s3 = 0;
                        for (int i = x + d1; i <= n; i++) {
                            for (int j = 1; j < y - d1 + d2; j++) {
                                if (border[i][j]) break;
                                s3 += a[i][j];
                            }
                        }

                        int s4 = 0;
                        for (int i = x + d2 + 1; i <= n; i++) {
                            for (int j = n; j >= y - d1 + d2; j--) {
                                if (border[i][j]) break;
                                s4 += a[i][j];
                            }
                        }

                        int s5 = total - s1 - s2 - s3 - s4;

                        int maxPopulation = Math.max(s1, Math.max(s2, Math.max(s3, Math.max(s4, s5))));
                        int minPopulation = Math.min(s1, Math.min(s2, Math.min(s3, Math.min(s4, s5))));

                        answer = Math.min(answer, maxPopulation - minPopulation);
                    }
                }
            }
        }
    }

    static boolean[][] drawBorder(int x, int y, int d1, int d2) {
        boolean[][] border = new boolean[n + 1][n + 1];

        for (int i = 0; i <= d1; i++) {
            border[x + i][y - i] = true;
            border[x + d2 + i][y + d2 - i] = true;
        }

        for (int i = 0; i <= d2; i++) {
            border[x + i][y + i] = true;
            border[x + d1 + i][y - d1 + i] = true;
        }

        return border;
    }

    static boolean isValidPos(int x, int y, int d1, int d2) {
        int x1 = x + d1;
        int y1 = y - d1;
        int x2 = x + d2;
        int y2 = y + d2;
        int x3 = x + d1 + d2;
        int y3 = y - d1 + d2;

        return isValidRange(x1, y1) && isValidRange(x2, y2) && isValidRange(x3, y3) && isValidRange(x, y);
    }


    static boolean isValidRange(int x, int y) {
        return 1 <= x && x <= n && 1 <= y && y <= n;
    }

    static void init() {
        n = scan.nextInt();
        a = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                a[i][j] = scan.nextInt();
                total += a[i][j];
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
