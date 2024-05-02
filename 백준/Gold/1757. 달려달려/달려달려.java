import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();

    static int n, m;
    static int[] d;

    //dp[i][j] := 현재 시간 i고 지침지수 j일 때 최대한 멀리 갈 수 있는 거리
    static int[][] dp;

    public static void main(String[] args) {
        init();
        System.out.println(solve());
    }

    static int solve() {
        // dp init
        dp = new int[n][m + 1];

        for (int i = 1; i <= m; i++) {
            dp[0][i] = d[0];
        }

        for (int time = 1; time < n; time++) {
            for (int tired = 0; tired <= m; tired++) {
                if (tired == 0) {
                    for (int j = 1; j <= m; j++) { // time - j 시점에 쉬는 경우
                        if (time - j < 0) break;
                        dp[time][tired] = Math.max(Math.max(dp[time - j][j], dp[time - j][0]), dp[time][tired]);
                    }
                } else {
                    dp[time][tired] = dp[time - 1][tired - 1] + d[time];
                }
            }
        }

        // print2D(dp);
        return dp[n - 1][0];
    }

    static void print2D(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                sb.append(arr[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = scan.nextInt();
        }
    }

    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int nextInt() {
            return Integer.parseInt(next());
        }

        String next() {
            try {
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return st.nextToken();
        }
    }
}
