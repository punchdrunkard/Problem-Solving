import java.io.*;

public class Main {
    static int testCase; // 테스트 케이스
    static int[] targets; // 구하고자 하는 n들
    // dp[i][j] := j로 끝나는 줄어들지 않는 i자리 수의 개수
    static long[][] dp;

    static final int N_MAX = 64;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    public static void solve(){
        preprocessDpTable();
        fillDpTable();

        for (int target: targets){
            System.out.println(findAnswer(target));
        }
    }

    public static long findAnswer(int n){
        long answer = 0;

        for (int i = 0; i <= 9; i++){
            answer += dp[n][i];
        }

        return answer;
    }
    public static void fillDpTable(){
        for (int len = 2; len <= N_MAX; len++){ // 자릿수에 대해
            for (int number = 0; number <= 9; number++){ // 마지막 수에 대해
                for (int prev = 0; prev <= number; prev++){
                    dp[len][number] += dp[len - 1][prev];
                }
            }
        }
    }

    public static void preprocessDpTable(){
        dp = new long[N_MAX + 1][10];

        // 1자리수의 경우, 그 자신이다.
        for (int number = 0; number <= 9; number++) {
            dp[1][number] = 1;
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());
        targets = new int[testCase];

        for (int tc = 0; tc < testCase; tc++){
            targets[tc] = Integer.parseInt(br.readLine());
        }
    }
}
