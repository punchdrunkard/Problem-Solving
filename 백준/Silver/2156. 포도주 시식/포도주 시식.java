import java.io.*;

public class Main {
    static int n;
    static int[] grapes;

    // dp[i][j] := i번째 잔까지 왔을 때, j개 연속 마셨을 때 최대 포도주의 양
    static int[][] dp;


    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    public static void solve() {
        preprocess();
        fillDpTable();
        System.out.println(findAnswer());
    }

    public static int findAnswer(){
        return Math.max(Math.max(dp[n][0], dp[n][1]), dp[n][2]);
    }

    public static void fillDpTable(){
        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.max(Math.max(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]);
            dp[i][1] = dp[i - 1][0] + grapes[i];
            dp[i][2] = dp[i - 1][1] + grapes[i];
        }
    }

    public static void preprocess() {
        dp = new int[n + 1][3];

        dp[1][0] = 0;
        dp[1][1] = grapes[1];
        dp[1][2] = 0;
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        grapes = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            grapes[i] = Integer.parseInt(br.readLine());
        }
    }
}
