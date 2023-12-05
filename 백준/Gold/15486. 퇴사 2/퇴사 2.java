import java.io.*;
import java.util.Arrays;

public class Main {
    static int n;
    static int[] t, p;

    // dp[i] : i일에 상담을 했을 때, 얻을 수 있는 최대 이익
    static int[] dp;

    public static void main(String[] args) throws IOException {
        input();

        dp = new int[n + 1];
        dp[n - 1] = n - 1 + t[n - 1] > n ? 0 : p[n - 1];

        for (int i = n - 2; i >= 0; i--){
            int next = i + t[i];
            dp[i] = dp[i + 1];

            if (next <= n){
                dp[i] = Math.max(dp[i + 1], p[i] + dp[next]);
            }
        }

        System.out.println(dp[0]);
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        t = new int[n];
        p = new int[n];

        for (int i = 0; i < n; i++){
            int[] splited = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            t[i] = splited[0];
            p[i]= splited[1];
        }
    }
}
