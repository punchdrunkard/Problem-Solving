import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int MOD = 15746;
	static FastReader scan = new FastReader();

	public static void main(String[] args) {
		int n = scan.nextInt();
		System.out.println(solve(n));
	}

	static int solve(int n){
		if (n == 1) return 1;

		int[] dp = new int[n + 1]; // dp[n] := n자리 2진 수열의 갯수
		dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i <= n; i++){
			dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
		}

		return dp[n] % MOD;
	}


	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String s) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(new File(s)));
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

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
