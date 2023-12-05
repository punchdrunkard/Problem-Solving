import java.io.*;

public class Main {
    static int t; // 테스트 케이스
    static final int MOD = 1_000_000_009;
    static int[] numbers;
    static int n_max = -1;

    static long[] dp;

    public static void main(String[] args) throws IOException {
        input();
        preprocessing();
        calc();
        printAnswer();
    }

    public static void printAnswer(){
        for (int n: numbers){
            System.out.println(dp[n] % MOD);
        }
    }
    public static void calc(){
        for (int i = 4; i <= n_max; i++){
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % MOD;
            dp[i] %=  MOD;
        }
    }

    public static void preprocessing(){
        dp = new long[n_max + 1];

        dp[1] = 1;
        dp[2] = 2; // 1 + 1, 2
        dp[3] = 4; // 1 + 1 + 1, 2 + 1,  1 + 2, 3
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        numbers = new int[t];

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            numbers[i] = n;
            n_max = Math.max(n_max, n);
        }
    }
}
