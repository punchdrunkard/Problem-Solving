import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int n, t;
	static int[] k, s;
	static int[][] cache;

	public static void main(String[] args) {
		init();
		System.out.println(dp(n, t));
	}

	// i개의 과목을 고려했을 때, time 시간만큼 공부하면 얻을 수 있는 최대 점수
	static int dp(int i, int time) {

		if (i == 0 || time == 0) {
			return 0;
		}

		if (cache[i][time] != -1) {
			return cache[i][time];
		}

		// 현재 과목 선택 X
		cache[i][time] = dp(i - 1, time);

		// 현재 과목 선택 O
		if (time - k[i - 1] >= 0) {
			cache[i][time] =
				Math.max(cache[i][time], dp(i - 1, time - k[i - 1]) + s[i - 1]);
		}

		return cache[i][time];
	}

	static void init() {
		n = scan.nextInt();
		t = scan.nextInt();
		k = new int[n];
		s = new int[n];
		for (int i = 0; i < n; i++) {
			k[i] = scan.nextInt();
			s[i] = scan.nextInt();
		}

		cache = new int[n + 1][t + 1];
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
