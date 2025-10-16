import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();

		int t = scan.nextInt();
		for (int i = 0; i < t; i++) {
			sb.append(solve(scan.nextInt())).append('\n');
		}
		System.out.println(sb);
	}

	static int solve(int n) {
		// dp[i][k] := i를 1, 2, 3의 합으로 나타낼 때 로 끝나는 조합들의 갯수
		int[][] dp = new int[n + 4][4];
		// 초기화
		dp[1][1] = 1;
		dp[2][1] = 1;
		dp[2][2] = 1;
		dp[3][1] = 1;
		dp[3][2] = 1;
		dp[3][3] = 1;

		for (int i = 3; i <= n; i++) {
			dp[i][1] = dp[i - 1][1];
			dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
			dp[i][3] = dp[i - 1][1] + dp[i - 3][2] + dp[i - 3][3];
		}

		return dp[n][1] + dp[n][2] + dp[n][3];
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
