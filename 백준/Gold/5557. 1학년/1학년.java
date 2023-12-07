import java.io.*;
import java.util.Arrays;
import java.util.function.Predicate;

public class Main {
    static int n; // 숫자의 갯수
    static int[] numbers;

    // dp[index][sum] := index 번까지 결과가 sum이 나오는 올바른 수식의 갯수
    static long[][] dp;

    static final int SUM_MAX = 20; // 이 값을 넘을 수 없다.
    static int target; // 마지막으로 나와야 하는 결과 값

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    public static void solve() {
        preprocess();
        fillDpTable();

        long answer = dp[n - 2][target];
        System.out.println(answer);
    }

    public static void fillDpTable() {
        for (int i = 1; i < n - 1; i++){
            int currentNumber = numbers[i];

            for (int sum = 0; sum <= SUM_MAX; sum++){
                if (dp[i - 1][sum] == 0) continue; // 존재하지 않으면 continue
                int nextSumResult = sum + currentNumber;
                int nextMinusResult = sum - currentNumber;


                if (isValidRange.test(nextSumResult)) {
                    dp[i][nextSumResult] += dp[i - 1][sum];
                }

                if (isValidRange.test(nextMinusResult)) {
                    dp[i][nextMinusResult] += dp[i - 1][sum];
                }
            }
        }
    }

    public static Predicate<Integer> isValidRange = (number) -> 0 <= number && number <= SUM_MAX;
    public static void preprocess() {
        dp = new long[n][SUM_MAX + 1];
        int firstNumber = numbers[0];

        // numbers 안에는 0에서 9까지의 값만 존재하므로
        dp[0][firstNumber] = 1;
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        target = numbers[n - 1];
    }
}
