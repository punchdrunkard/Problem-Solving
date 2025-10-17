import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();

	public static void main(String[] args) {
		int n = scan.nextInt();
		System.out.println(solve(n));
	}

	static long solve(int n) {
        if (n <= 1) {
            return 0;
        }
        
		// 끝 모양에 따라 정의
		// dp[i][0] := 3 * i 사각형에서 마지막 한 칸까지만 채워진 경우
		// dp[i][1] := 3 * i 사각형에서 마지막 두 칸까지만 채워진 경우
		// dp[i][2] := 3 * i 사각형이 완벽하게 채워진 경우
		long[][] dp = new long[n + 1][3];
        
        dp[1][1] = 0;
		dp[2][0] = 2;
		dp[2][2] = 3;

		for (int i = 3; i <= n; i++) {
			dp[i][0] = dp[i - 1][1];
			dp[i][1] = dp[i - 1][0] + 2 * dp[i - 1][2];
			dp[i][2] = dp[i - 2][2] + dp[i][0];
		}

		return dp[n][2];
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
