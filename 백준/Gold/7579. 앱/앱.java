import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int n, m;
	static int[] using, deactivateCosts;
	static int[][] cache;

	public static void main(String[] args) {
		init();
		int answer = Integer.MAX_VALUE;

		for (int cost = 0; cost <= 10000; cost++) {
			if (dp(n, cost) >= m) {
				answer = cost;
				break;
			}
		}
		System.out.println(answer);
	}

	// dp[idx][cost]: idx개의 앱을 고려하고, cost 비용으로 확보할 수 있는 최대 메모리
	static int dp(int idx, int cost) {
		// Base Case: 앱이 없으면 확보할 메모리가 없음
		if (idx == 0) {
			return 0;
		}

		// 메모이제이션 반환
		if (cache[idx][cost] != -1) {
			return cache[idx][cost];
		}

		// 현재 앱을 종료하지 않는 경우
		int result = dp(idx - 1, cost);

		// 현재 앱을 종료하는 경우
		if (cost >= deactivateCosts[idx - 1]) {
			result = Math.max(result,
				dp(idx - 1, cost - deactivateCosts[idx - 1]) + using[idx - 1]
			);
		}

		// 결과 저장
		cache[idx][cost] = result;
		return result;
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		using = new int[n];
		deactivateCosts = new int[n];
		for (int i = 0; i < n; i++) {
			using[i] = scan.nextInt();
		}

		for (int i = 0; i < n; i++) {
			deactivateCosts[i] = scan.nextInt();
		}

		cache = new int[n + 1][10001];
		for (int i = 0; i <= n; i++) {
			Arrays.fill(cache[i], -1);
		}
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
