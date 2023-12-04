import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static final int MAX = 16;
    static final int INF = (int) 1e9;
    static int n;
    static int[][] w;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        input();

        dp = new int[n][1 << n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(solve(0, 1)); // (1 << 0)
    }

    public static int solve(int current, int visited) {
        // 이미 방문한 경우
        if (dp[current][visited] != -1) return dp[current][visited];

        // base-case : 모든 도시를 방문한 경우 (-> 시작점 0으로 돌아와야 함)
        if (visited == (1 << n) - 1) {
            // 시작점으로 방문할 수 없는 경우
            if (w[current][0] == 0) {
                return INF;
            }
            return w[current][0];
        }

        dp[current][visited] = INF;

        for (int i = 0; i < n; i++) {
            // 이미 해당 도시를 방문했거나, 방문할 수 없는 경우
            if ((visited & (1 << i)) != 0 || w[current][i] == 0) {
                continue;
            }
            dp[current][visited] = Math.min(dp[current][visited], solve(i, visited | (1 << i)) + w[current][i]);
        }

        return dp[current][visited];
    }


    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        w = new int[n][n];
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            w[i] = Arrays.stream(row.split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }

}
