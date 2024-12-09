import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static char[] s1, s2;
	static int[][] memo;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		memo = initDpTable();
		preprocessBaseCase();

		int maxLength = 0;

		for (int i = 0; i <= s1.length; i++) {
			for (int j = 0; j <= s2.length; j++) {
				maxLength = Math.max(maxLength, dp(i, j)); // 최대 길이 갱신
			}
		}

		return maxLength;
	}

	static int dp(int i, int j) {
		if (memo[i][j] != -1) {
			return memo[i][j];
		}

		// base case
		if (i == 0 || j == 0) {
			memo[i][j] = 0;
			return 0;
		}

		if (s1[i - 1] == s2[j - 1]) {
			memo[i][j] = dp(i - 1, j - 1) + 1;
		} else {
			memo[i][j] = 0;
		}

		return memo[i][j];
	}

	static void preprocessBaseCase() {
		for (int i = 0; i <= s1.length; i++) {
			memo[i][0] = 0;
		}

		for (int i = 0; i <= s2.length; i++) {
			memo[0][i] = 0;
		}
	}

	static int[][] initDpTable() {
		int[][] memo = new int[s1.length + 1][s2.length + 1];
		for (int i = 0; i <= s1.length; i++) {
			for (int j = 0; j <= s2.length; j++) {
				memo[i][j] = -1;
			}
		}

		return memo;
	}

	static void init() {
		s1 = scan.next().toCharArray();
		s2 = scan.next().toCharArray();
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
