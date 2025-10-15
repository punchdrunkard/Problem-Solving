import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();

	static int n, m;
	static int[] a, c;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {

		// dp[i][c] := i번째 인덱스까지 봤을 때, 비용 c로 만들 수 있는 메모리의 최댓값
		long[][] dp = new long[n + 1][100 * 100 + 1];
		for (int i = 1; i <= n; i++) {
			for (int cost = 0; cost <= 100 * 100; cost++) {
				dp[i][cost] = dp[i - 1][cost];
				if (cost - c[i] >= 0) {
					dp[i][cost] = Math.max(dp[i][cost], dp[i - 1][cost - c[i]] + a[i]);
				}
			}
		}

		return search(dp[n]);
	}

	static int search(long[] arr) {

		int lo = -1;
		int hi = 100 * 100 + 1;

		while (lo + 1 < hi) {
			int mid = lo + (hi - lo) / 2;

			if (!(arr[mid] >= m)) {
				lo = mid;
			} else {
				hi = mid;
			}
		}

		return hi;
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		a = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			a[i] = scan.nextInt();
		}
		c = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			c[i] = scan.nextInt();
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
