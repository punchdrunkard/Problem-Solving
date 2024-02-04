import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();

	static int n;
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) {
		n = scan.nextInt();
		System.out.println(solve());
	}

	static int solve() {
		int[] dp = new int[5001];
		Arrays.fill(dp, INF);

		dp[3] = 1;
		dp[5] = 1;

		for (int i = 6; i <= n; i++){
			if (dp[i - 3] != INF) {
				dp[i] = Math.min(dp[i], 1 + dp[i - 3]);
			}

			if (dp[i - 5] != INF){
				dp[i] = Math.min(dp[i], 1 + dp[i - 5]);
			}
		}

		if (dp[n] == INF) return -1;
		return dp[n];
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

	public static class OneLineParser {
		private StringTokenizer st;

		public OneLineParser(String line) {
			st = new StringTokenizer(line);
		}

		public int nextInt() {
			return Integer.parseInt(st.nextToken());
		}

		public Long nextLong() {
			return Long.parseLong(st.nextToken());
		}

		public String nextToken() {
			return st.nextToken();
		}

		public boolean hasMoreTokens() {
			return st.hasMoreTokens();
		}

		public void setNewLine(String line) {
			st = new StringTokenizer(line);
		}
	}
}
