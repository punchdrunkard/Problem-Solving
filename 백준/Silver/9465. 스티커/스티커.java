import java.io.*;
import java.util.Arrays;

public class Main {
    static int t; // 테스트 케이스
    static int[][] dp;

    static int[][] stickers;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {
            n = Integer.parseInt(br.readLine());
            stickers = new int[2][n];

            for (int j = 0; j < 2; j++) {
                String[] line = br.readLine().split(" ");
                stickers[j] = Arrays.stream(line).mapToInt(Integer::parseInt).toArray();
            }

            solve();
        }
    }

    public static void solve() {
        preprocess();
        fillDpTable();
        System.out.println(findAnswer());
    }

    public static int findAnswer() {
        return Math.max(Math.max(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]);
    }

    public static void fillDpTable() {
        for (int i = 1; i < n; i++) {
            dp[i][0] = stickers[0][i] + Math.max(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = stickers[1][i] + Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = Math.max(Math.max(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]);
        }
    }
    public static void preprocess() {
        dp = new int[n][3];

        dp[0][0] = stickers[0][0];
        dp[0][1] = stickers[1][0];
        dp[0][2] = 0;
    }
}
