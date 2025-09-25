import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();

	static int n, k;
	static int[] w, v;

	// memo[i][w] := 현재 인덱스 i까지 봤고, 무게가 w일 때 얻을 수 있는 최대 가치
	static int[][] memo;

	public static void main(String[] args) {
		init();
		System.out.println(dp(0, 0));
	}

	static int dp(int i, int currentWeight) {
		// base case
		if (i >= n) {
			return 0;
		}

		// memoization
		if (memo[i][currentWeight] != -1) {
			return memo[i][currentWeight];
		}

		// recursive case
		// 1. i번째 물건을 선택하지 않았을 때
		memo[i][currentWeight] = dp(i + 1, currentWeight);

		// 2. i번째 물건을 선택했을 떄
		int nextWeight = currentWeight + w[i];
		if (nextWeight <= k) {
			memo[i][currentWeight] = Math.max(
				memo[i][currentWeight],
				dp(i + 1, nextWeight) + v[i]);
		}

		return memo[i][currentWeight];
	}

	static void init() {
		n = scan.nextInt();
		k = scan.nextInt();

		w = new int[n];
		v = new int[n];
		for (int i = 0; i < n; i++) {
			w[i] = scan.nextInt();
			v[i] = scan.nextInt();
		}

		memo = new int[n][100001];
		for (int i = 0; i < n; i++) {
			Arrays.fill(memo[i], -1);
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
