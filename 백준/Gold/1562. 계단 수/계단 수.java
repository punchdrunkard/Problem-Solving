import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    static final int MOD = 1_000_000_000;
    static int n;
    static long[][][] dp; // 자릿수, 마지막 수, state bit

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // init dp table
        dp = new long[n + 1][10][1 << 10];

        for (long[][] ints : dp) {
            IntStream.range(0, ints.length).forEach(j -> Arrays.fill(ints[j], -1));
        }

        long answer = 0;

        for (int i = 1; i <= 9; i++){
            answer += solve(1, i, 1 << i);
            answer %= MOD;
        }

        System.out.println(answer);
    }

    // count : 자릿 수, prev : 이전 수, visited : 방문 상태
    public static long solve(int count, int prev, int visited) {
        if (dp[count][prev][visited] != -1) return dp[count][prev][visited];

        // base-case : 정답을 찾은 경우 (1-9까지 포함하고, n자리수)
        if (count == n) {
            return dp[count][prev][visited] = (visited == (1 << 10) - 1) ? 1 : 0;
        }

        // 다음 상태로 go
        dp[count][prev][visited] = 0;

        if (prev > 0){
            dp[count][prev][visited] += solve(count + 1, prev - 1, visited | (1 << (prev - 1)));
            dp[count][prev][visited] %= MOD;
        }

        if (prev < 9){
            dp[count][prev][visited] += solve(count + 1, prev + 1, visited | (1 << (prev + 1)));
            dp[count][prev][visited] %= MOD;
        }

        return dp[count][prev][visited] % MOD;
    }
}
