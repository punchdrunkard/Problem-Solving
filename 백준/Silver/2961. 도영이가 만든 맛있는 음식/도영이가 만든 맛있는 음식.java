import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static int n;
	static Pair[] ingredients;

	static long answer = Long.MAX_VALUE;
	static boolean[] visited;

	static class Pair<T> {
		T s, v;

		Pair(T s, T v) {
			this.s = s;
			this.v = v;
		}
	}

	public static void main(String[] args) {
		init();
		solve(0, 0, 0, 0);
		System.out.println(answer);
	}

	static void solve(long sSum, long vSum, int idx, int count) {
		if (idx == n && count > 0) {
			long diff = Math.abs(sSum - vSum);
			answer = Math.min(answer, diff);
			return;
		}

		for (int i = idx; i < n; i++) {
			if (visited[i]) {
				continue;
			}

			Pair<Long> ci = ingredients[i];

			long nextS = count == 0 ? ci.s : sSum * ci.s;
			long nextV = vSum + ci.v;

			visited[i] = true;
			solve(nextS, nextV, idx + 1, count + 1);
			visited[i] = false;
			solve(sSum, vSum, idx + 1, count);
		}
	}

	static void init() {
		n = scan.nextInt();
		ingredients = new Pair[n];
		visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			long s = scan.nextLong();
			long v = scan.nextLong();

			ingredients[i] = new Pair(s, v);
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
