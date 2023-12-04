import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int MOD = 1_000_000_000;
    static final int MAX = 101;

    // dp[자릿수][마지막으로 채운 수][state]
    static long[][][] dp = new long[MAX][10][1 << 10];

    public static long solve(int n) {
        // dp init : 자릿수가 1인 계단수는 한 개이다.
        for (int i = 0; i <= 9; i++) {
            dp[1][i][(1 << i)] = 1;
        }

        // bottom-up
        for (int digit = 2; digit <= n; digit++) { // 현재 자릿수
            for (int last = 0; last <= 9; last++) { // 마지막으로 채운 수
                for (int bit = 0; bit < (1 << 10); bit++) { // 비트 -> num을 포함 시킴
                    if (last > 0) {
                        dp[digit][last][bit | (1 << last)] += (dp[digit - 1][last - 1][bit] % MOD);
                    }

                    if (last < 9) {
                        dp[digit][last][bit | (1 << last)] += (dp[digit - 1][last + 1][bit] % MOD);
                    }
                }
            }
        }

        // 답 찾기 : 1로 끝나는 계단 수 ~ 9로 끝나는 계단 수
        long answer = 0;

        for (int start = 1; start <= 9; start++) {
            answer += (dp[n][start][(1 << 10) - 1] % MOD);
        }

        return answer % MOD;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(solve(n));
    }

}
