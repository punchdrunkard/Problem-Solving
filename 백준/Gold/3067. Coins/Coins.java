import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int n, m;
	static int[] coins;
	static int[][] cache;

	public static void main(String[] args) {
		int t = scan.nextInt();
		StringBuilder answer = new StringBuilder();

		for (int testCase = 0; testCase < t; testCase++) {
			init();
			answer.append(solve()).append('\n');
		}

		System.out.println(answer);
	}

	static int solve() {
		cache = new int[n + 1][m + 1];
		for (int i = 0; i < cache.length; i++) {
			Arrays.fill(cache[i], -1);
		}

		return dp(n, m);
	}

	// i개의 동전을 고려하고, cost 를 만들 수 있는 방법의 수
	static int dp(int i, int cost) {

		// memoization
		if (cache[i][cost] != -1) {
			return cache[i][cost];
		}

		// basecase
		if (cost == 0) {
			return 1;
		}

		if (i == 0 || cost < 0) {
			return 0;
		}

		// 현재 동전을 포함하지 않는 경우
		cache[i][cost] = dp(i - 1, cost);

		// 현재 동전을 포함하는 경우
		if (cost - coins[i - 1] >= 0) {
			cache[i][cost] += dp(i, cost - coins[i - 1]);
		}

		return cache[i][cost];
	}

	static void init() {
		n = scan.nextInt();
		coins = new int[n];
		for (int i = 0; i < n; i++) {
			coins[i] = scan.nextInt();
		}
		m = scan.nextInt();
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
