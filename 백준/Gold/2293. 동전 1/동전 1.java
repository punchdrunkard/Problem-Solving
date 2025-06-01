import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static int n, k;
	static int[] coins;

	// cache[i] := i 원을 만드는데 필요한 경우의 수
	static int[][] cache;

	public static void main(String[] args) {
		init();
		System.out.println(dp(0, k));
	}

	static int dp(int idx, int value) {
		if (value == 0) {
			return 1;
		}

		if (idx >= n) {
			return 0;
		}

		if (value < 0) {
			return 0;
		}

		if (cache[idx][value] != -1) {
			return cache[idx][value];
		}

		int result = 0;
		// 현재 동전을 사용하지 않는 경우
		result += dp(idx + 1, value);

		// 현재 동전을 사용하는 경우
		if (value >= coins[idx]) {
			result += dp(idx, value - coins[idx]);
		}

		cache[idx][value] = result;
		return result;
	}

	static void init() {
		n = scan.nextInt();
		k = scan.nextInt();
		coins = new int[n];
		for (int i = 0; i < n; i++) {
			coins[i] = scan.nextInt();
		}

		cache = new int[n][k + 1];
		for (int i = 0; i < n; i++) {
			Arrays.fill(cache[i], -1);
		}
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
