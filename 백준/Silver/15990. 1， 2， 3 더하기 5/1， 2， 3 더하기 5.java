import java.io.*;

public class Main {
    static int t; // 테스트 케이스
    static int[] numbers;

    // dp[i][j] := i를 1,2,3 의 합으로 나타냈을 때, j로 끝나는 경우의 수
    static long[][] dp;

    static final int N_MAX = 100_001;
    static final int MOD = 1_000_000_009;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    public static void solve(){
        preprocess();

        for (int i = 3; i <= N_MAX; i++){
            dp[i][1] = dp[i - 1][2] + dp[i - 1][3];
            dp[i][1] %= MOD;

            dp[i][2] = dp[i - 2][1] + dp[i - 2][3];
            dp[i][2] %= MOD;

            if (i > 3) {
                dp[i][3] = dp[i - 3][1] + dp[i - 3][2];
                dp[i][3] %= MOD;
            }
        }

        for (int n : numbers){
            long answer = 0;

            for (int i = 1; i <= 3; i++){
                answer += dp[n][i];
                answer %= MOD;
            }

            System.out.println(answer);
        }
    }

    public static void preprocess(){
        dp = new long[N_MAX + 1][4];

        dp[1][1] = 1; // 1
        dp[2][2] = 1; // 2
        dp[3][3] = 1; // 3
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        numbers = new int[t];

        for (int tc = 0; tc < t; tc++){
            numbers[tc] = Integer.parseInt(br.readLine());
        }
    }
}
