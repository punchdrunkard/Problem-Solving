import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static String s1, s2;
	static int[][] dp;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		// base case -> 전부 insert 만 하는 경우
		for (int i = 0; i <= s1.length(); i++) {
			dp[i][0] = i;
		}

		for (int j = 0; j <= s2.length(); j++) {
			dp[0][j] = j;
		}

		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					int insert = 1 + dp[i][j - 1];
					int delete = 1 + dp[i - 1][j];
					int replace = 1 + dp[i - 1][j - 1];

					dp[i][j] = Math.min(insert, Math.min(delete, replace));
				}
			}
		}

		return dp[s1.length()][s2.length()];
	}

	static void init() {
		s1 = scan.next();
		s2 = scan.next();
		dp = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		long nextLong() {
			return Long.parseLong(next());
		}

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
