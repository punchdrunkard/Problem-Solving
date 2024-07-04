import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int t, n;
	static final int MOD = 1_000_000_009;
	static StringBuilder sb = new StringBuilder();

	// dp[n][0] := n을 1,2,3의 합으로 나타낼 때 사용한 수의 갯수가 홀수
	// dp[n][1] := n을 1,2,3의 합으로 나타낼 때 사용한 수의 갯수가 짝수
	static long[][] dp = new long[100001][2];

	public static void main(String[] args) {
		t = scan.nextInt();

		solve();

		for (int i = 0; i < t; i++) {
			n = scan.nextInt();
			sb.append(dp[n][0] % MOD).append(' ').append(dp[n][1] % MOD).append('\n');
		}

		System.out.println(sb);
	}

	static void solve() {
		// preprocess
		dp[1][0] = 1; // 1 = 1
		dp[2][0] = 1; // 2 = 2
		dp[2][1] = 1; // 2 = 1 + 1
		dp[3][0] = 2; // 3 = 3, 3 = 1 + 1 + 1
		dp[3][1] = 2; // 3 = 2 + 1, 1 + 2

		for (int i = 4; i <= 100000; i++) {
			dp[i][0] = (dp[i - 1][1] + dp[i - 2][1] + dp[i - 3][1]) % MOD;
			dp[i][1] = (dp[i - 1][0] + dp[i - 2][0] + dp[i - 3][0]) % MOD;
		}
	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int nextInt() {
			return Integer.parseInt(next());
		}

		String next() {
			try {
				while (st == null || !st.hasMoreTokens()) {
					st = new StringTokenizer(br.readLine());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return st.nextToken();
		}
	}
}
