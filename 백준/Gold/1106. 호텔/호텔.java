import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int c, n;
	static int[] costs, clients;
	static int[][] cache;

	public static void main(String[] args) {
		init();

		int answer = Integer.MAX_VALUE;

		for (int currentCost = 0; currentCost < 100001; currentCost++) {
			if (dp(n, currentCost) >= c) {
				answer = currentCost;
				break;
			}
		}

		System.out.println(answer);
	}

	// dp(i, cost) := i개의 도시를 고려했을 때, cost 원 써서 증가시킬 수 있는 고객의 수
	static int dp(int i, int cost) {

		if (i == 0 || cost == 0) {
			return 0;
		}

		if (cache[i][cost] != -1) {
			return cache[i][cost];
		}

		// 현재 도시 선택 X
		cache[i][cost] = dp(i - 1, cost);

		// 현재 도시 선택
		if (cost - costs[i - 1] >= 0) {
			cache[i][cost] = Math.max(cache[i][cost], (dp(i, cost - costs[i - 1]) + clients[i - 1]));
		}

		return cache[i][cost];
	}

	static void init() {
		c = scan.nextInt(); // 고객을 "적어도" c명 늘리기
		n = scan.nextInt();
		costs = new int[n];
		clients = new int[n];
		for (int i = 0; i < n; i++) {
			costs[i] = scan.nextInt();
			clients[i] = scan.nextInt();
		}

		cache = new int[n + 1][100001];
		for (int i = 0; i < cache.length; i++) {
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
